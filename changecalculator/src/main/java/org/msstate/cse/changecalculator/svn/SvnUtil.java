package org.msstate.cse.changecalculator.svn;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.msstate.cse.changecalculator.utility.Util;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.tmatesoft.svn.core.wc2.SvnOperationFactory;

public class SvnUtil {
	
	private static SvnOperationFactory svnOperationFactory = null;
	private static SVNClientManager svnClientManager = null;
	
	public static SvnOperationFactory getSvnOperationFactory(){
		if(svnOperationFactory==null){
			svnOperationFactory = new SvnOperationFactory();
		}
		return svnOperationFactory;
	}

	public static SVNClientManager getSvnClientManager(){
		if(svnClientManager == null){
			// SVNWCUtil is a utility class that creates a default options driver.
					DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
 
					// Create instance of SVNClientManager providing authentication info and
					// options driver
					svnClientManager = SVNClientManager.newInstance(options, "anonymous", "anonymous");
		
		}
		return svnClientManager;
	}

	/**
	 * Return diff content
	 * 
	 * @param url	svnUrl
	 * @param revisionNumber
	 * @return Output of "svn diff -c revisionNumber url"
	 */
	public static String getDiffContent(String url, long revisionNumber) {
		if(Util.isNullOrEmpty(url)){
			System.err.println("Invalid URL.");
			return null;
		}
		if(revisionNumber<0){
			System.err.println("Invalid revision number. Revision number cannot be less than zero.");
		}
		String diffString = "";
			try {
				SVNURL svnURL = SVNURL.parseURIEncoded(url);
				SVNRevision svnRevNumber = SVNRevision.create(revisionNumber);
				SVNRevision prevSvnRevNumber = SVNRevision.create(revisionNumber - 1);
				// Getting svn client manager
				SVNClientManager clientManager = getSvnClientManager();
				SVNDiffClient diffClient = clientManager.getDiffClient();
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream result = new PrintStream(baos);
				diffClient.doDiff(svnURL, svnRevNumber, prevSvnRevNumber,
						svnRevNumber, SVNDepth.INFINITY, true, result);
				diffString = baos.toString();
			} catch (SVNException e) {
				System.err.println("Error getting diff content with revison number " + revisionNumber+" at URL:" + url);
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		return diffString;
	}
}

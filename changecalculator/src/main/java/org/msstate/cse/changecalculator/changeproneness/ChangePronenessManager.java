package org.msstate.cse.changecalculator.changeproneness;


import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.msstate.cse.changecalculator.database.DbUtil;
import org.msstate.cse.changecalculator.diff.DiffParser;
import org.msstate.cse.changecalculator.hibernate.HibernateUtil;
import org.msstate.cse.changecalculator.hibernate.model.SvnHistroy;
import org.msstate.cse.changecalculator.properties.Properties;
import org.msstate.cse.changecalculator.svn.SvnUtil;
import org.msstate.cse.changecalculator.utility.Util;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class ChangePronenessManager {
	private String svnUrl = "";
	private long startRevNum = -1;
	private long endRevNum = -1;
	
	public ChangePronenessManager(Properties prop) {
		if(null==prop){
			System.err.println("Properties file is null");
		}
		this.svnUrl = prop.getSvn_url();
		this.startRevNum = prop.getStartRevNum();
		this.endRevNum = prop.getEndRevNum();
	}
	
	public void calculateChangeProneness() {
		if (Util.isNullOrEmpty(svnUrl)) {
			System.err.println("Invalid SVN URL.");
			return;
		}
		if (startRevNum < 0) {
			System.err.println("Invalid start revision nubmer ");
			return;
		}
//		if (endRevNum < 0) {
//			System.err.println("Invalid end revision nubmer ");
//			return;
//		}

		Collection<Long> logEntries = null;
		try {
			SVNRepository repository = getSvnRepository();
			// end revision number
			endRevNum = repository.getLatestRevision();
			logEntries = repository.log(new String[] { "" }, null, startRevNum, endRevNum, true, true);
		} catch (SVNException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			
		}
		
		// ignore first revision
		boolean isFirstRevision = true;
		
		//iterate over each log entries
		for (Iterator entries = logEntries.iterator(); entries.hasNext();) {
			try {
				SVNLogEntry logEntry = (SVNLogEntry) entries.next();
				long revNum = logEntry.getRevision();
				
				System.out.println("Currently Looking at revision # " +revNum);

				// ignore first revision
				if (isFirstRevision) {
					isFirstRevision = false;
					continue;
				}

				// get diff content
				String diffString = SvnUtil.getDiffContent(svnUrl, revNum);
				if (Util.isNullOrEmpty(diffString)) {
					continue;
				}
				// get changed classes
				List<String> changedClassList = DiffParser.getChangedFiles(diffString);
				if (null == changedClassList || changedClassList.isEmpty()) {
					continue;
				}
				// iterate changedClassList
				Iterator<String> iter = changedClassList.iterator();
				while (iter.hasNext()) {
					String classPath = iter.next();
					if (Util.isNullOrEmpty(classPath)) {
						continue;
					}

					if (classPath.contains(".java")) {
						saveIntoDB(classPath, logEntry);
					}
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				continue;
			}
		}

	}
	
	private void saveIntoDB(String filePath, SVNLogEntry logEntry){
		//create SvnHistory model
		SvnHistroy svnHistory = new SvnHistroy();
		svnHistory.setClassPath(formatFilePath(filePath));
		svnHistory.setRevNum(logEntry.getRevision());
		svnHistory.setDate_milisecond(convertIntoMiliSecond(logEntry.getDate()));
		DbUtil.saveIntoDB(svnHistory);
	}
	
	private long convertIntoMiliSecond(Date date){
		if(null == date){
			System.err.println("Invalid Date");
			return -1 ;
		}
		return date.getTime();
		
	}

	private String formatFilePath(String filePath) {
		if (Util.isNullOrEmpty(filePath)) {
			System.err.println("Invlalid filepath");
			return null;
		}
		int beginIndex = filePath.indexOf("org/apache/");
		String formattedString = filePath.substring(beginIndex);
		return formattedString;
	}
	
	private SVNRepository getSvnRepository() {
		SVNRepository repository = null;
		String name = "anonymous";
		String password = "anonymous";
		try {
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(svnUrl));
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
			repository.setAuthenticationManager(authManager);
		} catch (SVNException e) {
			e.printStackTrace();
		}
		return repository;
	}
}

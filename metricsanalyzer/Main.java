/*
 * Main.java
 */

package metricsanalyzer;
import java.io.*;
import java.util.Vector;
/**
 *
 * @author Byron
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String revisionNum;
        //String[] revisions = {"599254", "599285"} ;
        //String[] revisions = {"533549", "543076", "552009", "560024", "570247", "580646", "595448"} ;
        String xmlDir = "C:\\Documents and Settings\\Byron\\My Documents\\ESE Research\\Science of Design\\Metrics\\Lucene\\XML\\";
        
        File dir = new File(xmlDir);
        Vector revisions = new Vector();
        String[] children = dir.list();
        if (children = null) {
            // Either dir does not exist or is not a directory
        } else {
            for (int i=0; i<children.length; i++) {
                // Get filename of file or directory
                String fileName = children[i];
                revisions.addElement(fileName);
            }
        }
        XMLParser xml = new XMLParser();
        File xmlFile = new File(args[0]);
        revisionNum = new String(args[1]);
        
        for (int i=0; i<revisions.size(); i++) {
            //System.out.println(xmlDir+revisions.elementAt(i).toString());
            //System.out.println(revisions.elementAt(i).toString().substring(0,6));
            xmlFile = new File(xmlDir+revisions.elementAt(i).toString());
            xml.parseFile(xmlFile, revisions.elementAt(i).toString().substring(0,6));
        }
        
        //xml.parseFile(xmlFile, revisionNum);
    }
    
}

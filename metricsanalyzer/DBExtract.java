/*
 * DBExtract.java
 *
 */

package metricsanalyzer;

import java.io.*;
import java.sql.*;
/**
 *
 * @author Byron
 */
public class DBExtract {
 
    /** Creates a new instance of DBExtract */
    public DBExtract() {
        MetricsDB db = new MetricsDB();
        Connection con = db.getConnection();
        String systemMetrics = "SELECT * FROM systemmetrics"; 
        String packageMetrics = "SELECT * FROM packagemetrics"; 
        String issues = "SELECT * FROM issues";
        String metrics= "SELECT * FROM metrics"; 
        String repository= "SELECT * FROM repository"; 
        String revisionData= "SELECT * FROM revisiondata";  

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(repository);
            stmt.close();
        } catch (java.sql.SQLException sqe) {
            System.out.println("error adding metrics values:  "+ sqe);
        }
        db.closeConnection(); 
    }

}

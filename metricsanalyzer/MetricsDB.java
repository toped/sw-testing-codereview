/*
 * MetricsDB.java
 */

package metricsanalyzer;

import java.sql.*;
/**
 *
 * @author Byron
 */
public class MetricsDB {
    
    private Connection con;
    private String URL = "jdbc:mysql://localhost:3306/SoD";
    // private String URL = "jdbc:mysql://localhost:3306/SoD";
    private StringBuffer error = new StringBuffer(" ");
    private String username = "root";
    private String password = "10ft3\"LB";
    
    
    /** Creates a new instance of MetricsDB */
    public MetricsDB() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (Exception e) {
            System.out.println("ERROR: failed to load MySQL JDBC driver." + e);
            error.append("MYSQL Driver Exception:   " + e.toString());
            e.printStackTrace();
            return;
        }
        try {
            con = DriverManager.getConnection(URL, username, password);
            
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not establish connection to MySQL Database at URL: "+URL+ "\nEXCEPTION:"+ ex);
            error.append("SQL Connection Exception   " + ex.toString());
            ex.printStackTrace();
        }
        
    }
    
    public Connection getConnection() {
        return (con);
    }
    
    public void closeConnection() {
        try {
            con.close();
        } catch (java.sql.SQLException sqle) {
            System.out.println("Problem closing connection:  " + sqle);
        }
    }
    
    public String getError() {
        return (error.toString());
    }
}

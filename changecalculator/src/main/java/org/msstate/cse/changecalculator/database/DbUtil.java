package org.msstate.cse.changecalculator.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.msstate.cse.changecalculator.hibernate.HibernateUtil;
import org.msstate.cse.changecalculator.hibernate.model.SvnHistroy;
import org.msstate.cse.changecalculator.utility.Util;

public class DbUtil {
	private static PreparedStatement ps = null;
	private static String tableName = null;
	
	
	
	public static void setTableName(String tableName) {
		DbUtil.tableName = tableName;
	}

	public static Connection getDBConnection(){
		DBConnection dbCon = new DBConnection();
		return dbCon.getConnection();
	}
	
	public static void saveIntoDB(Object obj){
		SvnHistroy svnHis = (SvnHistroy) obj;
		/*//session
		Session session = HibernateUtil.getSession();
		
	    //creating transaction object  
	    Transaction t=session.beginTransaction(); 
	    session.save(svnHis);
//	    session.persist(svnHis);//persisting the object  
	    t.commit();//transaction is commited  
	    //close session
	    session.close();  */
		
		if(Util.isNullOrEmpty(tableName)){
			System.err.println("Please provide valid table name. Table name is empty.");
			return;
		}
		
		try {
			Connection con = getDBConnection();
			ps = con.prepareStatement("SELECT * FROM "
					+ tableName
					+ " WHERE rev_num = ? AND class_path = ?");
			ps.setLong(1, svnHis.getRevNum());
			ps.setString(2, svnHis.getClassPath());
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				// Issue not yet in DB. Add to DB.
				ps = con.prepareStatement("INSERT INTO "
						+ tableName
						+ " (rev_num, class_path, date_time_ms) "
						+ "VALUES (?, ?, ?)");
				ps.setLong(1, svnHis.getRevNum());
				ps.setString(2, svnHis.getClassPath());
				ps.setLong(3, svnHis.getDate_milisecond());
				ps.executeUpdate();
			} 

		} catch (SQLException e) {
			System.err.println("Error inserting value");
			e.printStackTrace();
			
		}

	}

}

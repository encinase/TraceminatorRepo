package ar.com.traceminator.misc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilsSQL {
	
public static Connection connectSQLLite(String db){
		
		Connection conn = null;
		try {
			// db parameters
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:" + db; //C:/sqlite/db/chinook.db";
			// create a connection to the database
			conn = DriverManager.getConnection(url);
//			System.out.println("Connection to SQLite has been established.");
			return conn;
          
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	public static void disconnectSQLLite(Connection conn){
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	public static void createNewDatabase(String db) {
		 
        String url = "jdbc:sqlite:" + db; //C:/sqlite/db/" + fileName;
 
        try {
        	Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public static void createNewTable(String db, String sql) {
        // SQLite connection string
//        String url = "jdbc:sqlite:C://sqlite/db/tests.db";
        
        // SQL statement for creating a new table
//        String sql = "CREATE TABLE IF NOT EXISTS versiones (\n"
//        		+ "operacion text PRIMAREY KEY, \n"
//                + "	inteVer text NOT NULL,\n"
//                + "	testVer text NOT NULL,\n"
//                + "	prodVer text NOT NULL,\n"
//                + "	contVer text NOT NULL,\n"
//                + "	inteFec text NOT NULL,\n"
//                + "	testFec text NOT NULL,\n"
//                + "	prodFec text NOT NULL,\n"
//                + "	contFec text NOT NULL"
//                + ");";
        Connection conn = null;
        try {
        	
        	conn = connectSQLLite(db);
            Statement stmt = conn.createStatement();
            disconnectSQLLite(conn);
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
	
	public static void insert(Connection conn,String db, String sql) {
		
		// ejemplo insert
//        String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";
		
		// ejemplo update
//		String sql = "UPDATE warehouses SET name = ? , "
//      + "capacity = ? "
//      + "WHERE id = ?";
		
		// ejemplo delete
//		String sql = "DELETE FROM warehouses WHERE id = ?";

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
        
    }
	
	public static void query(String db, String sql){
//        String sql = "SELECT id, name, capacity FROM warehouses";
		Connection conn = null;
        try {
        	conn = connectSQLLite(db);
        	Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
            	System.out.println(rs.getInt("id") +  "\t" + 
                                  rs.getString("name") + "\t" +
                                  rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
    }

}

package ar.com.traceminator.misc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.traceminator.variables.Global;

public class UtilsReportes {
	
	static String archivoVersiones = Global.DirWork  + "/deploys/versiones.csv";
	
	public static List obtenerOperaciones(){
		
		List<String> operaciones;
		operaciones = new ArrayList<String>();
		
		String sql = "";
    	Connection conn = UtilsSQL.connectSQLLite(Global.db);
    	Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs    = stmt.executeQuery("Select operacion from T_VERSIONES_OP order by operacion");
	        // si rs.next es true es que ya existe la operacion y hago update
			while (rs.next()){
				operaciones.add(rs.getString("operacion"));
			} 
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	UtilsSQL.disconnectSQLLite(conn);
		return operaciones;
	}
}

package ar.com.traceminator.variables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import ar.com.traceminator.utils.SimThread;


public class Global {
	
//	public static String DirWork = "C:/Traceminator/";
//	public static String DirWork = "E:/Traceminator/"; 
	public static String DirWork = System.getenv("TRACE_HOME") + "/";
	public static String db = DirWork + "db/traceminator.db";
	
	public static int cantHilos = 0;
	public static HashMap<String, List<String>> simVivos = new HashMap();
//	public static HashMap<String, SimThread> threadVivos = new HashMap();
	
	// Supervielle
//	static Properties prop = new Properties();
//	prop.load(new FileInputStream("/properties/adminSOA.properties"));
//	public static String DirWork = prop.getProperty("DirWork");
	public static String svnUser = "eencinas";
	public static String svnPass = "Deloitte";
	public static String svnSupsevicesPath = "http://10.241.161.30:9880/SUPServices/";
	public static String svnVersionsPath =  svnSupsevicesPath + "versions";
}
//import java.beans.Statement;

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.io.InputStreamReader;
//import java.util.Scanner;


public class citadel {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		/*String dict_file = "input.txt";
		if(args.length == 0) {
			System.out.println("enter dictionary file"); 
			Scanner in = new Scanner(System.in);
			dict_file = in.nextLine(); //Строка
		} else dict_file = args[0];
			try{
			
	        String log_file = "twitter.txt";
	        System.out.println("load dictionary file: \"" + dict_file + "\""); 

	        FileWriter fw = new FileWriter(log_file , true);
	        
	        //FileReader fr = new FileReader(dict_file);
	        //String query = "";
	        //while(fr.read(query))
	        
	        //final String CHARSET="UTF-8";
	        File in = new File(dict_file);
	        
	        BufferedReader reader = null;
	        reader = new BufferedReader(new InputStreamReader(new FileInputStream(in), "UTF-8"));
	        
	        //new InputStreamReader(new FileInputStream(in),CHARSET))

		    for (String line; (line = reader.readLine()) != null;) {
		    	//System.out.println("line: " + line);
		    	one_query.search(line, fw);
		    }
	        
	        fw.close();
			
			System.out.println("\nSee log file: \"" + log_file + "\"");
	        
	    }catch(FileNotFoundException e){
	        System.out.println("open file error");
	    }*/
		System.out.println("test");
//------------------------------------------------------------------------------------------
		Class.forName("org.sqlite.JDBC");
	    
	    Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	      Statement statement = (Statement) connection.createStatement();
	      ((java.sql.Statement) statement).setQueryTimeout(30);  // set timeout to 30 sec.
	      
	      ((java.sql.Statement) statement).executeUpdate("drop table if exists person");
	      ((java.sql.Statement) statement).executeUpdate("create table person (id integer, name string)");
	      ((java.sql.Statement) statement).executeUpdate("insert into person values(1, 'leo')");
	      ((java.sql.Statement) statement).executeUpdate("insert into person values(2, 'yui')");
	      ResultSet rs = ((java.sql.Statement) statement).executeQuery("select * from person");
	      while(rs.next())
	      {
	        // read the result set
	        System.out.println("name = " + rs.getString("name"));
	        System.out.println("id = " + rs.getInt("id"));
	      }
	    }
	    catch(SQLException e)
	    {
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }
	    }
//-----------------------------------------------------------------------------------------
	}

}

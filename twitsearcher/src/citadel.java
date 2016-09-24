import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.InputStreamReader;
import java.util.Scanner;


public class citadel {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String dict_file = "input.txt";
		String base_file = "base.db";
		int rpp = 100;
		
		String logo = "\n" +
"    .                     o8o      .       . search module v1.2\n" +
"  .o8                     `\"'    .o8     .o8\n" +
".o888oo oooo oooo    ooo oooo  .o888oo .o888oo  .ooooo.  oooo d8b\n" +
"  888    `88. `88.  .8'  `888    888     888   d88' `88b `888\"\"8P\n" +
"  888     `88..]88..8'    888    888     888   888ooo888  888\n" +
"  888 .    `888'`888'     888    888 .   888 . 888    .o  888\n" +
"  \"888\"     `8'  `8'     o888o   \"888\"   \"888\" `Y8bod8P' d888b\n\n";
		System.out.print(logo);
		
//------------------------------------------------------------------------------------------
		Class.forName("org.sqlite.JDBC");
	    
	    Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:" + base_file);
	      Statement sm = (Statement) connection.createStatement();
	      ((java.sql.Statement) sm).setQueryTimeout(30);  // set timeout to 30 sec.
	      
/*
//SQL Lite
CREATE TABLE if not exists "tbl_tweets_base" (
"id" INTEGER PRIMARY KEY  NOT NULL,
"t_user" VARCHAR(100) NOT NULL ,
"t_text" VARCHAR(150) NOT NULL UNIQUE,
"t_created_at" DATETIME
);
//MySQL
CREATE TABLE if not exists `tbl_tweets_base` (
id INT(9) NOT NULL AUTO_INCREMENT,
t_user VARCHAR(100) NOT NULL ,
t_text VARCHAR(150) NOT NULL UNIQUE,
t_created_at DATETIME,
PRIMARY KEY(id)
);

//SQL Lite
insert or ignore into tbl_tweets_base (t_user, t_text, t_created_at) values("from_user", "text", "Wed, 23 Jan 2013 14:06:45 +0000"), ("from_user2", "text2", "Wed, 23 Jan 2013 14:06:45 +0000");
//MySQL
insert ignore into tbl_tweets_base (t_user, t_text, t_created_at) values("from_user", "text6", STR_TO_DATE("Wed, 23 Jan 2013 14:06:45 +0000", "%a, %d %b %Y %H:%i:%s"));
*/
	      
	      //((java.sql.Statement) sm).executeUpdate("insert into tbl_tweets_base (t_user, t_text, t_created_at_str) values('test_user', 'boobs', '1.09.1997')");
	      
	      
	      //-------------------------------------------
	      if(args.length == 0) {
				System.out.print("enter dictionary file: "); 
				Scanner in = new Scanner(System.in);
				dict_file = in.nextLine(); //Строка
			} else dict_file = args[0];
				try{
				
		        //String log_file = "twitter.txt";
		        
		        

		        //FileWriter fw = new FileWriter(log_file , true);
		        
		        //FileReader fr = new FileReader(dict_file);
		        //String query = "";
		        //while(fr.read(query))
		        
		        //final String CHARSET="UTF-8";
		        File in = new File(dict_file);
		        BufferedReader reader = null;
		        reader = new BufferedReader(new InputStreamReader(new FileInputStream(in), "UTF-8"));
		        System.out.println("open file: \"" + dict_file + "\"");
		        
		        //new InputStreamReader(new FileInputStream(in),CHARSET))
		        System.out.println("Start searching, " + rpp + "\" requests for each string/word");		        

			    for (String line; (line = reader.readLine()) != null;) {
			    	//System.out.println("line: " + line);
			    	one_query.search(line, sm, rpp);
			    }
		        
		        //fw.close();
				
				//System.out.println("\nSee log file: \"" + log_file + "\"");
			    System.out.println("\nResult stored in: \"" + base_file + "\"");
		        
		    }catch(FileNotFoundException e){
		        System.out.println("error while opening dictionary file.");
		    }
	      //-------------------------------------------

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
	    
	    System.out.println("bye.");
//-----------------------------------------------------------------------------------------
	}

}

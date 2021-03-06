import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Statement;

import com.bigfatgun.fixjures.Fixjure;
import com.bigfatgun.fixjures.json.JSONSource;


public class one_query {
	//public static int rpp = 100; // max records in one session

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public static void search(String query, Statement sm, int rpp) throws UnsupportedEncodingException, IOException, SQLException {
		//try {
			
			System.out.print(" query: \"" + query + "\" ...");
			String query_utf8 = URLEncoder.encode(query, "UTF-8");
			// TODO isset file in DB
			
			// https://api.twitter.com/1.1/statuses/show.json?id=210462857140252672
			
			// https://dev.twitter.com/docs/api/1/get/search
			// http://search.twitter.com/search.json?q=:)&rpp=100&lang=ru&page=1
			// rpp max 100
			// page max 15
			// 100 * 15 = 1500
			
			// to user = reply
			// &include_entities=true
			//
			URL url = new URL("https://search.twitter.com/search.json?rpp=" + rpp + "&q=" + query_utf8);
			BufferedReader reader = null;

			String callback = new String();
			
			try {
				// received a reply
			    reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			    for (String line; (line = reader.readLine()) != null;) {
			    	callback += line;
			    }
			    /*System.out.println("\n**************RAW*************************************\n"
			    		+ callback 
			    		+ "\n************END*RAW***********************************\n");*/
			
			    tweetData qtweetData = Fixjure.of(tweetData.class).from(JSONSource.newJsonString(callback)).create();
			    // TODO

			    String results_str = qtweetData.getResults();
			    // no tweets for this query
			    if(results_str.equals("[]")) {
			    	System.out.println("\b\b\bNot found");
			    	return;
			    }
			    // delete parts (from which errors)
			    results_str = results_str.replace(",\"metadata\":{\"result_type\":\"recent\"}", "");
			    results_str = results_str.replace("\"geo\":null,", "");
			    results_str = results_str.replace("\"to_user_name\":null,", "");
			    results_str = results_str.replace("\"to_user\":null,", "");
			    
			    // divided into an array "tweets"
			    results_str = results_str.replace("},{", "}01g4pk3y01{");
			    String[] tmp = results_str.split("01g4pk3y01");
			    
			    tmp[0] = tmp[0].substring(1, tmp[0].length());
			    tmp[tmp.length - 1] = tmp[tmp.length - 1].substring(0, tmp[tmp.length - 1].length() - 1);
			    int i = 0;
			    for(; i < tmp.length ; i++) {
			    	// retrieving results
			    	tweetDetails qtweetDetails = Fixjure.of(tweetDetails.class).from(JSONSource.newJsonString(tmp[i])).create();
			    	
			    	String text = qtweetDetails.getText();
			    	
			    	text = text.replace("\"", "'");
			    	
			    	/*//add or remove two slash to change 
			    	
			    	//sm.write(text + "\r\n");
			    	
			    	/*/
			    	
			    	// record into file
			    	//fw.write("search result to query: " + query + "\r\n");
			    	
			    	//String id = qtweetDetails.getId();
			    	//String from_user_id = qtweetDetails.getFrom_user_id();
			    	String from_user = qtweetDetails.getFrom_user();
			    	//String iso_language_code = qtweetDetails.getIso_language_code();
			    	String created_at = qtweetDetails.getCreated_at();
			    	
			    	String sql_query = "insert or ignore into tbl_tweets_base (t_user, t_text, t_created_at) values(\"" + from_user + "\", \"" + text + "\", \"" + created_at + "\");";
			    	//System.out.print("log: text: " + text + "\n query: " + sql_query + "\n");
			    	
			    	sm.executeUpdate(sql_query);
			    	
			    	//fw.write("id: " + id + "\r\n");
			    	//fw.write("text: " + text + "\r\n");
			    	//fw.write("from_user_id: " + from_user_id + "\r\n");
			    	//fw.write("from_user: " + from_user + "\r\n");
			    	//fw.write("iso_language_code: " + iso_language_code + "\r\n");
			    	//fw.write("created_at: " + created_at + "\r\n");
			    	//fw.write("-----------------------------------------------------------------\r\n");
			    	
			    	/*/
			    	
			    	// TODO record into DB
			    	
			    	// show on display
			     	/*System.out.println("id: " + id);
			     	System.out.println("text: " + text);
			     	System.out.println("from_user_id: " + from_user_id);
			     	System.out.println("from_user: " + from_user);
			     	System.out.println("iso_language_code: " + iso_language_code);
			     	System.out.println("created_at: " + created_at);
			     	System.out.println("----------------------------");*/
			     	
			    }	System.out.print("\b\b\bFound: " + i + "\n");
			    
			} finally {
			    if (reader != null) try { reader.close(); } catch (IOException ignore) {}
			}
			
		//} catch(ArrayIndexOutOfBoundsException e) {
		//	System.out.print("(.)(.)");
		//}
	}
}

//----------------------MYSQL-----------------------------------------------
/*
id (tweet_id unique)
text
from_user_id
from_user
iso_language_code
created_at

id_query_word
record_to_bd_at
*/
/*			     	Connection             conn  = null;        
			        PreparedStatement   pstmt = null;
			        ResultSet            rs    = null;
			        
			        try {
			                        
			             
			            Properties connInfo = new Properties();
			            
			            connInfo.put("characterEncoding","UTF8");
			            connInfo.put("user", "root");
			            connInfo.put("password", "usbw");
			            
			            conn  = DriverManager.getConnection("jdbc:mysql://localhost:3307/?", connInfo);
			            
			            pstmt = conn.prepareStatement("show databases;");
			            
			            if(pstmt.execute()) {
			                rs = pstmt.getResultSet();                                
			                
			                while (rs.next()) {
			                    //System.out.println(rs.getString(1));
			                    System.out.println(rs.getString("Database"));
			                }
			            }                                    
			        }
			        catch (SQLException ex) {            
			            System.out.println(ex.toString());
			            //ex.printStackTrace();
			        }
			        finally {
			            
			            try {
			                
			                if (pstmt != null)
			                    pstmt.close();
			                
			                if (conn != null)
			                    conn.close();                
			            }
			            catch (SQLException ex) {            
			                System.out.println("On close: " + ex.toString());
			                //ex.printStackTrace();
			            }                        
			            
			        }        
*/
//--------------------MYSQL-END---------------------------------------------

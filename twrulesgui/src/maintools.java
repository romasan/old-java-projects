/*
 * Twitter Analytics Tool
 * use for reflection - Twitter GET search: https://dev.twitter.com/docs/api/1/get/search
 * create by: login0101
 * original name: boobs_curl
 * creation date: winter vacation in January 2013
 */

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;

public class maintools {
	
	static String dictonary_file = "";
	static Boolean write_log = false;
	static Boolean connect_to_database = true;
	static Boolean write_raw_sql = false;
	static Boolean use_any_mode = false;
	static Boolean only_russian = true;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Display display = new Display();
	    final Shell shell = new Shell(display, SWT.MIN);
	    shell.setText("Twitter Analytics Tool (Search module v1.3.4)");
	    shell.setMinimumSize(680, 400);
	    
	    /*
	     Button[] radios = new Button[3];
    
    radios[0] = new Button(shell, SWT.RADIO);
    radios[0].setSelection(true);
    radios[0].setText("SQL Lite");
    radios[0].setBounds(10, 5, 75, 30);
 
    radios[1] = new Button(shell, SWT.RADIO);
    radios[1].setText("MySQL");
    radios[1].setBounds(10, 30, 75, 30);
	     */
	    
	    //final Button cb_create_table = new Button(shell, SWT.CHECK);
	    //cb_create_table.setText("Create table (if not exists)");
	    //cb_create_table.setBounds(10, 35, 150, 20);
	    //cb_create_table.setSelection(true);

//----------------------------------Checkbox write raw sql
	    final Button cb_write_raw_sql = new Button(shell, SWT.CHECK);
	    cb_write_raw_sql.setText("Write raw SQL (raw.sql)");
	    cb_write_raw_sql.setBounds(10, 35, 150, 20);

//----------------------------------Checkbox write log
	    final Button cb_writelog = new Button(shell, SWT.CHECK);
	    cb_writelog.setText("Write log (twitter.log)");
	    cb_writelog.setBounds(10, 60, 150, 20);
	    
//----------------------------------Checkbox only russian
	    final Button cb_only_russian = new Button(shell, SWT.CHECK);
	    cb_only_russian.setText("������ �� �������");
	    cb_only_russian.setBounds(10, 85, 150, 20);
	    cb_only_russian.setSelection(true);
	    
//------------------------------Form (address, base, user, password)
	    Label label_url = new Label(shell, SWT.RIGHT);
	    label_url.setText("Address: ");
	    label_url.setBounds(140, 10, 80, 20);
	    
	    //final String txt_url = "example:3306";
	    final String txt_url = "localhost:3307";
	    final Text text_url = new Text(shell, SWT.BORDER);
	    text_url.setBounds(230, 10, 170, 20);
	    text_url.setText(txt_url);
	    
	    text_url.addListener(SWT.Activate, new Listener() {
			public void handleEvent(Event e) {
				if(txt_url.equals(text_url.getText())) text_url.setText("");				
			}
	    });
	    
	    text_url.addListener(SWT.FocusOut, new Listener() {
			public void handleEvent(Event e) {
				if(text_url.getText() == "") text_url.setText(txt_url);				
			}
	    });
//---	    
	    Label label_base = new Label(shell, SWT.RIGHT);
	    label_base.setText("Database: ");
	    label_base.setBounds(140, 35, 80, 20);
	    
	    //final String txt_base = "basename";
	    final String txt_base = "test";
	    final Text text_base = new Text(shell, SWT.BORDER);
	    text_base.setBounds(230, 35, 170, 20);
	    text_base.setText(txt_base);
	    
	    text_base.addListener(SWT.Activate, new Listener() {
			public void handleEvent(Event e) {
				if(txt_base.equals(text_base.getText())) text_base.setText("");				
			}
	    });
	    
	    text_base.addListener(SWT.FocusOut, new Listener() {
			public void handleEvent(Event e) {
				if(text_base.getText() == "") text_base.setText(txt_base);				
			}
	    });
//---
	    Label label_user = new Label(shell, SWT.RIGHT);
	    label_user.setText("User: ");
	    label_user.setBounds(140, 60, 80, 20);
	    
	    //final String txt_user = "username";
	    final String txt_user = "root";
	    final Text text_user = new Text(shell, SWT.BORDER);
	    text_user.setBounds(230, 60, 170, 20);
	    text_user.setText(txt_user);
	    
	    text_user.addListener(SWT.Activate, new Listener() {
			public void handleEvent(Event e) {
				if(txt_user.equals(text_user.getText())) text_user.setText("");				
			}
	    });
	    
	    text_user.addListener(SWT.FocusOut, new Listener() {
			public void handleEvent(Event e) {
				if(text_user.getText() == "") text_user.setText(txt_user);				
			}
	    });
//---
	    Label label_password = new Label(shell, SWT.RIGHT);
	    label_password.setText("Password: ");
	    label_password.setBounds(140, 85, 80, 20);
	    
	    //final String txt_password = "password";
	    final String txt_password = "usbw";
	    final Text text_password = new Text(shell, SWT.BORDER | SWT.PASSWORD);
	    text_password.setBounds(230, 85, 170, 20);
	    text_password.setText(txt_password);
	    
	    text_password.addListener(SWT.Activate, new Listener() {
			public void handleEvent(Event e) {
				if(txt_password.equals(text_password.getText())) text_password.setText("");				
			}
	    });
	    
	    text_password.addListener(SWT.FocusOut, new Listener() {
			public void handleEvent(Event e) {
				if(text_password.getText() == "") text_password.setText(txt_password);				
			}
	    });
	    
//--------------------------------Input form
	    //Label text_query_label = new Label(shell, SWT.RIGHT);
	    //text_query_label.setBounds(10, 290, 40, 20);
	    //text_query_label.setText("Query:");
	    
	    Label label_text_query = new Label(shell, SWT.None);
	    label_text_query.setText("Search query:");
	    label_text_query.setBounds(10, 260, 130, 20);
	    
	    final Text text_query = new Text(shell, SWT.BORDER);
	    text_query.setBounds(10, 280, 130, 50);
	    text_query.setText(":)");
	    Font initialFont = text_query.getFont();
	    FontData[] fontData = initialFont.getFontData();
	    for (int i = 0; i < fontData.length; i++) {
	      fontData[i].setHeight(20);
	    }
	    Font newFont = new Font(display, fontData);
	    text_query.setFont(newFont);
	    //text_query.setFont(new Font("Arial", 10));//setFont(new Font("Arial", 14, SWT.BOLD ) );
	    
//--------------------------------Select emotion combo
	    final Combo combo = new Combo(shell, SWT.READ_ONLY);
	    combo.setItems(new String[] { "positive", "neutral", "negative" });
	    //combo.setSize(200, 200);
	    combo.select(1);
	    combo.setBounds(10, 335, 130, 20);
	    
//--------------------------------Horisontal line
	    Label hr = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    hr.setBounds(10, 110, 660, 2);
	    
//----------------------------------Log textarea
	    final Text textarea = new Text(shell, SWT.BORDER | SWT.V_SCROLL);
	    textarea.setBounds(150, 120, 510, 240);
	    textarea.setEditable(false);
	    //textarea.append(logo + "\n");

//----------------------------------Checkbox connect to database (default true)
	    final Button cb_connect_to_database = new Button(shell, SWT.CHECK);
	    cb_connect_to_database.setText("Connect to database.");
	    cb_connect_to_database.setBounds(10, 10, 150, 20);
	    cb_connect_to_database.setSelection(true);
	    
	    cb_connect_to_database.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				//if()
				if(cb_connect_to_database.getSelection()) {
					//textarea.append(" [ Database connect mode: ON ]\n");
					connect_to_database = true;
					text_url.setEnabled(true);
					text_base.setEnabled(true);
					text_user.setEnabled(true);
					text_password.setEnabled(true);
				} else {
					//textarea.append(" [ Database connect mode: OFF ]\n");
					connect_to_database = false;
					text_url.setEnabled(false);
					text_base.setEnabled(false);
					text_user.setEnabled(false);
					text_password.setEnabled(false);
				}
			}
	    });
	    
//-----------------------------------Button start
	    //Image img_start = new Image( display, "res/twitter.png" );
	    
	    //InputStream stream = maintools.c.getClass().getClassLoader().getResourceAsStream("res/twitter.png");
	    //Image img_start = new Image(display, stream);
	    final Button btn_run = new Button(shell, 0);
	    //btn_run.setText("RUN SEARCH MODULE");
	    //btn_run.setFont(new Font(display, "Arial", 14, SWT.BOLD ) );

	    //btn_run.setImage(img_start);

	    //btn_run.setEnabled(false);
	    btn_run.setBounds(10, 120, 130, 130);
	    
	    btn_run.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				
				textarea.setText("");
				//textarea.append("Start search...\n");
				
				if(cb_writelog.getSelection()) {
					textarea.append(" [ Write log: ON ]\n");
					write_log = true;
				} else {
					textarea.append(" [ Write log: OFF ]\n");
					write_log = false;
				}
				
				if(cb_write_raw_sql.getSelection()) {
					textarea.append(" [ Write raw SQL: ON ]\n");
					write_raw_sql = true;
				} else {
					textarea.append(" [ Write raw SQL: OFF ]\n");
					write_raw_sql = false;
				}
				
				if(cb_connect_to_database.getSelection()) {
					textarea.append(" [ MySQL: ON ]\n");
					connect_to_database = true;
				} else {
					textarea.append(" [ MySQL: OFF ]\n");
					connect_to_database = false;
				}
				
				if(cb_only_russian.getSelection()) {
					textarea.append(" [ Language: Russian ]\n");
					only_russian = true;
				} else {
					textarea.append(" [ Language: ANY ]\n");
					only_russian = false;
				}
				
				if(cb_connect_to_database.getSelection() || cb_write_raw_sql.getSelection() || cb_writelog.getSelection()) {
					//textarea.append(" [ MySQL: ON ]\n");
					use_any_mode = true;
				} else {
					textarea.append("Select at least one mode.\n");
					use_any_mode = false;
				}
//-----------------------------------------------------------------------------------------------------line start new thread
				btn_run.setEnabled(false);
				//System.out.println("Thread start (real)");
				
		    	Display.getDefault().asyncExec(new Runnable() {
		    	    public void run() {
		    	    
					try {
						if(connect_to_database) Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} 
					
					Connection conn = null;        
			        PreparedStatement pstmt = null;

			        if(use_any_mode) try {
			        	String address = text_url.getText();
			        	String base = text_base.getText();
			        	String user = text_user.getText();
			        	String password = text_password.getText();
			        	
			            Properties connInfo = new Properties();
			            connInfo.put("characterEncoding", "UTF8");
			            connInfo.put("user", user);
			            connInfo.put("password", password);
			            String url = "jdbc:mysql://" + address + "/" + base;
			            if(connect_to_database) conn = DriverManager.getConnection(url, connInfo);
			            
			            //pstmt = conn.prepareStatement("set names 'utf8';");
			            //pstmt = conn.prepareStatement("set names 'cp1251';");
						//pstmt.execute();
						    
			            //create table in base
			            if(connect_to_database) pstmt = conn.prepareStatement(
				            "CREATE TABLE if not exists `tbl_tweets_base` (" +
				            "id INT(9) NOT NULL AUTO_INCREMENT," +
				            "t_user VARCHAR(100) NOT NULL ," +
				            "t_text VARCHAR(150) NOT NULL UNIQUE," +
				            "t_created_at DATETIME," +
				            "t_emotion ENUM('positive', 'neutral', 'negative')," +
				            //"t_to_user_name VARCHAR(100)," +
				            "PRIMARY KEY(id)" + 
				            ") DEFAULT CHARSET='utf8';"
					    );
			            if(connect_to_database) pstmt.execute();
					    
					    FileWriter fw = null;
					    if(write_log) fw = new FileWriter("twitter.log" , true);
					    
					    FileWriter fw2 = null;
					    if(write_raw_sql) fw2 = new FileWriter("raw.sql", true);
	
					    // Read dictonary file
	//				    File in = new File(dictonary_file);
	//			        BufferedReader reader = null;
	//			        reader = new BufferedReader(new InputStreamReader(new FileInputStream(in), "UTF-8"));
	//			        textarea.append("Open file: \"" + dictonary_file + "\"\n");
	//				    for (String line; (line = reader.readLine()) != null;) {
	final String line = text_query.getText();
	final String emotion = combo.getText();//getCaretPosition();
	textarea.append("emotion: \"" + emotion + "\"\n");
					    	textarea.append("query: \"" + line + "\"\n");
					    	if(connect_to_database) pstmt = conn.prepareStatement("select count(id) as 'cnt' from tbl_tweets_base;");
				            
					    	Integer cnt = 0; //comment for testing without record
					    	if(connect_to_database) if(pstmt.execute()) {
				                ResultSet rs = pstmt.getResultSet();                                
				                rs.next();
			                    cnt = rs.getInt("cnt");
				            }// else textarea.append("err 0001");
	
	//new SearchModule(line, emotion, connect_to_database, write_log, write_raw_sql, fw, fw2, conn, pstmt, textarea).start();
	
	textarea.append("Please wait...\n");
					    	    	//System.out.println("Thread start");
					    			for(int p = 1; p < 16; p++) {// first 15 pages of results
					    		    	ArrayList<tweetElem> res = null;
					    					res = one_query.search(line, 100, p, only_russian);//line, 100, p
					    			    	int j = res.size();
					    			    	String sql_query = "insert ignore into tbl_tweets_base (t_user, t_text, t_created_at, t_emotion) values";
					    			    	String log_append = "";
					    			    	
					    			    	for(int i = 0; i < j; i++) {
					    			    		tweetElem e1 = res.get(i);
					    			    		sql_query = sql_query.concat("(\"" + e1.from_user + "\", \"" + e1.text + "\", \"" + e1.created_at + "\", \"" + emotion + "\")" + ((i < j - 1)?",":";"));
					    			    		if(write_log) log_append = log_append.concat(e1.text + "\r\n");
					    			    	}
					    			    	
					    			    	//textarea.append("SQL: " + sql_query + "\n");
					    			    	if(connect_to_database) {
					    			    		pstmt = conn.prepareStatement(sql_query);
					    			    		pstmt.execute();
					    			    	}
					    		            
					    		            if(write_log) fw.write(log_append);
					    		            if(write_raw_sql) fw2.write(sql_query + "\r\n");
					    	        
					    	    	}//end for 15 pages
	
					    	if(write_log) fw.close();
	    					if(write_raw_sql) fw2.close();
					    	
					        if(connect_to_database) pstmt = conn.prepareStatement("select count(id) as 'cnt' from tbl_tweets_base;");
					    	if(connect_to_database) if(pstmt.execute()) {
				                ResultSet rs = pstmt.getResultSet();                                
				                rs.next();
			                    cnt = rs.getInt("cnt") - cnt;
			                    textarea.append("added " + cnt + " records to database.\n");
				            }// else textarea.append("err 0002");
			        }
			        catch (SQLException ex) {
			        	textarea.append("Error code: " + ex.toString() + "\n");
			            //ex.printStackTrace();
			        } catch (UnsupportedEncodingException e2) {
			        	textarea.append("Error code: " + e2.toString() + "\n");
						//e2.printStackTrace();
					} catch (FileNotFoundException e3) {
						textarea.append("Error code: " + e3.toString() + "\n");
						//e3.printStackTrace();
					} catch (IOException e4) {
						textarea.append("Error code: " + e4.toString() + "\n");
						textarea.append("Please try again later.\n");
						//e4.printStackTrace();
					} catch (NullPointerException e5) {
						textarea.append("Not found.\n");
					}
			        finally {
			            try {
			                if (pstmt != null)
			                    pstmt.close();
			                if (conn != null)
			                    conn.close();                
			            }
			            catch (SQLException ex) {
			            	//textarea.append("boobs\n");           
			                //System.out.println("On close: " + ex.toString());
			                //ex.printStackTrace();
			            }                        
			            
			        }
			        
			        textarea.append("OK\n");
			        //System.out.println("Thread end (real)");
			        btn_run.setEnabled(true);
		    	    }//end run
		    	});//Thread end
//---------------------------------------------------------------------------------------------------------line end new thread
			}//end handle
	    });//end listener

//--------------------------------Enter dictonary file
	    //final Label label_dictonaryfile = new Label(shell, 0);
	    //label_dictonaryfile.setText("Not selectead dictonary file");
	    //label_dictonaryfile.setBounds(140, 122, 430, 18);
	    
	    //label_dictonaryfile.setEnabled(false);
	    
	    //Button button1 = new Button(shell, 0);
	    //button1.setText("open dictionary file");
	    //button1.setBounds(10, 120, 120, 20);
	    
	    //button1.setEnabled(false);
	    
	    //button1.addListener(SWT.Selection, new Listener() {
		//	public void handleEvent(Event e) {
		//		FileDialog fd = new FileDialog(shell);
		//          fd.setText("Open sqllite base file");
		//          //fd.setFilterPath("C:/");
		//          String[] filterExt = { "*.txt" };
		//          fd.setFilterExtensions(filterExt);
		//          dictonary_file = fd.open();
		//          if(dictonary_file != null) {
		//        	  label_dictonaryfile.setText(dictonary_file);
		//        	  btn_run.setEnabled(true);
		//          }
		//	}
	    //});
	    
//-----------------------------------Twitter logo
	    //Image image1 = new Image( display, "res/twitter_logo.png" );
	    //Label twitter_logo = new Label( shell, SWT.NONE );
	    //twitter_logo.setBounds(410, 3, 258, 80);
	    //twitter_logo.setImage( image1 );

//-----------------------------------Mysql logo	    
	    //Image image2 = new Image( display, "res/mysql_logo.png" );
	    //Label mysql_logo = new Label( shell, SWT.NONE );
	    //mysql_logo.setBounds(3, 3, 136, 70);
	    //mysql_logo.setImage( image2 );
	    
//-----------------------------------
	    shell.pack();
	    shell.open();
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch())
	        display.sleep();
	    }
	    display.dispose();

	}

}

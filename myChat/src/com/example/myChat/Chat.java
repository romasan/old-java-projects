package com.example.myChat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieStore;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.my2.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.EditText;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Chat extends Activity {
	private static final String BOOKKEY = "bookkey";
	private static final String PRICEKEY = "pricekey";
	private static int OPTIONS_TYPE = 0;
	private Button myButton;
	private ImageButton myImg;
	public EditText myText;
	public TextView myChat;
	public EditText myLogin;
	public EditText myPassword;
	public TextView myRequest;
	public TextView myRegRequest;
	public String key = "";
	public String lasttime = "";
	public Boolean aloop = false;
	public Timer myTimer;
	public String host = "http://109.174.40.95/json/";
	BasicCookieStore cookieStore;
	HttpContext localContext;
	ListView listView;  //определяем наш ListView в main.xml 
    ArrayList<HashMap<String, Object>> myBooks;      //создаем массив списков
    HashMap<String, Object> hm;                             //список объектов
    SimpleAdapter adapter;
	public EditText myConfirm;
	
//-------------------------------------------------------------------------------------------------------------------main
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login();
    }
//-------------------------------------------------------------------------------------------------------------------loginFunctions
    public void addMyRequest(String J) {
    	myRequest.setText(J);
    }

	@SuppressLint("UseValueOf")
	public void loginJson(String J) throws JSONException {
    	
    	JSONObject jsonObj = new JSONObject(J);
    	    Log.e("boobs", jsonObj.getString("error").toString());
    	    String bt = new Boolean(true).getClass().getName();
    	    if(jsonObj.get("error").getClass().getName() == bt) {
    	    	if(jsonObj.getBoolean("error") == false){
    	    		chat();
    	    	};
    	    }
    }
//-------------------------------------------------------------------------------------------------------------------registrationFunction	
	public void myRegInfo(String J) throws JSONException {
    	
    	JSONObject jsonObj = new JSONObject(J);
    	    //Log.e("boobs", jsonObj.getString("error").toString());
    	    String bt = new Boolean(true).getClass().getName();
    	    if(jsonObj.get("error").getClass().getName() == bt) {
    	    	if(jsonObj.getBoolean("error") == false){
    	    		//login();
    	    		//myRegRequest.setText("Registration successfull");
    	    		//addMyRequest("Registration successfull");
    	    		Log.e("boobs", "registration success");
    	    	};
    	    } else {
    	    	//myRegRequest.setText(jsonObj.getString("error"));
    	    	//addMyRequest(jsonObj.getString("error"));
    	    	Log.e("boobs", jsonObj.getString("error"));
    	    }
    }
//-------------------------------------------------------------------------------------------------------------------getMessagesFunction
	public void writeMessages(String J) {
		try {
			JSONObject jsonObj = new JSONObject(J);
			String bt = new Boolean(true).getClass().getName();
    	    if(jsonObj.get("error").getClass().getName() == bt) {
    	    	if(jsonObj.getBoolean("error") == false){
    	    		int cnt = jsonObj.getInt("count");
    	    		if(cnt > 0) {
    	    			//String st = "<p>", me = st + "<font color=#9999ff>", m = "</font> ", oth = st + "<font color=#66ff66>", e = "</p>";
    	    			//String text = "";
    	    			for(int i = cnt - 1; i >= 0; i--) {
    	    					String mfrom = "mfrom_" + i;
    	    					String message = "message_" + i;
    	    					String itsme = "itsme_" + i;
    	    					//text += (jsonObj.getBoolean(itsme))?me:oth;
    	    	        	  //text += jsonObj.getString(mfrom) + ":" + m + jsonObj.getString(message) + e;
    	    	        	  hm = new HashMap<String, Object>();
    	    	                hm.put(BOOKKEY, jsonObj.getString(mfrom));
    	    	                hm.put(PRICEKEY, jsonObj.getString(message));
    	    	                myBooks.add(hm);
    	    			}
    	    			adapter.notifyDataSetChanged();
    	                listView.setSelection(adapter.getCount() - 1);
    	                //myChat.append(Html.fromHtml(text));
    	    			//int x = ( myChat.getLineCount()-1)*myChat.getLineHeight() - myChat.getHeight();
    	    			//Log.e("boobs", "info: " + x);
    	    			//if(x > 0) myChat.scrollTo(0, x);
    	    		}
    	    	}
    	    }
			
		} catch (JSONException e) {
			Log.e("boobs", "error98");
			e.printStackTrace();
		}
	}

//-------------------------------------------------------------------------------------------------------------------asyncGetMessages
    public class MyJsonGetMessages extends AsyncTask<Void, Void, Void>{
    	
    	HttpClient client;
    	HttpResponse response;
    	String myJson ="";
    	
    	@Override
    	protected void onPreExecute() {
    		
    		client = new DefaultHttpClient();
    		
    	} // End : onPreExecute 

    	@Override
    	protected Void doInBackground(Void... params) {
    		String URL = host + "?getnewmessages";
            HttpUriRequest request = new HttpGet(URL);
            try {
    			response = client.execute(request, localContext);	
    			InputStream content = response.getEntity().getContent();
    	          BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
    	          String s = "";
    	          while ((s = buffer.readLine()) != null) {
    	            myJson += s;
    	          }
            } catch (ClientProtocolException e1) {
            	Log.e("boobs", "error167");
                e1.printStackTrace();
            } catch (IOException e1) {
            	Log.e("boobs", "error170");
                e1.printStackTrace();
            }
    		return null;
    	}
    	
    	@Override
    	protected void onPostExecute(Void result) {
    		writeMessages(myJson);
        }

    }
    
//-------------------------------------------------------------------------------------------------------------------asyncLogin
    public class MyUrlJsonAsunc extends AsyncTask<String, Void, Void>{
    	
    	HttpClient client; 
    	HttpResponse response;
    	String myJson ="";
    	
    	@Override
    	protected void onPreExecute() {
    		client = new DefaultHttpClient();
    		cookieStore = new BasicCookieStore();
    		localContext = new BasicHttpContext();
    		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
    	} // End : onPreExecute 

    	@Override
    	protected Void doInBackground(String... param) {
    		String URL = host + "?login=" + param[0] + "&pass=" + param[1];
            HttpUriRequest request = new HttpGet(URL);
            try {
                //HttpClient client = null;
    			response = client.execute(request, localContext);
    			
    			InputStream content = response.getEntity().getContent();

    	          BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
    	          String s = "";
    	          //String data = "";
    	          while ((s = buffer.readLine()) != null) {
    	            myJson += s;
    	          }
    			
            } catch (ClientProtocolException e1) {
            	Log.e("boobs", "error160");
                e1.printStackTrace();
            } catch (IOException e1) {
            	Log.e("boobs", "error163");
                e1.printStackTrace();
            }
            
    		return null;
    	}
    	
    	@Override
    	protected void onPostExecute(Void result) {
    		
    		try {
				JSONObject jsonObj = new JSONObject(myJson);
				String errorMessage = jsonObj.getString("error");
				addMyRequest(errorMessage);
				Log.e("boobs", "errorMessage" + errorMessage);
			} catch (JSONException e1) {
				Log.e("boobs", "error177");
				e1.printStackTrace();
			}
    		
    		try {
				loginJson(myJson);
				Log.e("boobs", "success189");
				
			} catch (JSONException e) {
				Log.e("boobs", "error192");
				e.printStackTrace();
			}
    		
    		Log.e("boobs", "boobs");
            }

    }
    
//-------------------------------------------------------------------------------------------------------------------asyncMessageSend
    public class MyMsgSndJsonAsunc extends AsyncTask<String, Void, Void>{
    	
    	HttpClient client;
    	HttpResponse response;
    	String myJson = "";
    	
    	@Override
    	protected void onPreExecute() {
    		client = new DefaultHttpClient();
    	} // End : onPreExecute 

    	@Override
    	protected Void doInBackground(String... param) {
    		String URL = host + "?newmessage=" + param[0];
    		URL urlLink;
			try {
				urlLink = new URL(URL);
				URL = new URI("http", urlLink.getHost(), urlLink.getPath(), urlLink.getQuery(), null).toString();
			} catch (MalformedURLException e) {
				Log.e("boobs", "error330");
				e.printStackTrace();
			} catch (URISyntaxException e) {
				Log.e("boobs", "error333");
				e.printStackTrace();
			}
    		
    		Log.e("boobs", "send get: " + URL);
            HttpUriRequest request = new HttpGet(URL);
            try {
    			response = client.execute(request, localContext);
    			
    			/*InputStream content = response.getEntity().getContent();

    	          BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
    	          String s = "";
    	          while ((s = buffer.readLine()) != null) {
    	            myJson += s;
    	          }*/
    			
            } catch (ClientProtocolException e1) {
            	Log.e("boobs", "error229");
                e1.printStackTrace();
            } catch (IOException e1) {
            	Log.e("boobs", "error232");
                e1.printStackTrace();
            }
            //return "test";
    		return null;
    	}

    }
    
  //-------------------------------------------------------------------------------------------------------------------asyncRegistration
    public class MyRegJsonAsunc extends AsyncTask<String, Void, Void>{
    	
    	HttpClient client;
    	HttpResponse response;
    	String myJson = "";
    	
    	@Override
    	protected void onPreExecute() {
    		client = new DefaultHttpClient();
    	} // End : onPreExecute 

    	@Override
    	protected Void doInBackground(String... param) {
    		String URL = host + "?registration&rlogin=" + param[0] + "&rpass=" + param[1];
    		URL urlLink;
			try {
				urlLink = new URL(URL);
				URL = new URI("http", urlLink.getHost(), urlLink.getPath(), urlLink.getQuery(), null).toString();
			} catch (MalformedURLException e) {
				Log.e("boobs", "error330");
				e.printStackTrace();
			} catch (URISyntaxException e) {
				Log.e("boobs", "error333");
				e.printStackTrace();
			}
    		
    		Log.e("boobs", "send get: " + URL);
            HttpUriRequest request = new HttpGet(URL);
            try {
    			response = client.execute(request, localContext);
    			
    			InputStream content = response.getEntity().getContent();

    	          BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
    	          String s = "";
    	          while ((s = buffer.readLine()) != null) {
    	            myJson += s;
    	          }
    	          myRegInfo(myJson);
    			
            } catch (ClientProtocolException e1) {
            	Log.e("boobs", "error229");
                e1.printStackTrace();
            } catch (IOException e1) {
            	Log.e("boobs", "error232");
                e1.printStackTrace();
            } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //return "test";
    		return null;
    	}

    }

//-------------------------------------------------------------------------------------------------------------------registration
    public void registration() {
    	setContentView(R.layout.activity_registration);
    	OPTIONS_TYPE = 2;
    	
    	this.myButton = (Button)this.findViewById(R.id.button1);
    	this.myLogin    = (EditText)this.findViewById(R.id.editText1);
  	  	this.myPassword = (EditText)this.findViewById(R.id.editText2);
	  	this.myConfirm = (EditText)this.findViewById(R.id.editText3);
	  	this.myRegRequest  = (TextView)this.findViewById(R.id.textView1);
    	
    	this.myButton.setOnClickListener(new OnClickListener() {

    		public void onClick(View v) {
    			String logintext = myLogin.getText().toString();
    			String passtext  = myPassword.getText().toString();
    			String confirm  = myConfirm.getText().toString();
    			if(passtext.equals(confirm)) {
    				//myRequest.setText("Ok");
    				new MyRegJsonAsunc().execute(new String[] { logintext, passtext });
    			} else {
    				myRegRequest.setText("Password compare error");
    			}
    			//new MyUrlJsonAsunc().execute(new String[] { logintext, passtext });
              }
            });
    }
//-------------------------------------------------------------------------------------------------------------------login
    public void login() {
    	setContentView(R.layout.activity_login);
    	OPTIONS_TYPE = 0;
    	this.myImg      = (ImageButton)this.findViewById(R.id.imageButton1);
    	this.myLogin    = (EditText)this.findViewById(R.id.editText1);
  	  	this.myPassword = (EditText)this.findViewById(R.id.editText2);
        this.myRequest  = (TextView)this.findViewById(R.id.textView1);
        
        this.myImg.setOnClickListener(new OnClickListener() {

		public void onClick(View v) {
			String logintext = myLogin.getText().toString();
			String passtext  = myPassword.getText().toString(); 
			new MyUrlJsonAsunc().execute(new String[] { logintext, passtext });
          }
        });    
    }
//-------------------------------------------------------------------------------------------------------------------chat
    public void chat() {
    	setContentView(R.layout.activity_chat);
    	OPTIONS_TYPE = 1;
         
         listView = (ListView)findViewById(R.id.list);  //определяем наш ListView в main.xml 
         myBooks = new ArrayList<HashMap<String,Object>>();      //создаем массив списков
         
        adapter = new SimpleAdapter(this, 
                                                  myBooks, 
                                                  R.layout.list, new String[]{ // массив названий
                                                  BOOKKEY,         //верхний текст
                                                  PRICEKEY,        //нижний теккт
                                                  }, new int[]{    //массив форм
                                                  R.id.text1,      //наш id TextBox'a в list.xml
                                                  R.id.text2});    //ссылка на объект отображающий текст
                 
         listView.setAdapter(adapter);                         //говорим программе что бы отображала все объекты
    	
    	
    	
        this.myText = (EditText)this.findViewById(R.id.editText1);
        /*this.myChat = (TextView)this.findViewById(R.id.chat_area);
        myChat.setMovementMethod(ScrollingMovementMethod.getInstance());
        */
        this.myButton = (Button)this.findViewById(R.id.button1);
        
        //myChat.setMovementMethod(LinkMovementMethod.getInstance());
        
        myTimer = new Timer(); // Создаем таймер
        final Handler uiHandler = new Handler();
        aloop = true;
        myTimer.schedule(
        	new TimerTask() { // Определяем задачу
            @Override
            public void run() {
                uiHandler.post(new Runnable() {
                    public void run() {
                    	Log.d("boobs", "boooooooooooooobs");
                    	new MyJsonGetMessages().execute();
                    }
                });
            };
        }, 0L, 5000L); // интервал - 60000 миллисекунд, 0 миллисекунд до первого запуска.
        
        this.myButton.setOnClickListener(new OnClickListener() {
          public void onClick(View v) {
        	  
        	  
              
        	  String message = myText.getText().toString();
        	  Log.v("boobs", "send message: " + message);
        	  new MyMsgSndJsonAsunc().execute(new String[] { message });
        	  myText.setText("");
          }
        });
    }
    
//------------------------------------------------------------------------------------------------------------------------menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {

        menu.clear();

        if (OPTIONS_TYPE == 0) {
            getMenuInflater().inflate(R.menu.activity_login, menu);
        }
        if (OPTIONS_TYPE == 1) {
            getMenuInflater().inflate(R.menu.activity_chat, menu);
        }
        if (OPTIONS_TYPE == 2) {
            getMenuInflater().inflate(R.menu.activity_registration, menu);
        }

        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.quit:
            finish();
            return true;
        case R.id.registration:
            registration();
            return true;
        case R.id.back:
            login();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
//------------------------------------------------------------------------------------------------------------------------stop
    @Override
    public void onStop() {
    	if(aloop == true) {
    		myTimer.cancel();
    	}
    	
        super.onStop();
    }
//------------------------------------------------------------------------------------------------------------------------destroy    
    @Override
    public void onDestroy() {
    	if(aloop == true) {
    		myTimer.cancel();
    	}
        super.onDestroy();
    }
}

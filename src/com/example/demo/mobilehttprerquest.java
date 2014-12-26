package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class mobilehttprerquest extends AsyncTask<String, Void, String>  {

	public mobilehttprerquest() {
		// TODO Auto-generated constructor stub
	}
		
        @Override
        protected String doInBackground(String... urls) {
              
            // params comes from the execute() call: params[0] is the url.
            try {
                return getrqt(urls[0]); //postData(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            //textView.setText(result);
       }
    

        public String getrqt(String URL)throws ParseException, IOException 
        {
        	
        	  HttpClient httpclient = new DefaultHttpClient();
        	    HttpResponse response = httpclient.execute(new HttpGet(URL));
        	    StatusLine statusLine = response.getStatusLine();
        	    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
        	        ByteArrayOutputStream out = new ByteArrayOutputStream();
        	        response.getEntity().writeTo(out);
        	        out.close();
        	        String responseString1 = out.toString();
        	        return responseString1;
        	        //..more logic
        	    } else{
        	        //Closes the connection.
        	        response.getEntity().getContent().close();
        	        throw new IOException(statusLine.getReasonPhrase());
        	    }
        	    
        	   
        }
	
	public String postData(String URL) throws ParseException, IOException 
    {
    	String responseStr = "hello";
    	
    	// Create a new HttpClient and Post Header
//        HttpClient httpclient = new DefaultHttpClient();
//        HttpPost httppost = new HttpPost("http://198.20.168.145:8091/apicalls.asmx/UserSignup");
//        
//        
//            // Add your data
//         List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
//          nameValuePairs.add(new BasicNameValuePair("username", "para"));
//            nameValuePairs.add(new BasicNameValuePair("password", "para"));
//            nameValuePairs.add(new BasicNameValuePair("email", "param@gmail.com"));
//            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//            // Execute HTTP Post Request
//
//     HttpResponse  response = httpclient.execute(httppost);
//             responseStr = EntityUtils.toString(response.getEntity());
             
            
     
        
    	return responseStr;
    
    
    }
	
	public String test()
	{
		return "hello";
	}
}

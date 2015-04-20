package amigos;
import java.io.*;
import java.net.*;

public class List {
	private static String DEFAULTURL="http://localhost:54321/HtmlPruebas.html";
	
	public static String get(String webPage) {
	      URL url;
	      String line, output = "";
	      if(webPage.isEmpty()){
	    	  webPage=DEFAULTURL;
	      }
	      try {
	         url = new URL(webPage);
	         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = buffer.readLine()) != null) {
	            output=output+line;
	         }
	         buffer.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return output;
	   }

	   public static void main(String args[])
	   {
	     System.out.println(get(args[0]));
	   }
}

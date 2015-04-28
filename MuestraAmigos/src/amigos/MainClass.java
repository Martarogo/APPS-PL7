package amigos;

import org.apache.http.client.fluent.Request;

public class MainClass {
	private static String DEFAULTURL = "http://localhost:54321/api/amigo/";
	
	public static String listFriends(){
			try {
				return Request.Get(DEFAULTURL).execute().returnContent().asString();
			} catch (Exception e) {				
				e.printStackTrace();
				return "Error al obtener la lista de amigos";
			} 
	}
	
	public static String getFriend(int i){
		try {
			String url=DEFAULTURL+Integer.toString(i);
			return Request.Get(url).execute().returnContent().asString();
		} catch (Exception e) {				
			e.printStackTrace();
			return "Error al obtener la lista de amigos";
		} 
	}
	
	public static void main(String args[]) {
		System.out.println("Listado de amigos");
		System.out.println(listFriends());
		
		System.out.println("Marta (id 2)");
		System.out.println(getFriend(2));
	}
}

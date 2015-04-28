package amigos;

import org.apache.http.client.fluent.Request;

public class MainClass {
	private static String DEFAULTURL = "http://localhost:54321/api/amigo/";
	
	public static String listFriends(){
		String answer=null;
			try {
				answer=Request.Get(DEFAULTURL).execute().returnContent().asString();
			} catch (Exception e) {
				System.out.println("Error al obtener la lista de amigos");
				e.printStackTrace();
			} 
			return answer;
	}
	
	public static void main(String args[]) {
		System.out.println("Listado de amigos");
		System.out.println(listFriends());
	}
}
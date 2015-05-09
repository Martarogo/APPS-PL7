package amigos;

import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;


public class MainClass {
	private static String DEFAULTURL = "http://localhost:54321/api/amigo/";
	
	/**
	 * 
	 * @return A list of all subscribed friends in a Json code
	 */
	public static String listFriends(){
			try {
				return Request.Get(DEFAULTURL).execute().returnContent().asString();
			} catch (Exception e) {				
				e.printStackTrace();
				return "Error al obtener la lista de amigos";
			} 
	}
	
	/**
	 * 
	 * @param i ID of the selected friend
	 * @return Data of this friend
	 */
	public static String getFriend(int i){
		try {
			return Request.Get(DEFAULTURL+Integer.toString(i)).execute().returnContent().asString();
		} catch (Exception e) {				
			e.printStackTrace();
			return "Error al obtener la lista de amigos";
		} 
	}
	
	/**
	 * Updates the position of a registered member of Amigo's REST application
	 * @param id ID of the selected friend
	 * @param lati latitude of this friend's position
	 * @param longi longitude of this friend's position
	 * @return  State of the opperation 
	 */
	public static String updateInfo(int id, int lati, int longi){
		String info=addInfo(id,lati,longi);
		Request put=Request.Put(DEFAULTURL+Integer.toString(id)).bodyString(info, ContentType.create("application/x-www-form-urlencoded"));
		System.out.println(put.toString());
		try {
			if(put.execute().returnResponse().getStatusLine().getStatusCode()==HttpStatus.SC_NO_CONTENT){
				return "Done!";
			}else{
				return "Error: Server could not update "+id+"'s possition.";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		} 
	}
	/**
	 * @param id Amigo's identifier-int
	 * @param lati Amigo's latitude-int
	 * @param longi Amigo's longitude-int
	 * @return
	 */
	private static String addInfo(int id, int lati, int longi) {
		return "ID="+Integer.toString(id)+"&lati="+Integer.toString(lati)+"&longi="+Integer.toString(longi);
	}

	public static void main(String args[]) {
		System.out.println("Listado de amigos");
		System.out.println(listFriends());
		
		System.out.println("Marta (id 2)");
		System.out.println(getFriend(2));
		
		System.out.println("Actualiza Marta");
		System.out.println(updateInfo(2,4,5));
		System.out.println(getFriend(2));
		
	}
}

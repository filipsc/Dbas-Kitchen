
/**
 * @author Filip
 *
 */

public class KitchenReader{

	/**
	 * Filip, jag tänkte såhär: Main ligger nu i GUI_Handler, 
	 * KitchenReader är en klass för att handskas med input från GUI:n
	 * till databasen. Funkar? 
	 */
	private SQLiteJDBC kitchendb;

	/**
	 * @param args
	 */
	
	public KitchenReader(){
		SQLiteJDBC kitchendb = new SQLiteJDBC("kitchen");
	}
	
}

/**
 * @author Filip
 *
 */

public class KitchenReader{

	/**
	 * Filip, jag t�nkte s�h�r: Main ligger nu i GUI_Handler, 
	 * KitchenReader �r en klass f�r att handskas med input fr�n GUI:n
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
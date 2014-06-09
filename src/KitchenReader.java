
/**
 * A parser which also starts the program.
 */
public class KitchenReader{
	SQLiteJDBC kitchendb; 
	
	/**
	 * A parser which also starts the program.
	 */

	public static void main(String[] args){
		SQLiteJDBC kitchendb = new SQLiteJDBC("mainkitchen");
		
		//Testing
		kitchendb.insertKitchen("Kitchen3"); 
		kitchendb.insertIngredient("DEFAULT", "X", "Kitchen3");
		
		 
		
	}
	
}
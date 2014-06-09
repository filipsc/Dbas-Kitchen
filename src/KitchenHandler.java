
/**
 * In the future the info sent to and received from might be handled in this class to structure the text.
 * 
 * @author Filip
 */
public class KitchenHandler {
	
	//will this be the new databasehandler?
	private SQLiteJDBC kitchendb;
	//
	private String kitName;
	
	public KitchenHandler(String kitchenName){
		kitName = kitchenName;
		kitchendb = new SQLiteJDBC(kitName);
	}
	
	/**
	 * Capability: insert new ingredients in a kitchen
	 * 
	 * @param ingName
	 * @param unit
	 */
	public void newIngredient(String ingName, String unit){
		kitchendb.insertIngredient(ingName, unit, kitName);
	}
	
	/**
	 * Capability: Ability to change the stock of an ingredient.
	 * 
	 * @param ingName
	 * @param amount
	 */
	public void changeStock(String ingName, int amount){
		kitchendb.changeIngStoreBy(kitName, ingName, amount);
	}
	
	/**
	 * Capability: being able to check if there is enough stock for a recipe
	 * 
	 * @param recipe
	 * @return "Not enough", "Enough" or "Possibly enough"
	 */
	public String stockForRecipe(String recipe){
		return "";
	}
	
	/**
	 * Capability: tell the database that a recipe has been made and alter the stock
	 * 
	 * @param recipe
	 */
	public void makeRecipe(String recipe){
		
	}
	
	/**
	 * Capability: generate a shopping list so that we can make all the recipes in the list
	 * 
	 * @param recipeList, the list of recipes
	 * @return
	 */
	public String shoppingList(String[] recipeList){
		return "";
	}
}

import java.util.ArrayList;

/**
 * In the future the info sent to and received from might be handled in this class to structure the text.
 * 
 * @author Filip
 */
public class KitchenHandler {
	
	//will this be the new databasehandler?
	private SQLiteJDBC kitchendb;
	
	public KitchenHandler(String dbname){
		kitchendb = new SQLiteJDBC(dbname);
	}
	
	/**
	 * Capability: insert new ingredients in a kitchen
	 * 
	 * @param ingName
	 * @param unit
	 */
	public String newIngredient(String ingName, String unit, int amount){
		return kitchendb.insertIngredient(ingName, unit, amount);
	}
	
	public String deleteIngredient(String ingName){
		return kitchendb.deleteIngredient(ingName); 
	}
	
	/**
	 * Capability: Ability to change the stock of an ingredient.
	 * 
	 * @param ingName
	 * @param amount
	 */
	public void changeStock(String ingName, int amount){
		kitchendb.changeIngStoreBy(ingName, amount);
	}
	
	/**
	 * Capability: list the food stock with quantities
	 * 
	 * @return stocklist, a list of all the stock available
	 */
	public String listStock(){
		StringBuilder lister = new StringBuilder();
		ArrayList<String> ingList = kitchendb.listIngredients();
		for(int i = 0; i < ingList.size(); i++){
			String element = ingList.get(i) + kitchendb.getIngredientStock(ingList.get(0)) + "\n";
			lister.append(element);
		}
		return lister.toString();
	}
	
	/**
	 * Capability: being able to check if there is enough stock for a recipe
	 * 
	 * @param recipe
	 * @return "Not enough", "Enough" or "Possibly enough" for each, or "allenough" if all are enough.
	 */
	public String stockForRecipe(String recipe){
		StringBuilder answer = new StringBuilder();
		ArrayList<String> ingList = kitchendb.getRecipeIngredients(recipe);
		boolean allenough = true;
		
		for(int i = 0; i < ingList.size(); i++){
			int exist = kitchendb.getIngredientStock(ingList.get(i));
			int need = kitchendb.neededIngAmount(recipe, ingList.get(i));
			if(exist == -1){	//we will let -1 mean that the quantity is unknown, or will we?
				String info = "Possibly enough " + ingList.get(i) + "\n";
				answer.append(info);
				allenough = false;
			}else if(exist >= need){
				String info = "Enough " + ingList.get(i) + "\n";
				answer.append(info);
			}else{	//exist < need
				String info = "Not enough " + ingList.get(i) + "\n";
				answer.append(info);
				allenough = false;
			}
		}
		
		if(allenough){
			return "allenough";
		}else{
			return answer.toString();
		}
	}
	
	/**
	 * Capability: tell the database that a recipe has been made and alter the stock
	 * 
	 * Maybe make return type boolean?
	 * @param recipe
	 */
	public void makeRecipe(String recipe){
		if(this.stockForRecipe(recipe).equals("allenough")){
			ArrayList<String> ingList = kitchendb.getRecipeIngredients(recipe);
			for(int i = 0; i < ingList.size(); i++){
				int need = kitchendb.neededIngAmount(recipe, ingList.get(i));
				kitchendb.changeIngStoreBy(ingList.get(i), (- need));
				System.out.println(recipe + " made!");
			}
		}else{
			System.out.println("We are not sure that we have enough ingredients and made nothing");
		}
	}
	
	/**
	 * Capability: generate a shopping list so that we can make all the recipes in the list
	 * 
	 * @param recipeList, the list of recipes
	 * @return
	 */
	public String shoppingList(String[] recipeList){
		StringBuilder shopping = new StringBuilder();
		
		/*KOMPIS ALLT BORTKOMMENTERAT HÄR KAN VARA ONÖDIGT, TA DET IMORRN
		//Create a multiple-recipe ingredient-list
		//Also create a needlist for all those ingredients
		ArrayList<String> ingList = new ArrayList<String>();
		ArrayList<Integer> needList = new ArrayList<Integer>();
		for(int i = 0; i < recipeList.length; i++){
			ArrayList<String> theseIngs = kitchendb.getRecipeIngredients(recipeList[i]);
			for(int j = 0; j < theseIngs.size(); j++){	//not yet used ingredient
				if(!ingList.contains(theseIngs.get(j))){
					int need = kitchendb.neededIngAmount(recipeList[i], theseIngs.get(j));
					needList.add(new Integer(need));
				}else{	//ingredient used by other recipe
					ingList.add(theseIngs.get(j));
					int need = kitchendb.neededIngAmount(recipeList[i], theseIngs.get(j));
					needList.add(new Integer(need));
				}
			}
		}
		
		//Start copy-paste
		
		boolean allenough = true;
		for(int i = 0; i < ingList.size(); i++){
			int exist = kitchendb.getIngredientStock(ingList.get(i), kitName);
			int need = kitchendb.neededIngAmount(recipe, ingList.get(i));
			if(exist == -1){	//we will let -1 mean that the quantity is unknown, or will we?
				String info = "Possibly enough " + ingList.get(i) + "\n";
				answer.append(info);
				allenough = false;
			}else if(exist >= need){
				String info = "Enough " + ingList.get(i) + "\n";
				answer.append(info);
			}else{	//exist < need
				String info = "Not enough " + ingList.get(i) + "\n";
				answer.append(info);
				allenough = false;
			}
		}
		//End copy-paste
		 * 
		 */
		
		return shopping.toString();
	}
}

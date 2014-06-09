import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.Integer;

/**
 * A parser which also starts the program.
 */
public class KitchenReader{

	public static void main(String[] args){
		//String kitName	= "mainkitchen";	//moved to kitchenhandler
		//SQLiteJDBC kitchendb = new SQLiteJDBC(kitName);	//moved to kitchenhandler
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		KitchenHandler kitchen = new KitchenHandler("mainkitchen");
		
		//Testing
		//kitchendb.insertKitchen("Kitchen3"); 
		//kitchendb.insertIngredient("DEFAULT", "X", "Kitchen3");
		
		boolean running = true;
		while(running){
			System.out.print(">");
			String input ="";
			try{
				input = reader.readLine();
			}catch(IOException ie){
				ie.printStackTrace();
				System.out.println("Failed at reading line");
			}
			input = input.toLowerCase();
			String[] words = input.split(",");
			
			System.out.print(">");
			if(words[0].equals("exit")){
				running = false;
			}else if(words[0].equals("add ingredient")){
				kitchen.newIngredient(words[1], words[2]);
				System.out.println("Ingredient " + words[1] + " added.");
			}else if(words[0].equals("change stock")){
				kitchen.changeStock(words[1], new Integer(words[2]).intValue());
				System.out.println("Stock for" + words[1] + " changed.");
			}else if(words[0].equals("list stock")){
				String list = kitchen.listStock();
			}else if(words[0].equals("stock for")){
				String needed = kitchen.stockForRecipe(words[1]);
				System.out.println(needed);
			}else if(words[0].equals("make")){
				kitchen.makeRecipe(words[1]);
				System.out.println("Recipe " + words[1] + " made.");
			}else if(words[0].equals("shop for")){
				String[] recipes = new String[words.length-2];
				for(int i = 1; i < words.length; i++){
					recipes[i - 1] = words[i];
				}
				String shoppingList = kitchen.shoppingList(recipes);
				System.out.println(shoppingList);
			}else{
				System.out.println("Expression not recognized.");
			}
			
		}
		System.out.println("Exiting program");
	}
}
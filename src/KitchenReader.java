import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * A parser which also starts the program.
 */
public class KitchenReader{

	public static void main(String[] args){
		String kitName	= "mainkitchen";
		SQLiteJDBC kitchendb = new SQLiteJDBC(kitName);
		Scanner reader = new Scanner(new InputStreamReader(System.in));
		
		//Testing
		kitchendb.insertKitchen("Kitchen3"); 
		kitchendb.insertIngredient("DEFAULT", "X", "Kitchen3");
		
		boolean running = true;
		while(running){
			System.out.print(">");
			String input = "";
			input = reader.next();
			if(input.equals("exit")){
				running = false;
			}else if(input.equals("addIng")){
				String ingName = reader.next();
				String unit = reader.next();
				kitchendb.insertIngredient(ingName, unit, kitName);
			}else if(input.equals("print")){
				System.out.println(reader.next());
			}else{
				System.out.println("Expression not recognized.");
			}
			
		}
		reader.close();
		System.out.println("Exiting program");
	}
}
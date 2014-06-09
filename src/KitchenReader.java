import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * A parser which also starts the program.
 */
public class KitchenReader{

	public static void main(String[] args){
		String kitName	= "mainkitchen";
		SQLiteJDBC kitchendb = new SQLiteJDBC(kitName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		//Testing
		kitchendb.insertKitchen("Kitchen3"); 
		kitchendb.insertIngredient("DEFAULT", "X", "Kitchen3");
		
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
				kitchendb.insertIngredient(words[1], words[2], kitName);
			}else{
				System.out.println("Expression not recognized.");
			}
			
		}
		System.out.println("Exiting program");
	}
}
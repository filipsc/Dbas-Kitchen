import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * A parser which also starts the program.
 */
public class KitchenReader{

	public static void main(String[] args){
		SQLiteJDBC kitchendb = new SQLiteJDBC("mainkitchen");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		//Testing
		kitchendb.insertKitchen("Kitchen3"); 
		kitchendb.insertIngredient("DEFAULT", "X", "Kitchen3");
		
		boolean running = true;
		while(running){
			String input = "";
			try{
				input = reader.readLine();
			}catch(IOException ie){
				ie.printStackTrace();
				System.out.println("Failed at reading Line");
			}
			if(input.equals("exit")){
				running = false;
				System.out.println("Exiting program, bye!");
			}else{
				System.out.println("Expression not recognized.");
			}
		}
		
	}
	
}
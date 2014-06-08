import java.sql.*;

/**
 * The class controlling the database.
 * 
 * @author Filip Schulze, Eva Sokolova
 */
public class SQLiteJDBC {

	// keeping track of the name of the database
	private String dbname;
	// keeping track of the connection to the database
	private Connection mainConnection;
	
	/**
	 * Constructor for SQLiteJDBC-class. Constructs an object which keeps track 
	 * 
	 * @param dbname The desired name for the database
	 */
	public SQLiteJDBC(String dbname){
		this.dbname = dbname + ".db";
		String connectionName = "jdbc:sqlite:" + this.dbname;
		mainConnection = null;
		try {	// try to initialize and connect to a database or if it already exists just connect to it
	      Class.forName("org.sqlite.JDBC");
	      mainConnection = DriverManager.getConnection(connectionName);
	      
	   // add a check here to see if the tables are already initialized?
	   // Fixed that for ya hun'. 
	      getTables(); 
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
		
		this.createKitchenDB();
	}
	
	/**
	 * Initialize the wanted tables in the kitchen database.
	 */
	private void createKitchenDB(){
		Statement stmt;
		try{	//ingredient table			
			
			stmt = mainConnection.createStatement();
		
			//Checking if table exists.
			DatabaseMetaData metadata = mainConnection.getMetaData();
			ResultSet resultSet;
			resultSet = metadata.getTables(dbname, null, "INGREDIENT", null);
			if(resultSet.next()){
				System.out.println("Table " + resultSet.getString("TABLE_NAME") + " already exists.");
			}
			else{		
			String sql = "CREATE TABLE INGREDIENT " +
	                   "(NAME	TEXT	PRIMARY KEY	NOT NULL, " +
	                   " UNIT	TEXT	NOT NULL," +
	                   " STORAGE	TEXT	NOT NULL)"; 
			stmt.executeUpdate(sql);
			}
			resultSet.close();
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed at creating table INGREDIENT."); 
		}
		try{	//recipe table
			stmt = mainConnection.createStatement();
			
			//Checking if table exists.
			DatabaseMetaData metadata = mainConnection.getMetaData();
			ResultSet resultSet;
			resultSet = metadata.getTables(dbname, null, "RECIPE", null);
			if(resultSet.next()){
				System.out.println("Table " + resultSet.getString("TABLE_NAME") + " already exists."); 
			}
			else{
			String sql = "CREATE TABLE RECIPE " +
	                   "(NAME	TEXT	PRIMARY KEY	NOT NULL, " +
	                   " TYPE	TEXT," +
	                   " DESCRIPTION	TEXT)"; 
			stmt.executeUpdate(sql);
			}
			resultSet.close();
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed at creating table RECIPE.");
		}
		try{	//kitchen table
			stmt = mainConnection.createStatement();
			
			//Checking if table exists.
			DatabaseMetaData metadata = mainConnection.getMetaData();
			ResultSet resultSet;
			resultSet = metadata.getTables(dbname, null, "KITCHEN", null);
			if(resultSet.next()){
				System.out.println("Table " + resultSet.getString("TABLE_NAME") + " already exists."); 
			}
			else{
			String sql = "CREATE TABLE KITCHEN " +
	                   "(NAME	TEXT	PRIMARY KEY	NOT NULL)"; 
			stmt.executeUpdate(sql);
			}
			resultSet.close();
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed at creating table KITCHEN.");
		}
		try{	//usedIn table
			stmt = mainConnection.createStatement();
			
			//Checking if table exists.
			DatabaseMetaData metadata = mainConnection.getMetaData();
			ResultSet resultSet;
			resultSet = metadata.getTables(dbname, null, "USEDIN", null);
			if(resultSet.next()){
				System.out.println("Table " + resultSet.getString("TABLE_NAME") + " already exists."); 
			}
			else{
			String sql = "CREATE TABLE USEDIN " +
	                   "(INGREDIENTNAME	TEXT," +
	                   " RECIPENAME	TEXT," +
	                   " AMOUNT	INT NOT NULL," +
	                   "CONSTRAINT INGREDIENT_NAME FOREIGN KEY(INGREDIENTNAME) REFERENCES INGREDIENT(NAME)," +
	                   "CONSTRAINT RECIPE_NAME FOREIGN KEY(RECIPENAME) REFERENCES KITCHEN(NAME))"; 
			stmt.executeUpdate(sql);
			}
			resultSet.close();
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed at creating table USEDIN.");
		}
		try{	//presentIn table
			stmt = mainConnection.createStatement();
			//Checking if table exists.
			DatabaseMetaData metadata = mainConnection.getMetaData();
			ResultSet resultSet;
			resultSet = metadata.getTables(dbname, null, "PRESENTIN", null);
			if(resultSet.next()){
				System.out.println("Table " + resultSet.getString("TABLE_NAME") + " already exists."); 
			}
			else{
			String sql = "CREATE TABLE PRESENTIN " +
	                   "(KITCHENNAME TEXT," +
	                   " INGREDIENTNAME	TEXT," +
	                   " AMOUNT	INT	NOT NULL," +
	                   "CONSTRAINT KITCHEN_NAME FOREIGN KEY(KITCHENNAME) REFERENCES KITCHEN(NAME))"; 
			stmt.executeUpdate(sql);
			}
			resultSet.close();
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed at creating table PRESENTIN.");
		}
	}
	
	/**
	 * Insert a new ingredient into the Ingredient-table
	 * 
	 * @param name	what the ingredient is called
	 * @param unit	the unit that the ingredients are measured in
	 */
	public void insertIngredient(String name, String unit){
		try{
			Statement stmt = mainConnection.createStatement();
			String sql = "INSERT IGNORE INTO INGREDIENT (NAME UNIT) " +
					"VALUES ('" + name + "', '" + unit +"')";
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			System.out.println("Crashed at inserting ingredient");
			System.exit(0);
		}
	}
	
	/**
	 * Insert a new kitchen into the database
	 * 
	 * @param kitchenName , the name will be the key for the kitchen 
	 */
	public void insertKitchen(String kitchenName){
		try{
			Statement stmt = mainConnection.createStatement();
			String sql = "INSERT IGNORE INTO INGREDIENT (NAME) " +
					"VALUES ('" + kitchenName + "')";
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			System.out.println("Crashed at inserting kitchen");
			System.exit(0);
		}
	}
	
	/**
	 * @param kitName, the kitchen to change in
	 * @param ingName, the ingredient to be changed
	 * @param changeAmount, the amount to give or take from the storage
	 */
	public void changeIngStoreBy(String kitName, String ingName, int changeAmount){
		int existingAmount = 0;
		try{	//get the current amount
			Statement stmt = mainConnection.createStatement();
			String sql = "SELECT AMOUNT " +
					"FROM USEDIN " +
					"WHERE PRESENTIN.INGREDIENTNAME='" + ingName + "' AND PRESENTIN.KITCHENNAME='" + kitName + "'";
			ResultSet rs = stmt.executeQuery(sql);
			existingAmount = rs.getInt("AMOUNT");
		}catch(Exception e){
			System.out.println("Unable to find that ingredient");
			System.exit(0);
		}
		int newAmount = changeAmount + existingAmount;
		try{	// enter the updated amount
			Statement stmt = mainConnection.createStatement();
			String sql = "UPDATE PRESENTIN " +
					"SET AMOUNT='" + newAmount +"' " +
					"WHERE PRESENTIN.NAME='" + ingName + "' AND PRESENTIN.KITCHENNAME='" + kitName + "'";
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed to update that ingredient");
			System.exit(0);
		}
	}
	
	/**
	 * @param recName, the name of the recipe to be changed
	 * @param ingName, the name of the ingredient to be changed
	 * @param changeAmount, the new amount that should be used by the recipe
	 */
	public void setIngUsedIn(String recName, String ingName, int newAmount){
		try{	// enter the updated amount
			Statement stmt = mainConnection.createStatement();
			String sql = "UPDATE USEDIN " +
					"SET AMOUNT='" + newAmount +"' " +
					"WHERE USEDIN.NAME='" + ingName + "' AND USEDIN.RECIPE ='" + recName + "'";
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed to update that ingredient");
			System.exit(0);
		}
	}
	
	/*
	 * Check for existing tables
	 */
	public void getTables(){
		try{
	      DatabaseMetaData dbm = mainConnection.getMetaData();
	      ResultSet res = dbm.getTables(dbname, null, null, 
	    	         new String[] {"TABLE"});
	      if (!res.isBeforeFirst() ) {    
	    	  System.out.println("No data"); 
	    	 } 
	      else{
	    	      System.out.println("List of tables: "); 
	    	      while (res.next()) {
	    	         System.out.println(
	    	            "   "+res.getString("TABLE_CAT") 
	    	           + ", "+res.getString("TABLE_SCHEM")
	    	           + ", "+res.getString("TABLE_NAME")
	    	           + ", "+res.getString("TABLE_TYPE")
	    	           + ", "+res.getString("REMARKS")); 
	    	      }
	    	      res.close();
	      		}
		}
		catch(Exception e){
			System.out.println("Failed to find tables."); 
		}
	}
	
	/**
	 * @return mainConnection of the SQLiteJDBC
	 */
	public Connection getConnection(){
		return mainConnection;
	}
	
	/**
	 * NOT GOING TO BE USED
	 * @param name
	 * @param type
	 * @param description
	public void insertRecipe(String name, String type, String description){
		Statement stmt;
		try{	//recipe table
			stmt = mainConnection.createStatement();
			String sql = "INSERT IGNORE INTO RECIPE (NAME, TYPE, DESCRIPTION)" +
						" VALUES (" + name + "," + type+ "," + description + ")"; 
			stmt.executeUpdate(sql); 
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed at inserting recipe. Perhaps this recipe is already in database.");
		}
	}*/
}

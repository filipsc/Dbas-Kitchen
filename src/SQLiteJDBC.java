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
			String sql = "CREATE TABLE INGREDIENT " +
	                   "(NAME	TEXT	PRIMARY KEY	NOT NULL, " +
	                   " UNIT	TEXT	NOT NULL," +
	                   " STORAGE	TEXT	NOT NULL)"; 
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed at creating ingredient");
		}
		try{	//recipe table
			stmt = mainConnection.createStatement();
			String sql = "CREATE TABLE RECIPE " +
	                   "(NAME	TEXT	PRIMARY KEY	NOT NULL, " +
	                   " TYPE	TEXT," +
	                   " DESCRIPTION	TEXT)"; 
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed at creating recipe");
		}
		try{	//kitchen table
			stmt = mainConnection.createStatement();
			String sql = "CREATE TABLE KTICHEN " +
	                   "(NAME	TEXT	PRIMARY KEY	NOT NULL)"; 
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed at creating kitchen");
		}
		try{	//usedIn table
			stmt = mainConnection.createStatement();
			String sql = "CREATE TABLE USEDIN " +
	                   "(INGREDIENTNAME	TEXT	FOREIGN KEY REFERENCES INGREDIENT(NAME), " +
	                   " RECIPENAME	TEXT FOREIGN KEY REFERENCES KITCHEN(NAME)," +
	                   " AMOUNT	INT NOT NULL)"; 
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed at creating usedin");
		}
		try{	//presentIn table
			stmt = mainConnection.createStatement();
			String sql = "CREATE TABLE PRESENTIN " +
	                   "(KITCHENNAME	TEXT	FOREIGN KEY REFERENCES KITCHEN(NAME), " +
	                   " INGREDIENTNAME	TEXT	FOREIGN KEY REFERENCES INGREDIENT(NAME)," +
	                   " AMOUNT	INT	NOT NULL"; 
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			System.out.println("Failed at creating presentin");
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
			String sql = "INSERT INTO INGREDIENT (NAME UNIT) " +
					"VALUES ('" + name + "', '" + unit +"')";
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			System.out.println("Crashed at inserting ingredient");
			System.exit(0);
		}
	}
	
	/**
	 * @return mainConnection of the SQLiteJDBC
	 */
	public Connection getConnection(){
		return mainConnection;
	}
}

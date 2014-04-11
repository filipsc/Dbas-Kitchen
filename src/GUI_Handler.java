 

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GUI_Handler extends BasicGame {

	public GUI_Handler(){
		super("Dbas-kitchen"); 
	}
	
	public static void main(String[] args){
		try{
		KitchenReader kitchen = new KitchenReader(); 
		GUI_Handler gui = new GUI_Handler(); 
		AppGameContainer container = new AppGameContainer(gui);
		container.setDisplayMode(640, 480, false); 
		container.start();
		}
		catch (SlickException ex){
		}
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}	
}
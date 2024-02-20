package structures.basic;
import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.Avatar;
import java.util.ArrayList;

public class AI extends Player  {
	private ArrayList<Class<? extends Card>> hand = new ArrayList<>();
	private ArrayList<Class<? extends Card>> discardPile = new ArrayList<>();
	private int health;
	private int mana;
    private boolean userOwned;
    private Hand handObject;
    
	public AI(int health, int mana) {
		super(health, mana);
	}
	
	public AI(boolean userOwned) {
		super(userOwned);
	}
	
	public void aiMoved(ActorRef out, Avatar avatar, Tile tile) {
		BasicCommands.moveUnitToTile(out, avatar.getUnit(), tile);
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
	}

	

}

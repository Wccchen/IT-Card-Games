package structures.basic;
import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.Avatar;
import java.util.ArrayList;

public class AI extends Player implements MoveableUnit {
	private ArrayList<Class<? extends Card>> hand = new ArrayList<>();
	private ArrayList<Class<? extends Card>> discardPile = new ArrayList<>();
	private int health;
	private int mana;
    private boolean userOwned;
    
	public AI(int health, int mana) {
		super(health, mana);
	}
	
	public AI(boolean userOwned) {
		super(userOwned);
	}
	public void aiMoved(ActorRef out, Unit unit, Tile tile ) {
		BasicCommands.moveUnitToTile(out, unit, tile);
		
	}

	@Override
	public void attackUnit(ActorRef out, Tile tile, GameState gameState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveUnit(ActorRef out, Tile tile, GameState gameState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxHealth(int maxHealth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCurrentHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCurrentHealth(int currentHealth, ActorRef out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAttack(int attack, ActorRef out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTurnSummoned() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTurnSummoned(int turnSummoned) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLastTurnMoved() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLastTurnMoved(int lastTurnMoved) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Unit getUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUnit(Unit unit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionableTiles(ActorRef out, GameState gameState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tile getTile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTile(Tile tile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLastTurnAttacked() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLastTurnAttacked(int lastTurnAttacked) {
		// TODO Auto-generated method stub
		
	}
	

	

}

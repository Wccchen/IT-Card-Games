package structures.basic;

import akka.actor.ActorRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.MoveableUnit;
import structures.basic.Player;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;
import utils.UnitCommands;

public class Avatar implements MoveableUnit {
    private int maxHealth;
    private int currentHealth;
    private int attack;
    private int turnSummoned;
    private int lastTurnMoved;
	private Unit unit;
    private Player player;
	private boolean userOwned;
	@JsonIgnore
	private Tile tile;
	private int lastTurnAttacked;

	public Avatar(Player player) {
		this.player = player;
		this.maxHealth = player.getHealth();
		this.currentHealth = maxHealth;
		this.attack = 2;
		this.turnSummoned = 0;
		this.lastTurnMoved = 0;
		this.userOwned = player.isUserOwned();
		if (this.userOwned){ //if human
			this.unit = BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 1000, Unit.class); //need to update
		}else { //if AI
			this.unit = BasicObjectBuilders.loadUnit(StaticConfFiles.aiAvatar, 1001, Unit.class); //need to update
		}
	}



	@Override
	public void attackUnit( ActorRef out, Tile tile, GameState gameState) {
		UnitCommands.attackUnit(this, out, tile, gameState);
	}



	@Override
	public void moveUnit(ActorRef out, Tile tile, GameState gameState) {
		UnitCommands.moveUnit(this,out,tile,gameState);
	}


	@Override
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		
	}

	@Override
	public int getCurrentHealth() {
		return this.currentHealth;
	}

	@Override
	public void setCurrentHealth(int currentHealth, ActorRef out) {
		this.currentHealth = currentHealth;
		BasicCommands.setUnitHealth(out, this.unit,this.currentHealth); //renders on front end
		this.player.setHealth(this.currentHealth, out); // to set player health when avatar takes dmg
		
	}

	@Override
	public int getAttack() {

		return this.attack;
	}

	@Override
	public void setAttack(int attack, ActorRef out) {
		this.attack = attack;
		BasicCommands.setUnitAttack(out,this.unit,this.attack); //renders on front end
		
	}

	@Override
	public int getTurnSummoned() {

		return this.turnSummoned;
	}

	@Override
	public void setTurnSummoned(int turnSummoned) {
		this.turnSummoned = turnSummoned;
		
	}

	@Override
	public int getLastTurnMoved() {
		return this.lastTurnMoved;
	}

	@Override
	public void setLastTurnMoved(int lastTurnMoved) {
		this.lastTurnMoved = lastTurnMoved;
		
	}

	@Override
	public Unit getUnit() {
		return this.unit;
	}

	@Override
	public void setUnit(Unit unit) {
		this.unit = unit;
		
	}

	@Override
	public boolean isUserOwned() {
		return this.userOwned;
	}

	@Override
	public void setUserOwned(boolean userOwned) {
		this.userOwned = userOwned;
		
	}



	@Override
	public int getMaxHealth() {
		return this.maxHealth;
	}

	public void actionableTiles(ActorRef out, GameState gameState){
		UnitCommands.actionableTiles(this, out, gameState);
	}
	public Tile getTile() {
		return this.tile;
	}
	
	public void setTile(Tile tile) {
		 this.tile = tile;
	}

	@Override
	public int getLastTurnAttacked() {
		return this.lastTurnAttacked;
	}

	@Override
	public void setLastTurnAttacked(int lastTurnAttacked) {
		this.lastTurnAttacked=lastTurnAttacked;
	}


}

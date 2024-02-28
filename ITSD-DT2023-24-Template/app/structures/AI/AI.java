package structures.AI;
import akka.actor.ActorRef;
import structures.GameState;
import structures.basic.*;
import utils.UnitCommands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AI extends Player {
	private GameState gameState;
	private ActorRef actorRef;
	private ArrayList<MoveableUnit> allUnits;

	public AI(boolean userOwned, GameState gameState) {
		super(userOwned);
		this.gameState = gameState;

	}

	public static void main(String[] args) {

		int number = 15;
		System.out.println(number);
		number = (int) (number *0.8);
		System.out.println(number);


	}

	public void setActorRef(ActorRef out){
		this.actorRef = out;
	}

	public void makeActions(){
		while(this.hasActions()){
			summonUnit();
			System.out.println("attempting to summon");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean hasActions(){ //expand class as functionality increases
		for (Card card:this.hand) {
			if (card instanceof Creature && card.getManacost() <= this.mana) { //has enough mana for summoning
				System.out.println("AI has actions remaining");
				return true;

			}
		}
		System.out.println("AI has no actions remaining");
		return false;

	}

	public static double calculateDistance(Tile startingTile, Tile targetTile){ //method for calculating distance between two tiles. multipurpose.
		double xDistance = startingTile.getTilex() - targetTile.getTilex();
		double yDistance = startingTile.getTiley() - targetTile.getTiley();
		double distance = Math.sqrt((xDistance * xDistance) + (yDistance*yDistance)); //pythagorean theorem for calculating distance to tile;
		return distance;

	}

	public void summonUnit(){
		/*
		if unit has health>attack, much higher chance of choosing tiles closer to AI avatar,
		if unit has attack<health, much higher chance of choosing tiles closer to player avatar.
		if unit has attack == health , any tile
		 */
		Creature creature = this.returnBestCreature();
		if (this.returnBestCreature().getCardname().equals("no creature")){
			return;
		}

		List<Tile> possibleTiles = this.tilesForAIUnitSummons();
//		int numberOfTiles = possibleTiles.size();
//		ArrayList<Tile>weightedTiles = new ArrayList<>(); // list for putting weighted array list
//		if (creature.getMaxHealth()>creature.getAttack()){
//			for (int i = 0; i<possibleTiles.size();i++){
//				for (int j = numberOfTiles; j<=1; j--){
//					weightedTiles.add(possibleTiles.get(i));
//				} numberOfTiles--;
//			}
//		} else if (creature.getAttack()> creature.getMaxHealth()) {
//			for (int i = possibleTiles.size()-1; i>=0;i--){
//				for (int j = 1; j<=numberOfTiles; j--){
//					weightedTiles.add(possibleTiles.get(i));
//				} numberOfTiles --;
//			}
//		} else{ //health is equal to attack
//			//do nothing since all tiles have an equal chance
//			weightedTiles.addAll(possibleTiles);
//		}

		if (creature.getMaxHealth()>creature.getAttack()){
			System.out.println("defensive placement since unit has more attack than health");
			int cutoffPoint = (int) (possibleTiles.size()*0.5);
			System.out.println("Cutoff is " + cutoffPoint);
			if (cutoffPoint == 0){
				cutoffPoint =1; //prevents list from being null
			}
			System.out.println(possibleTiles.size() + " is list size before cutoff");
			possibleTiles = possibleTiles.subList(0,cutoffPoint);
			System.out.println(possibleTiles.size() + " is list size after cutoff");
			//takes bottom % of possibleTiles that are closer to the AI avatar so defensive units are more likely to be summoned in a defensive position
		} else if (creature.getAttack()> creature.getMaxHealth()) {
			System.out.println("offensive placement");
			int cutoffPoint = (int)(possibleTiles.size()*0.5);
			System.out.println("Cutoff is " + cutoffPoint);
			System.out.println(possibleTiles.size() + " is list size before cutoff");
			possibleTiles =  possibleTiles.subList(cutoffPoint, possibleTiles.size());
			System.out.println(possibleTiles.size() + " is list size after cutoff");
			//takes top % of possibleTiles that are further from the AI avatar so offensive units are more likely to be summoned in an offensive position
		} else{ //health is equal to attack
			//no changes to possible tiles, equal chance of spawning anywhere
		}
		Random random = new Random();
		System.out.println(creature);
		System.out.println(actorRef);
		System.out.println(gameState);
		if (possibleTiles ==null ){
			System.out.println("No possible tiles currently.");
			return;
		}
		if (this.getMana() >= creature.getManacost()) {
			System.out.println("Playing " + creature.getCardname());
			this.setMana(this.getMana() - creature.getManacost(), actorRef);
			this.hand.remove(creature);

			UnitCommands.summon(creature, actorRef, possibleTiles.get(random.nextInt(possibleTiles.size())), gameState);
		}


	}

	public ArrayList<Tile> tilesForAIUnitSummons(){


		ArrayList<TileSummonWrapper> tileSummonWrappers = new ArrayList<>(); //used for sorting tiles based on distance from AI avatar
		ArrayList<Tile> tilesForAIUnits = new ArrayList<>();
		ArrayList<MoveableUnit>AIUnits = gameState.getBoard().friendlyUnits(false); //returns all AI units
		ArrayList<Tile> backupPossibleTiles = new ArrayList<>(); //backup in case no other possible tiles using this algorithm
		//e.g. if Avatars are next to each other, this doesn't work

		Tile avatarAITile = null;
		//loop for finding AI Avatar unit tile
		for (MoveableUnit unit : AIUnits){
			if (!unit.isUserOwned()&& unit instanceof Avatar){
				avatarAITile = unit.getTile();
			}
		}

		Tile avatarhumanTile = null;
		//loop for finding AI Avatar unit tile
		for (MoveableUnit unit : gameState.getBoard().friendlyUnits(true)){
			if (unit.isUserOwned()&& unit instanceof Avatar){
				avatarhumanTile = unit.getTile();
			}
		}
		double avatarToAvatarDist = AI.calculateDistance(avatarhumanTile, avatarAITile);
		for (MoveableUnit unit : AIUnits){
			int tileX = unit.getTile().getTilex();
			int tileY = unit.getTile().getTiley();
			for (int i = tileX - 1; i<=tileX+1;i++){ //loop to search adjacent tiles to units
				for (int j = tileY - 1; j<=tileY+1; j++){
					if ( 0<=i && i<=8 && 0<=j && j<=4 ) { //if coord in board range
						Tile possibleTile = gameState.getBoard().getTile(i,j);
						if (possibleTile.getUnit()==null){ //no unit on tile
							backupPossibleTiles.add(possibleTile); //adds tiles to backup list
							if (!tilesForAIUnits.contains(possibleTile)) { //if it doesn't contain the tile
								double distanceToHumanAvatar = AI.calculateDistance(possibleTile,avatarhumanTile);
								if (distanceToHumanAvatar<=avatarToAvatarDist) {
									//only summons on tiles that are ideally in between AI avatar and human Avatar
									//prevents units from being summoned "behind" AI avatar
									tilesForAIUnits.add(possibleTile);
								}
							}
						}
					}
				}
			}
		}

		for (Tile tile : tilesForAIUnits){
			double xdistanceAIavatar = avatarAITile.getTilex()- tile.getTilex(); //sorts tiles based on their distance from ai avatar in terms of x axis
			if (xdistanceAIavatar<0){
				xdistanceAIavatar= xdistanceAIavatar*-1;
			}
			TileSummonWrapper tileSummonWrapper = new TileSummonWrapper(tile,xdistanceAIavatar);
			tileSummonWrappers.add(tileSummonWrapper);
		}
		Collections.sort(tileSummonWrappers); //sort tiles based on distance from ai avatar
		ArrayList<Tile> sortedTiles = new ArrayList<>();
		for (TileSummonWrapper tileSummonWrapper : tileSummonWrappers){
			Tile tile = tileSummonWrapper.getTile();
			sortedTiles.add(tile);
		}
		if (sortedTiles != null) {
			return sortedTiles;
		}else{
			return backupPossibleTiles;
		}
	}

	public Creature returnBestCreature(){
		Creature creature = new Creature(0, "no creature" ,-1, null, null, true, null);
		// possible fix to Jackson failing to handle null
		for (Card card : this.hand){
			if (card instanceof Creature){
				Creature possibleCreature = (Creature) card;
				if (possibleCreature.getManacost()<=this.mana){
					if (creature==null){
						creature = possibleCreature;
					}else{
						if (possibleCreature.getManacost()>creature.getManacost()){
							creature = possibleCreature;
						}
					}
				}
			}
		}
		System.out.println("Best creature is " +creature.getCardname());
		return creature;
	}


}


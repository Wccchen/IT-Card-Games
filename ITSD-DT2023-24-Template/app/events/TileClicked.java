package events;


import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import structures.GameState;
import structures.basic.MoveableUnit;
import structures.basic.Tile;

/**
 * Indicates that the user has clicked an object on the game canvas, in this case a tile.
 * The event returns the x (horizontal) and y (vertical) indices of the tile that was
 * clicked. Tile indices start at 1.
 * 
 * { 
 *   messageType = “tileClicked”
 *   tilex = <x index of the tile>
 *   tiley = <y index of the tile>
 * }
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class TileClicked implements EventProcessor{
	/**
	 * NOTE FOR US GUYS: I think when the last Tile clicked has a unit that was an enemy, I think we set the last
	 * message sent in gamestate to OtherClicked, the reason for this
	 *
	 *
	 */
	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		try {Thread.sleep(250);} catch (InterruptedException e) {e.printStackTrace();}
		//extracts cordinate X, Y
		int tilex = message.get("tilex").asInt();  //.asInt(); is to Transfer the cordinate  into integer
		int tiley = message.get("tiley").asInt();
		
		if (gameState.something == true) {
			// do some logic
			Tile currentTile = gameState.getBoard().getTile(tilex, tiley);

			if (currentTile.getUnit() != null){
				//logic for if current tile has unit (Tile with Unit)

				if(currentTile.getUnit().isUserOwned()){
					//if unit clicked was friendly.
					if (gameState.getLastMessage().equals(GameState.noEvent)){
						//insert logic about highlighting appropriate tiles for move/attack
						MoveableUnit unit = currentTile.getUnit();
						System.out.println("Friendly unit clicked, test that it has detected");
						unit.actionableTiles(out,gameState); // Highlighting possible actions.

					}else if(gameState.getLastMessage().equals(GameState.spellCardClicked)||gameState.getLastMessage().equals("CreatureCardClicked")){
						//insert message to player saying card can't be played here
						//set last message to NoEvent
					}else{
						//other logic?? think can delete
					}
				}else{
					//if unit clicked was enemy
					if (gameState.getLastMessage().equals(GameState.friendlyUnitClicked)){
						//initiate attack logic

						MoveableUnit attacker = gameState.getLastUnitClicked();
						attacker.attackUnit(out, currentTile,gameState);

					}else if (gameState.getLastMessage().equals(GameState.spellCardClicked)){
						//maybe we change for specific spell cards since there are only 3?
						//if Dark Terminus, place logic here
						//set last message to NoEvent
					}else if (gameState.getLastMessage().equals(GameState.noEvent)){
						//no action, inform player

					}else if(gameState.getLastMessage().equals(GameState.creatureCardClicked)){
						//inform player that not action can be taken
						//set last message to NoEvent
					}
				}

			}else{
				//logic for if current tile has no unit
				if (gameState.getLastMessage().equals(GameState.friendlyUnitClicked)){
					MoveableUnit unitToMove = gameState.getLastUnitClicked();
					unitToMove.moveUnit(out,currentTile,gameState);

				}else if (gameState.getLastMessage().equals(GameState.creatureCardClicked)){
					//initiate summon logic
				}else if (gameState.getLastMessage().equals(GameState.noEvent)){
					//inform player no action
				}else if (gameState.getLastMessage().equals(GameState.spellCardClicked)){
					//depends on card, if Dark terminus, won't work
					//if Wraithling swarm or Horn, might work? we need to decide
				}


			}
		}




		//set to appropriate message after all logic
	}

}

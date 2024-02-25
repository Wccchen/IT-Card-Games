package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.AI;
import structures.basic.MoveableUnit;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;

/**
 * Indicates that the user has clicked an object on the game canvas, in this case
 * the end-turn button.
 * 
 * { 
 *   messageType = “endTurnClicked”
 * }
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class EndTurnClicked implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		//remove player 1's unspent mana
		gameState.getPlayer1().setMana(0, out);
		//insert AI method where AI makes moves
		AI artificial = (AI) gameState.getPlayer2();
		Tile avatartile = gameState.getBoard().getTile(8, 3);
		MoveableUnit avatar = avatartile.getUnit();
		Unit avatarUnit = avatar.getUnit();
		artificial.aiMoved(out, avatarUnit, avatartile);
		avatar.moveUnit(out, avatartile, gameState);
		//remove player 2's unspent mana
		gameState.getPlayer2().setMana(0, out);
		//insert draw card for each player
		gameState.getPlayer1().drawCard();
		gameState.getPlayer2().drawCard();
		gameState.setTurnNumber(gameState.getTurnNumber()+1); //increase turnNumber
		//increase both players mana by (turn number+1)
		gameState.getPlayer1().setMana(gameState.getTurnNumber()+1, out);
		gameState.getPlayer2().setMana(gameState.getTurnNumber()+1, out);
		String nextTurnMessage = "It's now turn " + gameState.getTurnNumber() + "! Make your moves.";
		BasicCommands.addPlayer1Notification(out, nextTurnMessage, 2 );
		gameState.setLastMessage(GameState.noEvent);






	}

}

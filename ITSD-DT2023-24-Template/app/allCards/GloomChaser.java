package allCards;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.BigCard;
import structures.basic.Creature;
import structures.basic.MiniCard;
import structures.basic.OpeningGambit;
import structures.basic.Tile;
import structures.basic.Unit;
import structures.basic.Wraithling;

public class GloomChaser extends Creature implements OpeningGambit{

    public GloomChaser(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard, boolean isCreature,  String unitConfig) {
        super(id, cardname, manacost, miniCard, bigCard, isCreature, unitConfig);
        this.userOwned = true;
        this.attack = 3;
        this.currentHealth = 1;
        this.maxHealth = currentHealth;
    }

	@Override
	public void openingGambit(ActorRef out, GameState gameState) {
		// TODO Auto-generated method stub
		Tile tile = this.getTile();

	    Tile wraithlingTile = gameState.getBoard().getTile(tile.getTilex() - 1, tile.getTiley());
	    if (wraithlingTile != null && wraithlingTile.getUnit() == null) {
	        Wraithling wraithling = new Wraithling();
	        wraithling.summon(out, wraithlingTile, gameState);
	        BasicCommands.addPlayer1Notification(out, "OpeningGambit ability triggered, spawning Wraithling", 3);
	    }
	}
}
package allCards;

import akka.actor.ActorRef;
import structures.basic.BigCard;
import structures.basic.Board;
import structures.basic.Creature;
import structures.basic.Deathwatch;
import structures.basic.MiniCard;
import structures.basic.Unit;

public class BadOmen extends Creature implements Deathwatch{

    public BadOmen(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard, boolean isCreature,  String unitConfig) {
        super(id, cardname, manacost, miniCard, bigCard, isCreature, unitConfig);
        this.userOwned = true;
        this.attack = 0;
        this.currentHealth = 1;
        this.maxHealth = currentHealth;
    }

    // +1 attack permanently
    @Override
    public void deathWatch(ActorRef out, Board board) {
		this.setAttack(getAttack()+1, null);
	}
}

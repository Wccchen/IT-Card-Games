package allCards;

import structures.basic.BigCard;
import structures.basic.Creature;
import structures.basic.MiniCard;
import structures.basic.Provoke;
import structures.basic.Unit;

public class SwampEntangler extends Creature implements Provoke{
    public SwampEntangler(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard, boolean isCreature,  String unitConfig) {
        super(id, cardname, manacost, miniCard, bigCard, isCreature, unitConfig);
        this.userOwned = false;
        this.attack = 0;
        this.currentHealth = 3;
        this.maxHealth = currentHealth;
    }

	@Override
	public void provoke() {
		// TODO Auto-generated method stub
		
	}
}

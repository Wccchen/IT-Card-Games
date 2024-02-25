package allCards;

import structures.basic.BigCard;
import structures.basic.Creature;
import structures.basic.MiniCard;
import structures.basic.OpeningGambit;
import structures.basic.Unit;

public class SilverguardSquire extends Creature implements OpeningGambit{

    public SilverguardSquire(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard, boolean isCreature,  String unitConfig) {
        super(id, cardname, manacost, miniCard, bigCard, isCreature, unitConfig);
        this.userOwned = false;
        this.attack = 1;
        this.currentHealth = 1;
        this.maxHealth = currentHealth;
    }

	@Override
	public void openingGambit() {
		// TODO Auto-generated method stub
		
	}
}

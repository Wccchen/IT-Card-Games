package allCards;

import structures.basic.BigCard;
import structures.basic.MiniCard;
import structures.basic.Spell;

public class HornOfTheForsaken extends Spell{

    public HornOfTheForsaken(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard, boolean isCreature, boolean isUserOwned) {
        super(id, cardname, manacost, miniCard, bigCard, isCreature, isUserOwned);
    }
}
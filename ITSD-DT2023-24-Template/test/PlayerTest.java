import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import akka.actor.ActorRef;
import structures.basic.Avatar;
import structures.basic.Player;
import structures.basic.Card;
import akka.actor.ActorRef;
import org.mockito.Mockito;
import akka.actor.ActorRef; 

public class PlayerTest {

    private Player player;
    private Avatar avatar;
    private Card card;

    void setUp() {
        player = new Player(true);
        avatar = new Avatar(player);
        card = new Card();
    }

    @Test
    public void testGetAvatar() {
        this.avatar = new Avatar(player);
        player.setAvatar(avatar);
        assertEquals(avatar, player.getAvatar());
    }


    @Test
    public void testDrawCard() {
        Card card = new Card();
        player.getPlayerDeck().add(card);
        assertTrue(player.getHand().contains(card));
    }

    // More methods and testing might be added
}
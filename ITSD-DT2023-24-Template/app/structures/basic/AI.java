package structures.basic;
import actors.GameActor;
import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.Avatar;
import java.util.ArrayList;

public class AI extends Player  {
	private GameState gameState;
	private ActorRef actorRef;

	public AI(boolean userOwned, GameState gameState) {
		super(userOwned);
		this.gameState = gameState;

	}

	public void setActorRef(ActorRef out){
		this.actorRef = out;
	}
}

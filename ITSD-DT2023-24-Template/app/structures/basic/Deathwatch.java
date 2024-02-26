package structures.basic;

import akka.actor.ActorRef;

public interface Deathwatch {
    void deathWatch(ActorRef out, Board board);
    
    /*
     Units that have deathwatch are
     Bad Omen
     Shadow Watcher
     Bloodmoon Priestess
     Shadowdancer
     */
}

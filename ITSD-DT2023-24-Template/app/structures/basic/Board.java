package structures.basic;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import utils.BasicObjectBuilders;

public class Board {

    
    Tile [][] tiles;

    
    public void renderBoard (ActorRef out){
        for (int i = 0; i<9;i++){
            for (int j = 0; j<5;j++){
                BasicCommands.drawTile(out,this.tiles[i][j],0);
            }
        }
    }

    public Tile getTile (int x, int y){
        Tile tile = this.tiles[x][y];
        return tile;
    }

    private List<Unit> allUnits;

    public Board(){
        this.tiles = new Tile [9][5];
        this.allUnits = new ArrayList<>();
        for (int i = 0; i<9;i++){
            for (int j = 0; j<5;j++){
                tiles[i][j] = BasicObjectBuilders.loadTile(i,j);
            }
        }
    }

    
   
   
    public void unitDeath() {
        for (Unit unit : this.allUnits) {
            
            if (unit instanceof Deathwatch) {
                ((Deathwatch) unit).deathWatch();

            }
        }
    }
}

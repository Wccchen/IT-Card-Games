package structures.basic;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import utils.BasicObjectBuilders;

public class Board {

    
    Tile [][] tiles;

    public Board(){
        this.tiles = new Tile [9][5];
        for (int i = 0; i<9;i++){
            for (int j = 0; j<5;j++){
                tiles[i][j] = BasicObjectBuilders.loadTile(i,j);
            }
        }
    }
    
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

    
   
    /**
     * This method is called when a unit on the board dies.
     * It iterates over all units currently on the board and triggers the Deathwatch
     * effect for those units that have the Deathwatch ability.
     */
    public void unitDeath() {
        // Iterate over all units present on the board.
        for (Unit unit : this.allUnits) {
            // Check if the current unit has the Deathwatch ability.
            if (unit instanceof Deathwatch) {
                // If the unit has the Deathwatch ability, cast it to Deathwatch
                // and call its deathWatch() method to trigger the specific effect.
                ((Deathwatch) unit).deathWatch();
            }
        }
    }
}

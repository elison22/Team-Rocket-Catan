package test.model.game;

import facade.ClientFacade;
import model.game.GameModel;
import model.player.Player;
import org.junit.*;

import java.util.ArrayList;

/**
 * Created by Hayden on 2/1/15.
 *
 * This will test the canBuildRoad method the facade will call on the GameModel
 * to determine if a player meets the qualifications to build a road (it is the
 * player's turn, it is the right turn state, the player has a road piece left,
 * the player has the resources necessary, and the location of the road is a
 * legal placement for a road).
 */
public class CanBuildRoadTest {
    GameModel game;

    @Before
    public void initGameModel(){

        game = new GameModel();
        ArrayList<Player> playerList = new ArrayList<Player>();
//        playerList.add(new Player(0, "player1"));
//        playerList.add(new Player(1, "player2"));
//        playerList.add(new Player(2, "player3"));
//        playerList.add(new Player(3, "player4"));
    }
    //JUnit tests are going to be much easier once we have the serializer finished.
    //We need to be able to pass it a json blob and get back a fully-initialized GameModel
    //so the entire board, turn tracker, players, resources, etc. will be ready to go.
}

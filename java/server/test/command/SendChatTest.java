package test.command;

import static org.junit.Assert.*;
import model.sgame.ServerGame;

import org.junit.Before;
import org.junit.Test;

import shared.dto.CreateGame_Params;
import shared.dto.JoinGame_Params;
import shared.dto.SendChat_Params;
import facade.IModelFacade;
import facade.ModelFacade;

public class SendChatTest {
	
	IModelFacade modelFacade;

	@Before
	public void setUp() throws Exception {
		modelFacade = new ModelFacade();
	}

	@Test
	public void test() {
		// Create a game
        assertNotNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "RollNumberTest")));

        // Populate game with players
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "green"), "test1", 0));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "purple"), "test2", 1));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "yellow"), "test3", 2));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "puce"), "test4", 3));
        
        // Send a chat
        assertNotNull(modelFacade.sendChat(0, new SendChat_Params(0, "testing chat")));
        assertNotNull(modelFacade.sendChat(0, new SendChat_Params(3, "testing chat")));
        assertNotNull(modelFacade.sendChat(0, new SendChat_Params(2, "testing chat")));
        assertNotNull(modelFacade.sendChat(0, new SendChat_Params(1, "testing chat")));
        
        ServerGame game = modelFacade.getGame(0);
        
        // Check the model to see that the chats got logged
        assertTrue(game.getChat().size() == 4);
        assertTrue(game.getChat().get(0).getOwner() == "test1");
        assertTrue(game.getChat().get(1).getMessage() == "testing chat");
        assertTrue(game.getChat().get(2).getMessage() == "testing chat");
        assertTrue(game.getChat().get(3).getOwner() == "test2");
	}

}

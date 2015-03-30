package test.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.dto.CreateGame_Params;
import facade.IModelFacade;
import facade.ModelFacade;

public class CreateGameTest {
	
	IModelFacade modelFacade;

	@Before
	public void setUp() throws Exception {
		modelFacade = new ModelFacade();
	}

	@Test
	public void test() {
		
		// Try creating a game
		assertNotNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "test")));
		
		// Try creating games with invalid titles
		assertNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "test!")));
		assertNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "")));
		assertNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "123456789abcDEFGHILMNOPQRS")));
		assertNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "\n")));
		assertNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "\"")));
		assertNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "_-_")));
		
		// Try valid titles
		assertNotNull(modelFacade.createGame(new CreateGame_Params(true, false, false, "test123")));
		assertNotNull(modelFacade.createGame(new CreateGame_Params(false, true, false, "1")));
		assertNotNull(modelFacade.createGame(new CreateGame_Params(false, false, true, "123456789abcDEFGHILMNOPQR")));
	}

}

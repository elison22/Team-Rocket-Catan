package test.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.dto.CreateGame_Params;
import shared.dto.JoinGame_Params;
import facade.IModelFacade;
import facade.ModelFacade;

public class GetModelTest {
	
	IModelFacade facade;

	@Before
	public void setUp() throws Exception {
		facade = new ModelFacade();
	}

	@Test
	public void test() {
		assertNotNull(facade.createGame(new CreateGame_Params(false, false, false, "test")));
		assertTrue(facade.joinGame(new JoinGame_Params(0, "white"), "string", 12));
		System.out.println(facade.getGameModel(0));
	}

}

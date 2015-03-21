package test.creategame;

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
		assertNotNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "test")));
	}

}

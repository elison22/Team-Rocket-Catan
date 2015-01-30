package test.proxy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proxy.ProxyFacade;
import proxy.ServerException;
import shared.dto.Login_Params;

public class ProxyGameTest {
	
	ProxyFacade facade; 
	
	@Before
	public void initProxy() {
		facade = new ProxyFacade("localhost", "8081");
	}

	@Test
	public void getModelTest() throws ServerException {
		Login_Params loginParams = new Login_Params("Test", "Test");
		try {
			facade.register(loginParams);
		} catch (ServerException e) {
			facade.login(loginParams);
		}
		
		//TODO need to figure out join() first??
		assertTrue(facade.model(-1) != null);
	}
	
	@Test
	public void resetGameTest() throws ServerException {
		//TODO need to figure out join() first
	}
	
	@Test
	public void addAITest() throws ServerException {
		//TODO figure out join() first??
	}
	
	@Test
	public void listAITest() throws ServerException {
		assertTrue(facade.listAI() != null);
		
	}

}

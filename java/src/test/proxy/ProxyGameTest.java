package test.proxy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proxy.ProxyGame;
import proxy.ProxyUser;
import proxy.ServerException;
import proxy.ServerProxy;
import shared.dto.Login_Params;

public class ProxyGameTest {
	
	ProxyGame proxyGame = new ProxyGame();
	
	@Before
	public void initProxy() {
		ServerProxy.getInstance().initProxy("localhost", "8081");
	}

	@Test
	public void getModelTest() throws ServerException {
		ProxyUser pu = new ProxyUser();
		Login_Params loginParams = new Login_Params("Test", "Test");
		try {
			pu.register(loginParams);
		} catch (ServerException e) {
			pu.login(loginParams);
		}
		
		//TODO need to figure out join() first??
		assertTrue(proxyGame.model(-1) != null);
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
		assertTrue(proxyGame.listAI() != null);
		
	}

}

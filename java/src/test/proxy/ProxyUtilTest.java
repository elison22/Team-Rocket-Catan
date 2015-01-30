package test.proxy;

import static org.junit.Assert.*;
import org.junit.*;

import proxy.ProxyFacade;
import proxy.ServerException;
import shared.dto.ChangeLogLevel_Params;

public class ProxyUtilTest {
	
	ProxyFacade facade;
	
	@Before
	public void initProxy() {
		facade = new ProxyFacade("localhost", "8081");
	}

	@Test
	public void changeLogLevelTest() throws ServerException {
		assertTrue(facade.changeLogLevel(new ChangeLogLevel_Params("FINEST")));
		
	}
	
	@Test(expected = Exception.class)
	public void testServerExceptionOnBadInput() throws ServerException {
		facade.changeLogLevel(new ChangeLogLevel_Params("NOT VALID"));
	}

}

package test.poller;

import static org.junit.Assert.*;

import org.junit.Test;

import facade.ClientFacade;
import proxy.MockProxy;
import serverpoller.AlternativePoller;

public class PollerTest {

	@Test
	public void test() {
		// Create a new facade and get the default version number
		ClientFacade cFacade = new ClientFacade();
		int prevVersion = cFacade.getVersionNumber();
		
		// Manually (don't rely on timer) poll the server
		AlternativePoller poller = new AlternativePoller(3000, new MockProxy(), cFacade);
		poller.pollServer();
		
		// Compare the previous version number to the now should-be-updated
		// version number. They should be different
		int curVersion = cFacade.getVersionNumber();
		assertTrue(prevVersion != curVersion);
	}
}

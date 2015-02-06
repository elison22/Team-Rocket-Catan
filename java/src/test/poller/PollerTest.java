package test.poller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import facade.ClientFacade;
import proxy.*;
import serverpoller.ServerPoller;

public class PollerTest {
	
	ServerPoller poller;
	ClientFacade cFacade;
	IProxyFacade proxy;
	
	@Before
	public void initPoller() {
		cFacade = new ClientFacade();
		proxy = new MockProxy();
		poller = new ServerPoller(3000, proxy, cFacade);
	}

	@Test
	public void testPollServer() {
		// Get the default version number
		int prevVersion = cFacade.getVersionNumber();
		
		// Manually (don't rely on timer) poll the server
		poller.pollServer();
		
		// Compare the previous version number to the now should-be-updated
		// version number. They should be different.
		int curVersion = cFacade.getVersionNumber();
		assertTrue(prevVersion != curVersion);
	}
	
	@Test
	public void testPollerTimer() {
		// Test that the poller's timer is working, and that the poller poll's 
		// the server at the specified interval.
		
		// Use the model's version number to test if it's getting updated
		int preVersion = -1;
		
		// Run the timer for ~9 seconds, at ~3 seconds check to see if the 
		// model got updated.
		for (int i = 0; i < 3; ++i) {
			for (long stop = System.currentTimeMillis() + 3000; stop > System.currentTimeMillis(); ) {
				try {
					// Let the CPU do other stuff while we wait 
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// Test if the version number changed
			assertTrue(preVersion < cFacade.getVersionNumber());
			
			// Remember the new version number
			preVersion = cFacade.getVersionNumber();
		}
		
	}
}

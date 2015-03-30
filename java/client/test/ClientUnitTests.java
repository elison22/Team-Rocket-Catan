package test;

public class ClientUnitTests {

	/** IMPORTANT: For the proxy tests to pass, the server MUST be running. The
	 * server also MUST be cleaned (run: ant clean) before each subsequent run 
	 * of these test cases! 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Holds all of the JUnit classes to be run. Note that the String 
		// must include the package names that the class is located in.
		String[] testClasses = new String[] {
				"test.proxy.ProxyGamesTest",
				"test.proxy.ProxyGameTest",
				"test.proxy.ProxyMovesTest",
				"test.proxy.ProxyUserTest",
				"test.proxy.ProxyUtilTest",
				"test.poller.PollerTest" ,
				"test.serializer.SerializerTest" ,
                "test.model.player.PlayerTest" ,
                "test.model.game.TurnTrackerTest" ,
                "test.model.board.BoardTest"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
}


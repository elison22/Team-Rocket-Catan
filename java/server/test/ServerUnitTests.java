package test;

public class ServerUnitTests {

	/** IMPORTANT: For the proxy tests to pass, the server MUST be running. The
	 * server also MUST be cleaned (run: ant clean) before each subsequent run 
	 * of these test cases! 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Holds all of the JUnit classes to be run. Note that the String 
		// must include the package names that the class is located in.
		String[] testClasses = new String[] {
				"test.command.BuildRoadTest",
				"test.command.BuildSettlementTest",
				"test.command.RegisterAndLoginTest",
				"test.game.CreateGameTest",
				"test.game.GetModelTest",
				"test.game.JoinGameTest",
				"test.user.UserManagerTest",
				"test.command.FinishTurnTest",
				"test.command.RollNumberTest"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}

}

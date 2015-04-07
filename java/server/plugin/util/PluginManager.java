package plugin.util;

/**
 * @author Hayden
 * The PluginManager class is used to access the config file and run the appropriate jar
 * file based on the persistence type that is passed into the server when it is run from
 * the command line. It will also send the class path to the appropriate PersistenceManager
 * so the server can create that object.
 */
public class PluginManager {

    /**
     * The path to the config file
     */
    private String configPath;

    /**
     * parses the json config file and returns the class path to the derived
     * PersistenceManager class that the plugin uses.
     * @return The class path to the PersistenceManager
     */
    public String getClassPath(){
        return null;
    }

    /**
     * parses the config file and runs the appropriate jar file for the type
     * of persistence specified at runtime.
     */
    public void runPersistence(){

    }

    /**
     * adds json information to the config file so new plugins can be added
     * without recompiling the code.
     * @param pluginConfig A string in json format that includes the type, the
     * path to the jar file, and the PersistenceManager.
     */
    public void addPluginInfo(String pluginConfig){

    }

}

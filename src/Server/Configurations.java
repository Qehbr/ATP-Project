package Server;


import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Class that handles the configuration properties for the server.
 * Will get the Thread pool size, the generating maze algorithm and the searching algorithm for the server to use.
 */
public class Configurations {

    private static Properties properties;

    /**
     * Initialize the Properties as such that the class will only initialize once.
     */
    public static void init()
    {
        try (InputStream input = Files.newInputStream(Paths.get("resources/config.properties")))
        {
            properties = new Properties();
            properties.load(input);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Get the name of the generating algorithm from the properties file
     * @return returns the appropriate Maze Generator algorithm
     */
    public static AMazeGenerator getGenerator()
    {
        AMazeGenerator aMazeGenerator = null;
        switch (properties.getProperty("MazeGeneratingAlgorithm"))
        {
            case "MyMazeGenerator":
                aMazeGenerator = new MyMazeGenerator();
                break;
            case "SimpleMazeGenerator":
                aMazeGenerator = new SimpleMazeGenerator();
                break;
        }
        return aMazeGenerator;
    }

    /**
     * Get the name of the searching algorithm from the properties file
     * @return returns the appropriate Maze Searching algorithm
     */
    public static ASearchingAlgorithm getAlgorithm()
    {
        ASearchingAlgorithm aSearchingAlgorithm = null;
        switch (properties.getProperty("MazeSearchingAlgorithm"))
        {
            case "BestFirstSearch":
                aSearchingAlgorithm = new BestFirstSearch();
                break;
            case "BreadthFirstSearch":
                aSearchingAlgorithm = new BreadthFirstSearch();
                break;
            case "DepthFirstSearch":
                aSearchingAlgorithm = new DepthFirstSearch();
                break;
        }
        return aSearchingAlgorithm;
    }

    /**
     * checks the properties file for the Thread pool size requested.
     * @return the number of the Thread pool size
     */
    public static int getNumberOfThreads()
    {
        return Integer.parseInt(properties.getProperty("ThreadPoolSize"));
    }

}

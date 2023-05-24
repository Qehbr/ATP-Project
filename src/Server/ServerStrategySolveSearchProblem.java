package Server;


import algorithms.mazeGenerators.Maze;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.HashSet;

/**
 * Strategy for solving a maze
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {

    //hash set for checking if maze was already solved
    HashSet<Integer> solutions;

    public ServerStrategySolveSearchProblem() {
        this.solutions = new HashSet<>();
    }

    @Override
    public void applyStrategy(InputStream is, OutputStream os) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(is);
            ObjectOutputStream toClient = new ObjectOutputStream(os);

            //get maze from client
            Maze clientMaze = (Maze) fromClient.readObject();

            //get path of maze directory
            String tempDirectoryPath = System.getProperty("java.io.tmpdir") + clientMaze.hashCode();

            // if already solved
            if (solutions.contains(clientMaze.hashCode())) {
                //get solution from path
                FileInputStream fileIs = new FileInputStream(tempDirectoryPath);
                ObjectInputStream objectIs = new ObjectInputStream(fileIs);
                Solution clientMazeSolution = (Solution) objectIs.readObject();
                //send solution to the client
                toClient.writeObject(clientMazeSolution);
                fileIs.close();
                objectIs.close();
            } else {
                //solve the maze
                Solution clientMazeSolution = Configurations.getAlgorithm().solve(new SearchableMaze(clientMaze));
                //save the solution in path
                FileOutputStream fileOs = new FileOutputStream(tempDirectoryPath);
                ObjectOutputStream objectOs = new ObjectOutputStream(fileOs);
                objectOs.writeObject(clientMazeSolution);
                //mark maze as solved
                solutions.add(clientMaze.hashCode());
                //send solution to the client
                toClient.writeObject(clientMazeSolution);
                fileOs.close();
                objectOs.close();
            }

            toClient.flush();
            toClient.close();
            fromClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

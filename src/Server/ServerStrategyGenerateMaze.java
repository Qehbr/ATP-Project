package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

/**
 * Strategy for generating a maze
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void applyStrategy(InputStream is, OutputStream os) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(is);
            ObjectOutputStream toClient = new ObjectOutputStream(os);

            //get dimensions from client
            int[] clientArray = (int[]) fromClient.readObject();

            //generate a new maze
            Maze clientMaze = Configurations.getGenerator().generate(clientArray[0], clientArray[1]);

            //compress the maze
            ByteArrayOutputStream byteOs = new ByteArrayOutputStream();
            //TODO make MyCompressor instead of SimpleCompressor
//            OutputStream compressorOs = new MyCompressorOutputStream(byteOs);
            OutputStream compressorOs = new SimpleCompressorOutputStream(byteOs);
            compressorOs.write(clientMaze.toByteArray());

            //send compressed maze to the client
            toClient.writeObject(byteOs.toByteArray());
            toClient.flush();

            byteOs.close();
            compressorOs.close();
            toClient.close();
            fromClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

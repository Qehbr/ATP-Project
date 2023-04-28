package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class representing the server using general server strategy
 */
public class Server {

    private int serverPort;
    private int listeningIntervals;
    private IServerStrategy serverStrategy;

    private volatile boolean stop;

    private ExecutorService threadPool; //thread pool for handling different clients

    /**
     * @param serverPort         Port to open for server
     * @param listeningIntervals Intervals of listening to port
     * @param serverStrategy     Strategy used by server
     */
    public Server(int serverPort, int listeningIntervals, IServerStrategy serverStrategy) {
        this.serverPort = serverPort;
        this.listeningIntervals = listeningIntervals;
        this.serverStrategy = serverStrategy;
        this.stop = false;
        this.threadPool = Executors.newFixedThreadPool(2); //TODO get number of threads from Configurations
    }

    /**
     * Starting the server
     */
    public void start() {
        new Thread(() -> {
            try {
                //starting server
                ServerSocket serverSocket = new ServerSocket(serverPort);
                serverSocket.setSoTimeout(listeningIntervals);
                System.out.println("Server is listening on port: " + serverPort);
                //listening
                while (!stop) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        //if client is connected
                        System.out.println("Client " + clientSocket.toString() + " is accepted");
                        //handle it
                        threadPool.execute(() -> {
                            handleClient(clientSocket);
                        });
                    } catch (SocketTimeoutException e) {
                        System.out.println("Socket timeout - there is no new clients");
                    }
                }
                threadPool.shutdown();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Handling the client
     *
     * @param clientSocket Connection to specific client
     */
    private void handleClient(Socket clientSocket) {
        try {
            serverStrategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stopping the server
     */
    public void stop() {
        this.stop = true;
    }
}

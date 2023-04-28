package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Client class representing client connecting to the server
 */
public class Client {

    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    /**
     * @param serverIP   IP of server
     * @param serverPort Port of server
     * @param strategy   Strategy used by client
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    /**
     * Connecting to server and applying a strategy
     */
    public void communicateWithServer() {
        //try to connect
        try (Socket serverSocket = new Socket(serverIP, serverPort)) {
            System.out.println("Client is connected to server: " + serverIP + ":" + serverPort);
            //apply strategy
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

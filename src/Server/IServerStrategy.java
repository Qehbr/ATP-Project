package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Strategy used by server
 */
public interface IServerStrategy {
    /**
     *
     * @param fromClient Client response
     * @param toClient   Server response
     */
    void applyStrategy(InputStream fromClient, OutputStream toClient);
}

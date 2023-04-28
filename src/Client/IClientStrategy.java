package Client;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface for strategy used by client
 */
public interface IClientStrategy {
    /**
     * @param fromServer Server response
     * @param toServer   Query to server
     */
    void clientStrategy(InputStream fromServer, OutputStream toServer);
}

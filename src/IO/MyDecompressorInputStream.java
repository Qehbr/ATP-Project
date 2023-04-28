package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * Decorator for input stream for decompressing the maze
 */
public class MyDecompressorInputStream extends InputStream {

    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        throw new IOException();
    }

    /**
     * Decompressing compressed bytes of the maze
     *
     * @param bytes        Decompressed bytes of the maze
     * @return             0 representing the function worked properly
     * @throws IOException If IOException was thrown, then there is a problem with InputStream in
     */
    @Override
    public int read(byte[] bytes) throws IOException {
        return 0;
    }
}

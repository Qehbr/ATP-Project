package IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Decorator for output stream for compressing the maze
 */
public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    @Override
    public void write(int b) throws IOException {
        throw new IOException();
    }

    /**
     * Compressing bytes of the maze
     *
     * @param bytes        Maze bytes
     * @throws IOException If IOException was thrown, then there is a problem with OutputStream out
     */
    @Override
    public void write(byte[] bytes) throws IOException {
        //TODO
    }

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }
}

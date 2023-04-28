package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * Decorator for input stream for decompressing the maze
 */
public class SimpleDecompressorInputStream extends InputStream {

    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        throw new IOException();
    }

    /**
     * Decompressing compressed bytes of the maze
     *
     * @param bytes Decompressed bytes of the maze
     * @return 0 representing the function worked properly
     * @throws IOException If IOException was thrown, then there is a problem with InputStream in
     */
    @Override
    public int read(byte[] bytes) throws IOException {
        //get compressed bytes
        byte[] mazeBytes = in.readAllBytes();

        //start position, goal position, rows, cols
        int i;
        for (i = 0; i < 24; i++) {
            bytes[i] = mazeBytes[i];
        }
        int j = i;

        //decompressing
        byte currentByte = 0;
        while (i < mazeBytes.length) {
            //fill with current number
            for (int k = 0; k < mazeBytes[i]; k++) {
                bytes[j] = currentByte;
                j++;
            }
            //switch to other number
            i++;
            if (currentByte == 0) {
                currentByte = 1;
            } else {
                currentByte = 0;
            }
        }
        return 0;
    }
}

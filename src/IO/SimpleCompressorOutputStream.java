package IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Decorator for output stream for compressing the maze
 */
public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    @Override
    public void write(int b) throws IOException {
        throw new IOException();
    }

    /**
     * Compressing bytes of the maze
     * First 24 bytes represent start position, goal position, rows, cols
     * Rest of the bytes represents number of 0's and 1's starting with 0. Maze (1 1 0 0 1) -> Compressed (0 2 2 1)
     *
     * @param bytes Maze bytes
     * @throws IOException If IOException was thrown, then there is a problem with OutputStream out
     */
    @Override
    public void write(byte[] bytes) throws IOException {
        //start position, goal position, rows, cols
        int i;
        for (i = 0; i < 24; i++) {
            out.write(bytes[i]);
        }

        int currentByte = 0;
        int currentByteLength = 0;
        while (i < bytes.length) {
            //count the number of the same bytes
            if (bytes[i] == currentByte) {
                currentByteLength++;
                i++;
            } else {
                //if number is more than int bytes
                while (currentByteLength > 127) {
                    //write 127 of current number
                    out.write(127);
                    //write 0 of next number
                    out.write(0);
                    currentByteLength -= 127;
                }
                // write current number
                out.write(currentByteLength);
                // switch to next number
                currentByteLength = 0;
                currentByte = currentByte == 0 ? 1 : 0;
            }
        }
        //write last number
        while (currentByteLength > 127) {
            out.write(127);
            out.write(0);
            currentByteLength -= 127;
        }
        out.write(currentByteLength);
    }

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }
}

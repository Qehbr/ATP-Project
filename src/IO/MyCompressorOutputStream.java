package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

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
        //start position, goal position, rows, cols
        int i;
        for (i = 0; i < 24; i++) {
            out.write(bytes[i]);
        }

        //initialize a dictionary and fill it with starting data of every single byte value possible
        Map<String,Integer> dictionary = new HashMap<>();
        for (i=0;i<128;i++)
            dictionary.put(String.format("%7s", Integer.toBinaryString((byte)i & 0xFF)).replace(' ', '0'),i);

        StringBuilder symbol = new StringBuilder();

        //add the new byte to the string and check if it's in the dictionary, if yes, add the value and reset the string
        //if not, add the new byte to the string and check again.
        for (i=24;i<bytes.length;i++)
        {
            symbol = new StringBuilder(symbol + Byte.toString(bytes[i]));
            if(dictionary.containsKey(symbol.toString())) {
                out.write(dictionary.get(symbol.toString()));
                symbol = new StringBuilder();
            }
        }
        //if there was a string that wasn't sent (because the length doesn't divide in 7), mark its start, and send it
        //one by one
        if(symbol.length()>0)
        {
            out.write(-1);
            for (i=0;i<symbol.length();i++)
                out.write((byte)Character.getNumericValue(symbol.charAt(i)));
        }

    }

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }
}

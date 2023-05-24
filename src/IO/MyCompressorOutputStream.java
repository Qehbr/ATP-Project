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

        //initialize a dictionary and fill it with starting data
        Map<String,Integer> dictionary = new HashMap<>();
        for (i=0;i<256;i++)
            dictionary.put(Byte.toString((byte)i),i);
        //initialize a mutable string
        StringBuilder currentSymbol = new StringBuilder();

        for (i=24;i<bytes.length;i++)
        {
            //check for the longest "string" of bytes in the dictionary.
            //if something isn't in the dictionary, send the previous string, add the new one to the dictionary
            //and start anew
            String symbol = currentSymbol.toString() + Byte.toString(bytes[i]);
            if (dictionary.containsKey(symbol))
                currentSymbol = new StringBuilder(symbol);
            else
            {
                out.write(dictionary.get(currentSymbol.toString()));
                dictionary.put(symbol,dictionary.size());
                currentSymbol = new StringBuilder(Byte.toString(bytes[i]));
            }
        }
        //if there's a string that wasn't sent, send it
        if(currentSymbol.length()>0)
            out.write(dictionary.get(currentSymbol.toString()));

    }

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }
}

package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
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
        //TODO: fix decompressor
        int i;
        //initialize a byte array to receive and order the data
        byte[] mazeBytes= in.readAllBytes();
        //start position, goal position, rows and cols
        for (i = 0; i < 24; i++)
            bytes[i] = mazeBytes[i];

        //initialize a dictionary with starter data
        Map<Integer, String> dictionary = new HashMap<>();
        for (i = 0; i < 256; i++)
            dictionary.put(i, Byte.toString((byte) i));

        //initialize a mutable string and add the first byte of data
        StringBuilder uncompressedMaze = new StringBuilder();
        int prevCode =  mazeBytes[24];
        uncompressedMaze.append(dictionary.get(prevCode));
        for (i=25;i<mazeBytes.length;i++)
        {
            //variable for the current byte of data and for the current "string" of symbols
            int currCode = mazeBytes[i];
            String currSymbol;

            //get the code for the current byte if it exists, if not, make a new one
            if (dictionary.containsKey(currCode))
                currSymbol = dictionary.get(currCode);
            else
                currSymbol = dictionary.get(prevCode)+dictionary.get(prevCode).charAt(0);

            //add the new symbol to the string
            uncompressedMaze.append(currSymbol);

            //make a new dictionary value for a new symbol, and cycle back the prev and curr code
            String prevSymbol = dictionary.get(prevCode);
            dictionary.put(dictionary.size(),prevSymbol+currSymbol.charAt(0));
            prevCode = currCode;
        }

        //parse to bytes the uncompressed maze string
        for (i=0;i<uncompressedMaze.length();i++)
            bytes[i+24] = Byte.parseByte(uncompressedMaze.substring(i,i+1));
        return 0;
    }
}

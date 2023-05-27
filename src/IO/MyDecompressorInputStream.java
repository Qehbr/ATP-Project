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
        int i;
        //initialize a byte array to receive and order the data
        byte[] mazeBytes= in.readAllBytes();
        //start position, goal position, rows and cols
        for (i = 0; i < 24; i++)
            bytes[i] = mazeBytes[i];

        //initialize a dictionary with starter data of every single byte value
        Map<Integer, String> dictionary = new HashMap<>();
        for (i = 0; i < 128; i++)
            dictionary.put(i, String.format("%7s", Integer.toBinaryString((byte)i & 0xFF)).replace(' ', '0'));

        //string to represent the uncompressed maze
        StringBuilder uncompressedMaze = new StringBuilder();

        //check the dictionary for the right value, and add it to the string
        //if we received "-1" it means it's the leftover data, add it one by one
        for (i=24;i<mazeBytes.length;i++)
        {
            if ((int)mazeBytes[i] == -1)
            {
                StringBuilder currSymbol = new StringBuilder();
                i++;
                while (i<mazeBytes.length)
                {
                    currSymbol = new StringBuilder(currSymbol + Byte.toString(mazeBytes[i]));
                    i++;
                }
                uncompressedMaze.append(currSymbol);
                break;
            }

            if(dictionary.containsKey((int)(mazeBytes[i])))
            {
                uncompressedMaze.append(dictionary.get((int)(mazeBytes[i])));
            }


        }

        //parse to bytes the uncompressed maze string
        for (i=0;i<uncompressedMaze.length();i++)
            bytes[i+24] = Byte.parseByte(uncompressedMaze.substring(i,i+1));
        return 0;
    }
}

package algorithms.maze3D;


import java.util.Random;

/**
 * Abstract class Maze3D Generator implementing interface Maze3D Generator
 */
public abstract class AMaze3DGenerator implements IMazeGenerator3D {

    public static Random random3D = new Random();

    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long startTime = System.currentTimeMillis();
        generate(depth, row, column);
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Static function for generating random position on edge of the 3D maze
     *
     * @param depths Depth of maze
     * @param rows   Rows of maze
     * @param cols   Columns of maze
     * @return positions[0] - depth, positions[1] - row, positions[2] - column of position
     */
    protected static int[] generatePositionsOnEdge(int depths, int rows, int cols) {
        int X = 0, Y = 0, Z = 0;
        int face = random3D.nextInt(6); // Generate a random face (0=top, 1=bottom, 2=left, 3=right, 4=front, 5=back)

        if (face == 0) { // Top face
            X = random3D.nextInt(depths);
            Y = 0;
            Z = random3D.nextInt(cols);
        } else if (face == 1) { // Bottom face
            X = random3D.nextInt(depths);
            Y = rows - 1;
            Z = random3D.nextInt(cols);
        } else if (face == 2) { // Left face
            X = 0;
            Y = random3D.nextInt(rows);
            Z = random3D.nextInt(cols);
        } else if (face == 3) { // Right face
            X = depths - 1;
            Y = random3D.nextInt(rows);
            Z = random3D.nextInt(cols);
        } else if (face == 4) { // Front face
            X = random3D.nextInt(depths);
            Y = random3D.nextInt(rows);
            Z = 0;
        } else { // Back face
            X = random3D.nextInt(depths);
            Y = random3D.nextInt(rows);
            Z = cols - 1;
        }

        int[] positions = new int[3];
        positions[0] = X;
        positions[1] = Y;
        positions[2] = Z;
        return positions;
    }


}

package algorithms.mazeGenerators;

import java.util.Random;

public abstract class AMazeGenerator implements IMazeGenerator {

    public static Random random = new Random();
    protected static String MazeWALL = "▓";
    protected static String MazePASS = " ";

    protected static String MazeGOAL = "E";
    protected static String MazeSTART = "S";


    @Override
    public long measureAlgorithmTimeMillis(int rows, int cols) {
        long startTime = System.currentTimeMillis();
        generate(rows, cols);
        return System.currentTimeMillis() - startTime;
    }

    protected static int[] generatePositionsOnEdge(int rows, int cols) {
        int X = 0, Y = 0;
        while ((X == 0 && Y == 0) || (X == 0 && Y == cols - 1) || (X == rows - 1 && Y == 0) || (X == rows - 1 && Y == cols - 1)) {
            int side = random.nextInt(4);
            switch (side) {
                case 0: // Top side
                    Y = random.nextInt(cols);
                    break;
                case 1: // Bottom side
                    X = rows - 1;
                    Y = random.nextInt(cols);
                    break;
                case 2: // Left side
                    X = random.nextInt(rows);
                    break;
                case 3: // Right side
                    X = random.nextInt(rows);
                    Y = cols - 1;
                    break;
            }
        }
        int[] positions = new int[2];
        positions[0] = X;
        positions[1] = Y;
        return positions;
    }

//    protected static Position[] generateStartAndGoalPosition(int rows, int cols) {
//        // Generate start and goal positions
//        int[] startPositions = generatePositionsOnEdge(rows, cols);
//        int[] goalPositions = generatePositionsOnEdge(rows, cols);
//        while (goalPositions[0] == startPositions[0] && goalPositions[1] == startPositions[1]) {
//            goalPositions = AMazeGenerator.generatePositionsOnEdge(rows, cols);
//        }
//        Position sp = new Position(startPositions[0], startPositions[1], MazeSTART) {
//        };
//        Position gp = new Position(goalPositions[0], goalPositions[1], MazeGOAL);
//        Position[] spAndGp = new Position[2];
//        spAndGp[0] = sp;
//        spAndGp[1] = gp;
//        return spAndGp;
//    }
}

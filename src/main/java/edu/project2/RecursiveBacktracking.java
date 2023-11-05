package edu.project2;

public class RecursiveBacktracking implements MazeGenerator {

    public Maze generate(int height, int width) {

        char[][] maze = new char[height][width];

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                maze[h][w] = '█';
            }
        }

        int startH = 1;
        int startW = 1;

        maze[startH][startW] = ' ';
        generatePath(startH, startW, maze);

        return new Maze(height, width, maze);
    }

    @SuppressWarnings("MagicNumber")
    private static void generatePath(int h, int w, char[][] maze) {
        int[] directions = new int[] {1, 2, 3, 4};

        shuffleArray(directions);

        for (int dir : directions) {
            int newH = h;
            int newW = w;

            switch (dir) {
                case 1:
                    newH -= 2;
                    break;
                case 2:
                    newW += 2;
                    break;
                case 3:
                    newH += 2;
                    break;
                case 4:
                    newW -= 2;
                    break;
                default:
                    break;
            }

            if (newH > 0 && newW > 0 && newH < maze.length - 1 && newW < maze[0].length - 1
                && maze[newH][newW] == '█') {
                maze[newH][newW] = ' ';
                maze[(h + newH) / 2][(w + newW) / 2] = ' ';
                generatePath(newH, newW, maze);
            }
        }
    }

    private static void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = RandomSeed.RANDOM.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

}

package edu.project2;

import java.util.LinkedList;
import java.util.Queue;

public class Breadth implements MazeGenerator {

    @Override
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
        generateBreadth(startH, startW, maze);

        return new Maze(height, width, maze);
    }

    @SuppressWarnings("MagicNumber")
    private void generateBreadth(int startH, int startW, char[][] maze) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {startH, startW});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int h = current[0];
            int w = current[1];

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
                    && maze[newH][newW] == '█' && (RandomSeed.RANDOM.nextInt(3) != 1 || queue.size() <= 1)) {
                    maze[newH][newW] = ' ';
                    maze[(h + newH) / 2][(w + newW) / 2] = ' ';
                    queue.offer(new int[] {newH, newW});
                }
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

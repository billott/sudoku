public class Sudoku {

    static int userGrid[][]=new int[][]
                    {{0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0}};//[horizontal][vertical]

    static int puzzleGrid[][]=new int[][]
                    {{0,1,9,0,0,2,0,0,0},
                    {4,7,0,6,9,0,0,0,1},
                    {0,0,0,4,0,0,0,9,0},
                    {8,9,4,5,0,7,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,2,0,1,9,5,8},
                    {0,5,0,0,0,6,0,0,0},
                    {6,0,0,0,2,8,0,7,9},
                    {0,0,0,1,0,0,8,6,0}};//[horizontal][vertical]

    static int grid[][]=new int[9][9];//the grid that the program experiments on


    // Create a puzzle Square
    int puzzleBoxData[][]=new int[3][3];

    public static void printPuzzle(int[][] grid) {
        // 25 character line on top
        final String horizontalLineSeparator = "-------------------------------";
        final String verticalLineSeparator = "|";

        // Top line of puzzle
        System.out.println("\n" + horizontalLineSeparator);

        // Created the puzzle box
        for (int i=0;i<9;i++) {
            // puzzle box left edge
            System.out.print(verticalLineSeparator);

            // print each row in puzzle
            for (int j=0;j<9;j++) {
                System.out.print(" " + grid[i][j] + " ");

                // After three numbers in the row
                // then print bar divider to
                // create a new puzzle section (box)
                if (((j + 1) % 3) == 0){
                    System.out.print(verticalLineSeparator);
                }
            }
            System.out.println();

            // After each row of numbers then print line separator
            if (((i + 1) % 3 )== 0){
                System.out.print(horizontalLineSeparator + "\n");
            }
        }
    }

    //static String pgrid[][]=new String[9][9];//the posibilities grid
    public static void main(String[] args) {
        for (int i=0;i<9;i++) {
            for (int j=0;j<9;j++) {
                grid[i][j]=userGrid[i][j];
            }
        }

        // show the initial grid
        //print(grid);

        double timeStart=System.currentTimeMillis();
        //printPuzzle(loop(0, 0, grid));
        //puzzleGrid
        printPuzzle(puzzleGrid);

        double timeEnd=System.currentTimeMillis();
        System.out.println("That took "+(timeEnd-timeStart)+" millis to complete.");
    }

    // loop does what??? What is the purpose of this loop function?
    public static int[][] loop(int y, int x, int[][] grid) {
        while(!validity(8, 8, grid) || grid[8][8]==0)//while not solved
        {
            if (userGrid[y][x]!=0) {
                int yy, xx;
                if (x==8) {yy=y+1; xx=0;} else {yy=y; xx=x+1;}
                loop(yy, xx, grid);
            } else {
                if (grid[y][x]<9) {//going forward
                    grid[y][x]++;
                    if (validity(y, x, grid)) {
                        int yy, xx;
                        if (x==8) {yy=y+1; xx=0;} else {yy=y; xx=x+1;}
                        loop(yy, xx, grid);
                    }
                } else {
                    grid[y][x]=0;
                    break;
                }
            }
        }
        return grid;
    }

    public static boolean validity(int x, int y, int[][] grid) {
        String temp="";
        for (int i=0;i<9;i++) {
            temp+=Integer.toString(grid[i][y]);//horizontal
            temp+=Integer.toString(grid[x][i]);//verical
            temp+=Integer.toString(grid[(x/3)*3+i/3][(y/3)*3+i%3]);//square
        }
        int count=0, idx=0;
        while ((idx=temp.indexOf(Integer.toString(grid[x][y]), idx))!=-1)
        {idx++; count++;}
        return count==3;
    }

}
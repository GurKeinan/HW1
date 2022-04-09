import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;  // Note: Do not change this line.

    public static int max(int x , int y) {
        return ((x>y) ? x:y);
    }
    public static int min(int x , int y) {
        return ((x<y) ? x:y);
    }

    /**
     * Returns array of integers that represent the size of the games student validity matrix
     *
     * @param boardSize size of student matrix for current game
     * @return          number of rows and columns of student matrix
     */
    public static int [] boardSize(String boardSize) {
        int [] sizes = new int[2];
        String num1 = boardSize.split(" X ")[0];
        String num2 = boardSize.split(" X ")[1];
        sizes[0] = Integer.parseInt(num1);
        sizes[1] = Integer.parseInt(num2);
        return sizes;
    }

    /**
     * Returns two-dimensional array of integers representing the board in which the game will be played
     *
     * @return starting board of the game
     */
    public static int[][] settingTheBoard() {
        boolean flag;
        String indexes;
        int xValue;
        int yValue;
        int [] sizes = new int [2];
        System.out.println("Dear president, please enter the board’s size.");
        String boardSize = scanner.nextLine();
        sizes = boardSize(boardSize);
        int m = sizes[0];
        int n = sizes[1];
        int[][] board = new int[m][n];
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n ; j++) {
                board[i][j] = 0;
            }
        }
        flag = true;
        boolean printEnterIndexes = true;
        while (flag) {
            if(printEnterIndexes == true) System.out.println("Dear president, please enter the cell’s indexes.");
            printEnterIndexes = true;
            indexes = scanner.nextLine();
            if (indexes.equals("Yokra")) {
                flag = false;
            }
            else {
                xValue = Integer.parseInt(indexes.split(", ")[0]);
                yValue = Integer.parseInt(indexes.split(", ")[1]);
                if((xValue>n-1) || (xValue<0) || (yValue>m-1) || (yValue<0)) {
                    System.out.println("The cell is not within the board’s boundaries, enter a new cell.");
                    printEnterIndexes = false;
                }
                else if(board[xValue][yValue] == 0 ) board[xValue][yValue] = 1;
                else board[xValue][yValue] = 0;
            }
        }
        return board;

    }

    /**
     * Prints board.
     *
     * @param board matrix of current semester students validity
     * @param m     number of rows in board
     * @param n     number of columns in board
     */
    public static void printBoard(int[][] board , int m , int n) {
        for (int i = 0 ; i < m ; i ++) {
            for(int j = 0 ; j < n ; j++) {
                if(board[i][j] == 0) {
                    System.out.print("▯");
                }
                else System.out.print("▮");
            }
            System.out.println();

        }

    }

    /**
     * Returns integer representing the number of valid cells around a specific cell in given array.
     *
     * @param board matrix of current semester students validity
     * @param m     number of rows in board
     * @param n     number of columns in board
     * @param x     row number of a cell in board being
     * @param y     column number of cell in board
     * @return      number of valid students around specific student
     */
    public static int validAroundCell(int[][] board , int m , int n, int x , int y) {
            int counter = 0;
            for (int i = max(x-1,0) ; i < min(x+2 , n-1) ; i++) {
                for (int j = max(y-1 , 0) ; j < min(y+2 , m-1) ; j++) {
                    if (i == x && j == y) continue;
                    else if(board[i][j] == 1) counter++;
                }
            }
            return counter;
        }

    /**
     * Updates board array according to certain conditions
     *
     * @param board matrix of current semester students validity
     * @param m     number of rows in game board
     * @param n     number of columns in game board
     */
    public static void updateBoard(int[][] board , int m , int n) {
            int counter = 0;
            int[][] tempBoard = new int[m][n];
            for (int i = 1 ; i < m ; i ++) {
                for (int j = 1 ; j < n ; j ++) {
                    counter = validAroundCell(board ,m ,n , i , j);
                    //conditions for updating the cells in the original board
                    if((board[i][j] == 1)&&(counter <= 1)) {
                        tempBoard[i][j] = 0;
                    }
                    else if((board[i][j] == 1)&&(counter >= 2)&&(counter <= 3)) {
                        tempBoard[i][j] = 1;
                    }
                    else if((board[i][j] == 1)&&(counter > 3)) {
                        tempBoard[i][j] = 0;
                    }
                    else if((board[i][j] == 0)&&(counter == 3)) {
                        tempBoard[i][j] = 1;
                    }
                    else if((board[i][j] == 0)&&(counter == 3)) {
                        tempBoard[i][j] = 1;
                    }
                    else if((board[i][j] == 0)&&(counter != 3)) {
                        tempBoard[i][j] = 0;
                    }


                }
            }
            copyBoard(tempBoard, board,m, n);
        }

    /**
     * Returns whether two semesters have the same valid\invalid students
     *
     * @param board1 matrix of current semester students validity
     * @param board2 matrix of previous semester students validity
     * @param m      number of rows in boards
     * @param n      number of columns in boards
     * @return       whether the boards are the same or not
     */
    public static boolean compareBoards(int[][] board1 , int[][] board2 , int m , int n) {
        for(int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if(board1[i][j] != board2[i][j]) return false;
            }
        }
        return true;
    }

    /**
     * Plays student game once
     *
     * @param board matrix of current semester students validity
     * @param m     number of rows in board
     * @param n     number of columns in board
     */
    public static void theStudentsGame(int[][] board , int m , int n) {
        int done = 0;
        int round = 1;
        int [][] prevBoard = new int [m][n];
        System.out.println("Semester number " + round + ":");
        printBoard(board, m , n);
        System.out.println("Number of students: " + validStudents(board, m, n)+"\n");
        done = gameOver(board, prevBoard, n, m, round);
        while (done == 0) {
            copyBoard(board, prevBoard, m, n);
            updateBoard(board, m, n);
            round++;
            if (!compareBoards(board , prevBoard , m , n)) {
                System.out.println("Semester number " + round + ":");
                printBoard(board, m, n);
                System.out.println("Number of students: " + validStudents(board, m, n)+"\n");;
            }
            done = gameOver(board, prevBoard, n, m, round);
        }
    }

    /**
     * Copies current semester board into previous semster board
     *
     * @param board     matrix of current semester students validity
     * @param prevBoard board of the previous semester
     * @param m         number of rows in board
     * @param n         number of columns in board
     */
    public static void copyBoard(int [][] board, int [][] prevBoard, int m, int n) {
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                prevBoard[i][j] = board[i][j];
            }
        }
    }

    /**
     * @param board matrix of current semester students validity
     * @param m     number of rows in board
     * @param n     number of columns in board
     * @return      number of valid students in current semester
     */
    public static int validStudents(int [][] board, int m, int n) {
        int counter = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] != 0) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * @param args input file for game
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String path = args[0];
        scanner = new Scanner(new File(path));
        //scanner = new Scanner(System.in);
        int numberOfGames = scanner.nextInt();
        scanner.nextLine();
        int playedGames = 0;
        int m,n;
        for (int i = 1; i <= numberOfGames; i++) {
            System.out.println("Game number " + i + " starts.");
            int[][] board = settingTheBoard();
            theStudentsGame(board , board.length, board[0].length);
            System.out.println("Game number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }
        System.out.println("All games are ended.");
    }

    /**
     *
     * @param board     matrix of current semester students validity
     * @param prevBoard matrix of previous semester students validity
     * @param n         number of columns in board
     * @param m         number of rows in board
     * @param round     semester number
     * @return          whether game ending conditions have been met
     */
    public static int gameOver(int board[][], int [][] prevBoard, int n, int m, int round) {
        boolean zeros = true;
        if(round >= 1000) {
            System.out.println("The semesters limitation is over.");
            return 1;
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if( board[i][j] != 0) {
                    zeros = false;
                }
            }
        }
        if(zeros == true) {
            System.out.println("There are no more students.");
            return 1;
        }
        if(compareBoards(board, prevBoard, m, n)) {
            System.out.println("The students have stabilized.");
            return 1;
        }
        return 0;
    }
}



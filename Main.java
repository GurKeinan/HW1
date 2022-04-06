import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main
{
    public static Scanner scanner;  // Note: Do not change this line.

    public static void settingTheBoard(int[][] board , int m , int n)
    {
        int Letters = 'z'-'a'+1;
        boolean flag;
        String indexes;
        int x_value;
        int y_value;


        for(int i = 0 ; i < m ; i++)
        {
            for(int j = 0 ; j < n ; j++)
            {
                board[i][j] = 0;
            }
        }

        flag = true;

        while (flag)
        {
            System.out.println("Dear president, please enter the cell's indexes.");
            indexes = scanner.nextLine();
            if (indexes.equals("Yokra"))
            {
                flag = false;
            }
            else
            {
                x_value = (indexes.charAt(0))-48;
                y_value = (indexes.charAt(3))-48;

                if((x_value>n-1) || (x_value<0) || (y_value>m-1) || (y_value<0))
                {
                    System.out.println("The cell is not within the board’s boundaries, enter a new cell.");
                }


                else if(board[x_value][y_value] == 0 ) board[x_value][y_value] = 1;

                else board[x_value][y_value] = 0;

            }
        }

    }

    public static void printBoard(int[][] board , int m , int n)
    {
        for (int i = 0 ; i < m ; i ++)
        {
            System.out.println();
            for(int j = 0 ; j < n ; j++)
            {
                if(board[i][j] == 0)
                {
                    System.out.print("▯");
                }
                else System.out.print("▮");
            }

        }
        System.out.println();

        }

        public static int[] validAroundCell(int[][] board , int x , int y)
        {
            int[] counters = {0,0};
            for (int i = x-1 ; i < x+2 ; i++)
            {
                for (int j = y-1 ; j < y+2 ; j++)
                {
                    if (i == x && j == y) continue;
                    else if(board[i][j] == 1) counters[0]+=1;
                    else if(board[i][j] == 0) counters[1]+=1;
                }
            }
            return counters;
        }
        public static void updateBoard(int[][] board , int m , int n)
        {
            int[] counters = {0,0};
            int[][] temp_board = new int[m+2][n+2];
            for(int i = 0 ; i < m+2 ; i++)
            {
                temp_board[i][0] = -1;
                temp_board[i][n+1] = -1;
            }
            for(int i = 0 ; i < n+2 ; i++)
            {
                temp_board[0][i] = -1;
                temp_board[m+1][i] = -1;
            }

            for (int i = 1 ; i < m+2 ; i ++)
            {
                for (int j = 1 ; j < n+2 ; j ++)
                {
                    counters = validAroundCell(board , j , i);
                    //conditions for updating the cells in the original board
                }
            }
        }


    public static void theStudentsGame(int[][] board , int m , int n)
    {
        int done = 1;
        int round = 1;
        int [][] prev_board = new int [m][n];
        while (done == 1)
        {
            System.out.println("semester number " + round);
            printBoard(board, m, n);
            System.out.println("Number of students: " + validStudents(board, m, n));
            copyBoard(board, prev_board, m, n);
            updateBoard(board, m, n);
            done = gameOver(board, prev_board, n, m, round);
            round++;
        }

    }

    public static void copyBoard(int [][] board, int [][] prev_board, int m, int n)
    {
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                prev_board[i][j] = board[i][j];
            }
        }
    }

    public static int validStudents(int [][] board, int m, int n) {
        int counter = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(board[i][j] != 0)
                {
                    counter++;
                }
            }
        }
        return counter;
    }








    public static void main(String[] args) throws IOException
    {
        String path = args[0];
        scanner = new Scanner(new File(path));
        //scanner = new Scanner(System.in);
        int numberOfGames = scanner.nextInt();
        scanner.nextLine();
        int playedGames = 0;
        int m,n;
        System.out.println("Dear president, please enter the board’s size");
        String board_size = scanner.nextLine();
        m = board_size.charAt(0) - 48;
        n = board_size.charAt(4) - 48;
        int[][] board = new int[m][n];
        settingTheBoard(board , m , n);



        for (int i = 1; i <= numberOfGames; i++) {
            System.out.println("Game number " + i + " starts.");
            theStudentsGame(board , m , n);
            System.out.println("Game number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }
        System.out.println("All games are ended.");
    }

    public static int gameOver(int board[][], int [][] prev_board, int n, int m, int round)
    {
        boolean same = true;
        if(round >= 1000)
        {
            System.out.println("The semesters limitation is over.");
            return 1;
        }
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(board[i][j] != prev_board[i][j])
                {
                    same = false;
                }
                if( board[i][j] != 0)
                {
                    return 0;
                }
            }
        }
        if(same)
        {
            System.out.println("The students have stabilized.");
            return 1;
        }
        System.out.println("There are no more students.");
        return 1;


    }
}



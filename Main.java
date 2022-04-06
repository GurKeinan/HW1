import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main
{
    public static Scanner scanner;  // Note: Do not change this line.



    public static int max(int x , int y)
    {
        return ((x>y) ? x:y);
    }
    public static int min(int x , int y)
    {
        return ((x<y) ? x:y);
    }
    public static int[][] settingTheBoard()
    {

        boolean flag;
        String indexes;
        int x_value;
        int y_value;

        System.out.println("Dear president, please enter the board’s size");
        String board_size = scanner.nextLine();
        int m = board_size.charAt(0) - 48;
        int n = board_size.charAt(4) - 48;
        int[][] board = new int[m][n];


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
        return board;

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
    public static int validAroundCell(int[][] board , int m , int n, int x , int y)
        {
            int counter = 0;
            for (int i = max(x-1,0) ; i < min(x+2 , n-1) ; i++)
            {
                for (int j = max(y-1 , 0) ; j < min(y+2 , m-1) ; j++)

                {
                    if (i == x && j == y) continue;
                    else if(board[i][j] == 1) counter++;
                }
            }
            return counter;
        }
        public static void updateBoard(int[][] board , int m , int n)
        {
            int counter = 0;
            int[][] temp_board = new int[m][n];

            for (int i = 1 ; i < m ; i ++)
            {
                for (int j = 1 ; j < n ; j ++)
                {
                    counter = validAroundCell(board ,m ,n , j , i);
                    //conditions for updating the cells in the original board
                    if((board[i][j] == 1)&&(counter <= 1))
                    {
                        temp_board[i][j] = 0;
                    }
                    else if((board[i][j] == 1)&&(counter >= 2)&&(counter <= 3))
                    {
                        temp_board[i][j] = 1;
                    }
                    else if((board[i][j] == 1)&&(counter > 3))
                    {
                        temp_board[i][j] = 0;
                    }
                    else if((board[i][j] == 0)&&(counter == 3))
                    {
                        temp_board[i][j] = 1;
                    }
                    else if((board[i][j] == 0)&&(counter == 3))
                    {
                        temp_board[i][j] = 1;
                    }
                    else if((board[i][j] == 0)&&(counter != 3))
                    {
                        temp_board[i][j] = 0;
                    }


                }
            }
            copyBoard(temp_board, board,m, n);
        }


    public static void theStudentsGame(int[][] board , int m , int n , int numOfGames)
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

        int[][] board = settingTheBoard();



        for (int i = 1; i <= numberOfGames; i++) {
            System.out.println("Game number " + i + " starts.");
            theStudentsGame(board , board.length, board[0].length, numberOfGames);
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



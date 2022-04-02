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
                x_value = (indexes.charAt(0))-Letters;
                y_value = (indexes.charAt(3))-Letters;

                if((x_value>n-1) || (x_value<0) || (y_value>m-1) || (y_value<0))
                {
                    System.out.println("The cell is not within the board’s boundaries, enter a new cell.");
                }


                else if(board[x_value][y_value] == 0 ) board[x_value][y_value] = 1;

                else board[x_value][y_value] = 0;

            }
        }

    }


    public static void theStudentsGame()
    {

    }

    public static void main(String[] args) throws IOException
    {
        int Letters = 'z'-'a'+1;
        String path = args[0];
        scanner = new Scanner(new File(path));
        int numberOfGames = scanner.nextInt();
        int m,n;
        System.out.println("Dear president, please enter the board’s size");
        String board_size = scanner.nextLine();
        m = board_size.charAt(0) - Letters;
        n = board_size.charAt(4) - Letters;
        int[][] board = new int[m][n];
        settingTheBoard(board , m , n);

        for (int i = 1; i <= numberOfGames; i++) {
            System.out.println("Game number " + i + " starts.");
            theStudentsGame();
            System.out.println("Game number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }
        System.out.println("All games are ended.");
    }
}



import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main
{
    public static Scanner scanner;  // Note: Do not change this line.

    public static void theStudentsGame()
    {
        int Letters = 'z'-'a'+1;
        int m;
        int n;
        String board_size;
        boolean flag;
        String indexes;
        int x_value;
        int y_value;
        int[][] board;

        System.out.println("Dear president, please enter the board’s size");
        board_size = scanner.nextLine();
        m = (board_size.charAt(0))-Letters;
        n = (board_size.charAt(board_size.length()-1))-Letters;

        board = new int[m][n];
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
            System.out.println("Dear president, please enter the cell's indexes");
            indexes = scanner.nextLine();
            if (indexes.equals("Yokra"))
            {
                flag = false;
            }
            else
            {
                x_value = (indexes.charAt(0))-Letters;
                y_value = (indexes.charAt(3))-Letters;

                if((x_value>n-1) || (x_value<0) || (y_value>m-1) || (y_value<0)) flag = false;

                else if(board[x_value][y_value] == 0 ) board[x_value][y_value] = 1;

                else board[x_value][y_value] = 0;

            }
        }


    }

    public static void main(String[] args) throws IOException
    {
        String path = args[0];
        scanner = new Scanner(new File(path));
        int numberOfGames = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numberOfGames; i++) {
            System.out.println("Game number " + i + " starts.");
            theStudentsGame();
            System.out.println("Game number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }
        System.out.println("All games are ended.");
    }
}



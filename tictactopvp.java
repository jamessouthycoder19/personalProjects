import java.util.Scanner;
public class tictactopvp {

    public static void main(String[] args)
    {
        try (Scanner scan = new Scanner(System.in)) {
            char[][] board = { {'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
            int pzazz = 1;
            int checker = 0;
            int counter = 0;
            int userChoice;
            int playerTurn;
            System.out.println("Player 1 you are X");
            System.out.println("Player 2 you are O");
            System.out.println("Who will be playing first? 1 or 2?");
            playerTurn = scan.nextInt();
            while (playerTurn != 1 && playerTurn != 2){
                System.out.println("Inavlid response, put either 1 or 2");
                playerTurn = scan.nextInt();
            }
            System.out.println("");
            for (int i = 0; i<3; i++){
                for (int j = 0; j<3; j++){
                    System.out.print(board[i][j] + " ");
                }
                System.out.println("");
            }
            System.out.println("");
            pzazz = 1;
            while (pzazz == 1){
                if (playerTurn == 1){
                    System.out.println("Player 1, type 1-9 to indicate where you would like to go");
                    userChoice = scan.nextInt();
                    System.out.println("");
                    while (checker == 0){
                        if(userChoice==1||userChoice==2||userChoice==3){
                            if(board[0][userChoice-1]== 'X' || board[0][userChoice-1] == 'O'){
                                System.out.println("Spot is already occupied, pick again");
                                userChoice = scan.nextInt();
                                System.out.println("");
                            }
                            else{
                                checker = 1;
                                board[0][userChoice-1] = 'X';
                            }
                    
                        }
                        else if (userChoice==4||userChoice==5||userChoice==6){
                            if(board[1][userChoice-4]== 'X' || board[1][userChoice-4] == 'O'){
                                System.out.println("Spot is already occupied, pick again");
                                userChoice = scan.nextInt();
                                System.out.println("");
                            }
                            else{
                                checker = 1;
                                board[1][userChoice-4] = 'X';
                            }
                        }
                        else{
                            if(board[2][userChoice-7]== 'X' || board[2][userChoice-7] == 'O'){
                                System.out.println("Spot is already occupied, pick again");
                                userChoice = scan.nextInt();
                                System.out.println("");
                            }
                            else{
                                checker = 1;
                                board[2][userChoice-7] = 'X';
                            }
                        }
                    }
                    checker = 0;
                    playerTurn++;
                }
                else{
                    System.out.println("Player 2, type 1-9 to indicate where you would like to go");
                    userChoice = scan.nextInt();
                    System.out.println("");
                    while (checker == 0){
                        if(userChoice==1||userChoice==2||userChoice==3){
                            if(board[0][userChoice-1]== 'X' || board[0][userChoice-1] == 'O'){
                                System.out.println("Spot is already occupied, pick again");
                                userChoice = scan.nextInt();
                                System.out.println("");
                            }
                            else{
                                checker = 1;
                                board[0][userChoice-1] = 'O';
                            }
                    
                        }
                        else if (userChoice==4||userChoice==5||userChoice==6){
                            if(board[1][userChoice-4]== 'X' || board[1][userChoice-4] == 'O'){
                                System.out.println("Spot is already occupied, pick again");
                                userChoice = scan.nextInt();
                                System.out.println("");
                            }
                            else{
                                checker = 1;
                                board[1][userChoice-4] = 'O';
                            }
                        }
                        else{
                            if(board[2][userChoice-7]== 'X' || board[2][userChoice-7] == 'O'){
                                System.out.println("Spot is already occupied, pick again");
                                userChoice = scan.nextInt();
                                System.out.println("");
                            }
                            else{
                                checker = 1;
                                board[2][userChoice-7] = 'O';
                            }
                        }
                    }
                    checker = 0;
                    playerTurn--;
                }
                // prints board
                for (int i = 0; i<3; i++){
                    for (int j = 0; j<3; j++){
                        System.out.print(board[i][j] + " ");
                    }
                    System.out.println("");
                }
                System.out.println("");
                
                //check for win
                for (int i = 0;i<3;i++){
                    if (board[i][0]==board[i][1]&& board[i][1]==board[i][2]){
                        if(board[i][0] == 'X'){
                            System.out.println("Player 1 Wins!");
                            pzazz = 0;
                        }
                        else{
                            System.out.println("Player 2 wins!");
                            pzazz = 0;
                            
                        }
                    }
                    else if (board[0][i]==board[1][i]&& board[1][i]==board[2][i]){
                        if(board[0][i] == 'X'){
                            System.out.println("Player 1 wins!");
                            pzazz = 0;
                            
                        }
                        else{
                            System.out.println("Player 2 wins!");
                            pzazz = 0;
                        }
                    }
                }
                if((board[0][0]==board[1][1]&& board[1][1]==board[2][2]) || (board[0][2]==board[1][1]&& board[1][1]==board[2][0])){
                    if(board[1][1] == 'X'){
                            System.out.println("Player 1 wins!");
                            pzazz = 0;
                        }
                        else{
                            System.out.println("Player 2 wins!");
                            pzazz = 0;
                        }
                }
                for(int t =0;t<3;t++){
                    for(int y=0;y<3;y++){
                        if (board[t][y] == 'O' || board[t][y] == 'X'){
                            counter++;
                        }
                    }
                }
                if(counter == 9 && pzazz == 1){
                    System.out.println("Cat game :(");
                    pzazz = 0;
                }
                counter = 0;
            }
        }
        
    }
}
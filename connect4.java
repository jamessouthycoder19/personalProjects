import java.util.Scanner;
public class connect4 {
    
    public static void main(String[] args){
        char board[][] = new char[8][7];
        int num;
        char a;
        int turn = 1;
        int column = 0;
        int countx = 0;
        int counto = 0;
        int thingy;
        try(Scanner scan = new Scanner(System.in)){
            for(int i = 1;i<8;i++){
                num = i;
                a = (char)(num + '0');
                board[0][i-1] = a;
            }
            for(int i = 1;i<8;i++){
                for(int j = 0;j<7;j++){
                    board[i][j] = '-';
                }
            }
            for(int i = 0;i<15;i++){
                printBoard(board);
                turn(board,turn,column,scan);
                if(turn == 1){
                    turn++;
                }
                else{
                    turn--;
                }
                thingy = checkWin(board,countx,counto);
                if(thingy == 1){
                    System.out.println("Player 1 wins!");
                }
                else if(thingy == 2){
                    System.out.println("Player 2 wins!");
                }
            }
        }
    }

    public static void printBoard(char board[][]){
        for(int i = 0;i<8;i++){
            for(int j = 0;j<7;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public static void turn(char board[][],int turn,int column,Scanner scan){
        int thingy = 0;
        int counter = 0;
        while(thingy == 0){
            counter = 0;
            System.out.println("Player " + turn + " pick a column");
            column = scan.nextInt();
            for(int i = 7;i>0;i--){
                if(board[i][column-1] == '-'){
                    if(turn == 1){
                        board[i][column-1] = 'X';
                    }
                    else{
                        board[i][column-1] = 'O';
                    }
                    i = 1;
                    thingy = 1;
                }
                else{
                    counter++;
                }
            }
            if(counter == 7){
                System.out.println("column is full");
            }
        }
            
        
        
    }
    public static int checkWin(char board[][],int countx,int counto){
        countx = 0;
        counto = 0;
        // check vertical
        for(int i = 0;i<7;i++){
            for(int j = 1;j<5;j++){
                for(int q = 0;q<4;q++){
                    if(board[j+q][i] == 'X'){
                        countx++;
                    }
                    else if(board[j+q][i] == 'O'){
                        counto++;
                    }
                }
                if(countx == 4){
                    return 1;
                }
                else if(counto == 4){
                    return 2;
                }
                countx = 0;
                counto = 0;
            }
        }
        // horizontal
        // diagnal
        return 0;
    }
    
    

}
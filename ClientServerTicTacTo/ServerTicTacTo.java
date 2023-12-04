package ClientServerTicTacTo;

import java.io.IOException;

public class ServerTicTacTo extends TicTacTo{
    private boolean gameOver = false;

    public ServerTicTacTo(int port) throws IOException{
        super(port,"X");
    }

    public void playGame() throws IOException{
        printBoard();
        while(!gameOver){
            System.out.println();
            System.out.println("Opponent's move:");
            oppTurn();
            printBoard();
            System.out.println();
            if(checkWin()){
                gameOver = true;
                System.out.println("You have lost :(");
                break;
            }
            myTurn();
            System.out.println();
            printBoard();
            if(checkWin()){
                System.out.println();
                gameOver = true;
                System.out.println("You have Won!");
            }
        }
    }

    public static void main(String[] args) throws IOException{
        ServerTicTacTo server = new ServerTicTacTo(1738);
        server.playGame();
    }
    
}

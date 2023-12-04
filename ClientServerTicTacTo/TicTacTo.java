package ClientServerTicTacTo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public abstract class TicTacTo {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String[][] board;
    private Scanner userScanner;
    private String team;
    private String opponent;

    public TicTacTo(String host, int port,String team) throws IOException{
        this.socket = new Socket(host,port);
        this.serverSocket = null;

        InputStream in = socket.getInputStream();
        InputStreamReader ir = new InputStreamReader(in);
        this.reader = new BufferedReader(ir);

        OutputStream out = socket.getOutputStream();
        this.writer = new PrintWriter(out);

        this.board = new String[3][3];
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                board[i][j] = "-";
            }
        }
        this.userScanner = new Scanner(System.in);
        this.team = team;
        if(this.team.equals("X")){
            this.opponent = "O";
        } else {
            this.opponent= "X";
        }
    }
    public TicTacTo(int port,String team) throws IOException{
        this.serverSocket = new ServerSocket(port);
        this.socket = serverSocket.accept();

        InputStream in = socket.getInputStream();
        InputStreamReader ir = new InputStreamReader(in);
        this.reader = new BufferedReader(ir);

        OutputStream out = socket.getOutputStream();
        this.writer = new PrintWriter(out);

        //creates and fills blank board
        this.board = new String[3][3];
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                board[i][j] = "-";
            }
        }

        this.userScanner = new Scanner(System.in);
        this.team = team;
        if(this.team.equals("X")){
            this.opponent = "O";
        } else {
            this.opponent= "X";
        }
    }

    public void myTurn(){
        System.out.println("Pick a spot (1-9)");

        //validates user choice
        int userChoice = -1;
        while(userChoice == -1){
            userChoice = userScanner.nextInt();
            userChoice--;
            if(userChoice < 0 || userChoice > 8){
                System.out.println("Pick a number 1-9");
                userChoice = -1;
            } else if(!board[((int)userChoice/3)][userChoice % 3].equals("-")){
                System.out.println("Spot already taken");
                userChoice = -1;
            }
        }
        //updates board
        board[((int)userChoice/3)][userChoice % 3] = team;
        //sends choice to other user
        send(userChoice);
    }

    public void oppTurn() throws IOException{
        int oppChoice = receive();
        board[((int)oppChoice/3)][oppChoice % 3] = opponent;
    }

    public boolean checkWin(){
        for(int i = 0;i<3;i++){
            if(!board[i][0].equals("-") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])){
                return true;
            }else if(!board[0][i].equals("-") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])){
                return true;
            }
        }
        if(!board[0][0].equals("-") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])){
            return true;
        } else if(!board[0][2].equals("-") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])){
            return true;
        }
        return false;
    }

    private void send(int spot){
        writer.println(spot);
        writer.flush();
    }

    private int receive() throws IOException{
        return Integer.parseInt(reader.readLine());
    }

    public void printBoard(){
        for(String[] line : board){
            for(String spot : line){
                System.out.print(spot + " ");
            }
            System.out.println();
        }
    }

    public void close() throws IOException{
        if(serverSocket!=null){
            serverSocket.close();
        }
        socket.close();
        writer.close();
        writer.close();
    }

    public abstract void playGame() throws IOException;
}

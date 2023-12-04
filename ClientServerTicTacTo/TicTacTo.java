package ClientServerTicTacTo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TicTacTo {
    private ServerSocket serverSocket;
    private Socket socket;
    private Scanner scanner;
    private PrintWriter writer;
    private String[][] board;
    private Scanner userScanner;
    private String team;
    private String opponent;

    public TicTacTo(String host, int port,String team) throws IOException{
        this.socket = new Socket(host,port);
        this.serverSocket = null;

        InputStream in = socket.getInputStream();
        this.scanner = new Scanner(in);

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
        this.scanner = new Scanner(in);

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
    }

    public void myTurn(){
        System.out.println("Pick a spot (1-9)");

        //validates user choice
        int userChoice = 0;
        while(userChoice == 0){
            userChoice = userScanner.nextInt();
            if(userChoice < 1 || userChoice > 9){
                System.out.println("Pick a number 1-9");
                userChoice = 0;
            } else if(!board[((int)userChoice/3)-1][userChoice % 3].equals("-")){
                System.out.println("Spot already taken");
                userChoice = 0;
            }
        }
        //updates board
        board[((int)userChoice/3)-1][userChoice % 3] = team;
        //sends choice to other user
        send(userChoice);
    }

    public void oppTurn(){
        int oppChoice = receive();
        board[((int)oppChoice/3)-1][oppChoice % 3] = opponent;
    }

    public int checkWin(){
        //final method left to implement
        return 0;
    }

    private void send(int spot){
        writer.println(spot);
        writer.flush();
    }

    private int receive(){
        return scanner.nextInt();
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
        scanner.close();
        writer.close();
    }
}

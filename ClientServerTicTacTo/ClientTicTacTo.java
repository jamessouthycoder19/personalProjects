package ClientServerTicTacTo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTicTacTo {
    private Socket socket;
    private Scanner scanner;
    private PrintWriter writer;
    private String[][] board;

    public ClientTicTacTo(String host,int port) throws IOException{
        this.socket = new Socket(host,port);

        InputStream in = socket.getInputStream();
        this.scanner = new Scanner(in);

        OutputStream out = socket.getOutputStream();
        this.writer = new PrintWriter(out);

        //Creates the board and gives each spot a number 1-9
        this.board = new String[3][3];
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                board[i][j] = "-";
            }
        }
    }

    private void send(int spot){
        writer.println(spot);
        writer.flush();
    }

    private int receive(){
        return scanner.nextInt();
    }

    public void close() throws IOException{
        socket.close();
        scanner.close();
        writer.close();
    }

    public static void main(String[] args) {
        
    }
}

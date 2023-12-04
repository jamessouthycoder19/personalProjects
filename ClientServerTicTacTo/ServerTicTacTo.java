package ClientServerTicTacTo;

import java.io.IOException;

public class ServerTicTacTo extends TicTacTo{

    public ServerTicTacTo(int port) throws IOException{
        super(port,"X");
    }

    public static void main(String[] args) throws IOException{
        ServerTicTacTo server = new ServerTicTacTo(1738);
        server.printBoard();
        server.close();
    }
    
}

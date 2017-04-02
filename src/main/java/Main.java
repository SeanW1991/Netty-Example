import com.sean.net.AbstractClient;
import com.sean.net.SocketContext;
import com.sean.net.AbstractServer;
import com.sean.net.tcp.client.TCPClient;
import com.sean.net.tcp.server.TCPServer;

import java.net.InetSocketAddress;

/**
 * Created by sean on 04/04/16.
 */
public final class Main {

    /**
     * Starts the application.
     * @param args The arguments for the application.
     */
    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        AbstractServer server = new TCPServer(new SocketContext(new InetSocketAddress(80)));
        server.configure().bind();
        try {
            AbstractClient client = new TCPClient(new SocketContext(new InetSocketAddress(80)));
            client.configure().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

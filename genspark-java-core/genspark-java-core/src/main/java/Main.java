import reload_system.session.Session;
import reload_system.watch_for_reloads.FileWatcher;
import reload_system.watch_for_reloads.ObjectRefs;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends WebSocketServer {

    public static void main(String[] args) {
        ObjectRefs.loadObjects();
        String host = "localhost";
        int port = 8887;
        WebSocketServer server = new Main(new InetSocketAddress(host, port));
        server.setReuseAddr(true);
        Path reloadDir = Paths.get(System.getProperty("user.dir") + "/src/main/java/reload_system/reload/");
        File reloadFile = Paths.get(System.getProperty("user.dir") + "/src/main/java/reload_system/reload/reload.log").toFile();
        new FileWatcher(reloadFile, reloadDir).watch();
        server.run();
    }
    public Main(InetSocketAddress address) {
        super(address);
    }
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        Session.session = conn;
        conn.send("{\"type\": \"connection\", \"message\": \"Welcome to the server!\"}");
        System.out.println("new connection to " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        Session.sendMessage(backend.harness.messageFromGui(message));
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message ) {
        System.out.println("received ByteBuffer from "    + conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
    }

    @Override
    public void onStart() {
        System.out.println("server started successfully");
    }
}

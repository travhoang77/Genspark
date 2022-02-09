package reload_system.session;

import org.java_websocket.WebSocket;

public class Session {
    public static WebSocket session;
    public static void sendMessage(String msg){
        session.send(msg);
    }
}

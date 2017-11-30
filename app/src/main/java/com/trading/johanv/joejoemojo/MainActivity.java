package com.trading.johanv.joejoemojo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity {
    public static WebSocketClient webSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectWebSocket();
    }

    private void connectWebSocket() {
        URI uri;

        try {
            uri = new URI("wss://ws.binary.com/websockets/v3?app_id=11543");
            Log.println(Log.INFO, "URI", uri.toString());
        } catch (URISyntaxException uriEx) {
            uriEx.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handShakeData) {
                webSocketClient.send("ping: 1");
            }

            @Override
            public void onMessage(String message) {

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
        };

        try {
            webSocketClient.connect();
            Log.println(Log.INFO,"WebSocket", "CONNECTION SUCCESSFUL");
        } catch (Exception webEx) {
            Log.println(Log.ERROR, "WebSocket", "not connecting" + webEx.getMessage());
        }
    }
}

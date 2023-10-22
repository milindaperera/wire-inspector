package org.odyssey.tools.wire;

import org.odyssey.tools.wire.core.WireInspectorException;
import org.odyssey.tools.wire.tcp.TCPClient;
import org.odyssey.tools.wire.tcp.TCPRequestProcessor;
import org.odyssey.tools.wire.tcp.TCPServer;

public class ClientMain {
    public static void main(String[] args) {
        System.out.println("Client Main ... ");
        TCPClient client = new TCPClient("localhost", 8080);
        try {
            client.connect();
            client.send(null);
        } catch (WireInspectorException e) {
            throw new RuntimeException(e);
        }
        //client.send(null);
    }
}
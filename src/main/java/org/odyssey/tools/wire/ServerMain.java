package org.odyssey.tools.wire;

import org.odyssey.tools.wire.core.WireInspectorException;
import org.odyssey.tools.wire.tcp.TCPProxyProcessor;
import org.odyssey.tools.wire.tcp.TCPRequestProcessor;
import org.odyssey.tools.wire.tcp.TCPServer;

public class ServerMain {
    public static void main(String[] args) {
        TCPServer tcpServer = new TCPServer(8080);
        try {
            tcpServer.start();
            //tcpServer.listen(new TCPRequestProcessor());
            tcpServer.listen(new TCPProxyProcessor("localhost", 8080));
        } catch (WireInspectorException e) {
            throw new RuntimeException(e);
        }
    }
}
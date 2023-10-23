package org.odyssey.tools.wire;

import org.odyssey.tools.wire.core.Starter;
import org.odyssey.tools.wire.core.WireInspectorException;
import org.odyssey.tools.wire.tcp.TCPProxyProcessor;
import org.odyssey.tools.wire.tcp.TCPRequestProcessor;
import org.odyssey.tools.wire.tcp.TCPServer;

public class ServerStarter implements Starter {

    @Override
    public void start(String[] args) {
        System.out.println("Starting SERVER");
        TCPServer tcpServer = new TCPServer(8080);
        try {
            tcpServer.start();
            tcpServer.listen(new TCPRequestProcessor());
        } catch (WireInspectorException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.odyssey.tools.wire;

import org.odyssey.tools.wire.core.Starter;
import org.odyssey.tools.wire.core.WireInspectorException;
import org.odyssey.tools.wire.tcp.TCPProxyProcessor;
import org.odyssey.tools.wire.tcp.TCPServer;

public class ProxyStarter implements Starter {
    @Override
    public void start(String[] args) {
        System.out.println("Starting PROXY SERVER");
        TCPServer tcpServer = new TCPServer(8081);
        try {
            tcpServer.start();
            tcpServer.listen(new TCPProxyProcessor("localhost", 8080));
        } catch (WireInspectorException e) {
            throw new RuntimeException(e);
        }
    }
}

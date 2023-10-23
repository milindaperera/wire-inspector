package org.odyssey.tools.wire;

import org.odyssey.tools.wire.core.Starter;
import org.odyssey.tools.wire.core.StringMessage;
import org.odyssey.tools.wire.core.WireInspectorException;
import org.odyssey.tools.wire.tcp.TCPClient;

public class ClientStarter implements Starter {
    @Override
    public void start(String[] args) {
        TCPClient client = new TCPClient("localhost", 8081);
        try {
            client.connect();
            client.send(new StringMessage("Test Message1"));
            client.send(new StringMessage("Test Message2"));
        } catch (WireInspectorException e) {
            throw new RuntimeException(e);
        }
    }
}

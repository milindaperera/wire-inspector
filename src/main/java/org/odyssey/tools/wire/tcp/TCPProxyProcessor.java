package org.odyssey.tools.wire.tcp;

import org.odyssey.tools.wire.core.Message;
import org.odyssey.tools.wire.core.RequestProcessor;
import org.odyssey.tools.wire.core.SocketMessage;
import org.odyssey.tools.wire.core.StringMessage;
import org.odyssey.tools.wire.core.WireInspectorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TCPProxyProcessor extends RequestProcessor {

    private final Queue<String> queue = new ConcurrentLinkedQueue<>();
    private final TCPClient tcpClient;

    public TCPProxyProcessor(String targetHost, int targetPort) {
        this.tcpClient = new TCPClient(targetHost, targetPort);
        try {
            this.tcpClient.connect();
        } catch (WireInspectorException e) {
            throw new RuntimeException(e);
        }
        Thread senderThread = new Thread(new SenderRunnable(this.queue, this.tcpClient), "ProxySenderThread");
        senderThread.start();
    }

    @Override
    public void processRequest(Message message) {
        if (message instanceof SocketMessage) {
            System.out.println("Client Connected:" + ((SocketMessage) message).getSocket().getPort());
            try {
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(((SocketMessage) message).getSocket().getInputStream()));
                do {
                    String msg = in.readLine();
                    System.out.println("Message Received: " + msg);
                    this.queue.add(msg);
                } while (in.ready());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Unknown message");
        }
    }
}

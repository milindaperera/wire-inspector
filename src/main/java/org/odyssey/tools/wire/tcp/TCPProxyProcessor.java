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
                    System.out.println("message: test");
                    String msg = in.readLine();
                    System.out.println("message: " + msg);
                    this.queue.add(msg);
                } while (in.ready());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Unknown message");
        }
    }

    public class SenderRunnable implements Runnable {
        private final Queue<String> queueRef;
        private final TCPClient client;
        private int count = 0;

        public SenderRunnable(Queue<String> queueRef, TCPClient client) {
            this.queueRef = queueRef;
            this.client = client;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (this.queueRef.size() > 0) {
                    this.client.send(new StringMessage(this.queueRef.poll() + this.count++));
                } else {
                    System.out.println("Queue empty");
                }
            }
        }
    }
}

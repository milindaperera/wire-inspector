package org.odyssey.tools.wire.tcp;

import org.odyssey.tools.wire.core.StringMessage;

import java.util.Queue;

public class SenderRunnable implements Runnable {
    private final Queue<String> queueRef;
    private final TCPClient client;

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
                System.out.println("Sending message : " + this.queueRef.peek());
                this.client.send(new StringMessage(this.queueRef.poll()));
            } else {
                System.out.println("Queue empty");
            }
        }
    }
}

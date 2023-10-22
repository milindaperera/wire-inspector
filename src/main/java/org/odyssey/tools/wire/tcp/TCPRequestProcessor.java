package org.odyssey.tools.wire.tcp;

import org.odyssey.tools.wire.core.Message;
import org.odyssey.tools.wire.core.RequestProcessor;
import org.odyssey.tools.wire.core.SocketMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TCPRequestProcessor extends RequestProcessor {
    @Override
    public void processRequest(Message message) {
        if (message instanceof SocketMessage) {
            System.out.println("Message received");
            try {
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(((SocketMessage) message).getSocket().getInputStream()));

                do {
                    String greeting = in.readLine();
                    System.out.println(greeting);
                } while (in.ready());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Unknown message");
        }


    }
}

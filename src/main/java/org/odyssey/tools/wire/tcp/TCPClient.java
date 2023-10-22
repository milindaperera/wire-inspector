package org.odyssey.tools.wire.tcp;

import org.odyssey.tools.wire.core.Client;
import org.odyssey.tools.wire.core.Message;
import org.odyssey.tools.wire.core.StringMessage;
import org.odyssey.tools.wire.core.WireInspectorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient implements Client {
    private final String host;
    private final int port;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void connect() throws WireInspectorException {
        try {
            this.socket = new Socket(host, port);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Client connected successfully");
        } catch (IOException e) {
            throw new WireInspectorException("Error occurred while connecting to remote server", e);
        }
    }

    @Override
    public void send(Message message) {
        if (message instanceof StringMessage) {
            this.out.println(((StringMessage) message).getMessage());
        }

    }

    @Override
    public void receive(Message message) {

    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}

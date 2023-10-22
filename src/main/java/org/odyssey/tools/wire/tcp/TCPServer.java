package org.odyssey.tools.wire.tcp;

import org.odyssey.tools.wire.core.Processor;
import org.odyssey.tools.wire.core.RequestProcessor;
import org.odyssey.tools.wire.core.Server;
import org.odyssey.tools.wire.core.SocketMessage;
import org.odyssey.tools.wire.core.WireInspectorException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Server {

    private final int port;
    private ServerSocket serverSocket;

    public TCPServer(int port) {
        this.port = port;
    }

    @Override
    public void start() throws WireInspectorException {
        try {
            this.serverSocket = new ServerSocket(this.port);
            System.out.println("Socket server started at port: " + this.port);
        } catch (IOException e) {
            throw new WireInspectorException("Error occurred while creating server socket", e);
        }
    }

    @Override
    public void listen(RequestProcessor processor) {
        System.out.println("Ready to accept requests");
        while (true) {
            try {
                Socket socket = this.serverSocket.accept();
                processor.processRequest(new SocketMessage(socket));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    public int getPort() {
        return port;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}

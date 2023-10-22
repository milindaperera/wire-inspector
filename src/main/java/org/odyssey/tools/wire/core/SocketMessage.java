package org.odyssey.tools.wire.core;

import java.net.Socket;

public class SocketMessage implements Message {
    private final Socket socket;

    public SocketMessage(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
}

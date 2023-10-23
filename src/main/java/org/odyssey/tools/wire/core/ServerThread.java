package org.odyssey.tools.wire.core;

public class ServerThread implements Runnable {

    private final RequestProcessor requestProcessor;
    private final Message message;

    public ServerThread(RequestProcessor requestProcessor, Message message) {
        this.requestProcessor = requestProcessor;
        this.message = message;
    }

    @Override
    public void run() {
        this.requestProcessor.processRequest(message);
    }
}

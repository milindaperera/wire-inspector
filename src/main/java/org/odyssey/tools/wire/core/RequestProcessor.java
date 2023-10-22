package org.odyssey.tools.wire.core;

public abstract class RequestProcessor implements Processor {
    public abstract void processRequest(Message message);
}

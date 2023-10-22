package org.odyssey.tools.wire.core;

public interface Client {
    void connect() throws WireInspectorException;
    void send(Message message);
    void receive(Message message);
}

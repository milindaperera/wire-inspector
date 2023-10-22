package org.odyssey.tools.wire.core;

import java.io.IOException;

public interface Server {
    void start() throws WireInspectorException;
    void listen(RequestProcessor processor);
    void pause();
    void stop();
}

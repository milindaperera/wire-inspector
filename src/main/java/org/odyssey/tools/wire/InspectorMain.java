package org.odyssey.tools.wire;

import org.odyssey.tools.wire.core.Starter;

public class InspectorMain {
    public static void main(String[] args) {
        Mode mode = Mode.valueOf(args[0]);
        System.out.println("Mode: " + mode);
        Starter starter = switch (mode) {
            case SERVER -> new ServerStarter();
            case CLIENT -> new ClientStarter();
            case PROXY -> new ProxyStarter();
        };
        starter.start(args);
    }
}

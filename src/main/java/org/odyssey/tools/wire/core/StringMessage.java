package org.odyssey.tools.wire.core;

public class StringMessage implements Message {
    private final String message;

    public StringMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

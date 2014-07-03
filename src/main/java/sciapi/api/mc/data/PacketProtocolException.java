package sciapi.api.mc.data;

public class PacketProtocolException extends Exception {
    public PacketProtocolException() {
    }

    public PacketProtocolException(String message, Throwable cause) {
            super(message, cause);
    }

    public PacketProtocolException(String message) {
            super(message);
    }

    public PacketProtocolException(Throwable cause) {
            super(cause);
    }
}

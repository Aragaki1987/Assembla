package lab6.client;

public interface SocketClientInterface {
    boolean openConnection();

    void handleSession();

    void closeSession();
}


package exercise;
import java.util.List;
import java.util.ArrayList;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection  {

    private String ip;

    private int port;

    private List<String> buffer = new ArrayList<>();

    private Connection connect;

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.connect = new Disconnected(this);
    }

    public String getCurrentState() {

        return this.connect.getCurrentState();

    }

    public void setConnect(Connection connect) {

        this.connect = connect;

    }

    public void connect() {

        this.connect.connect();

    }

    public void disconnect() {

        this.connect.disconnect();

    }

    public void write(String data) {

        this.connect.write(data);

    }

    public void addData(String data) {

        this.buffer.add(data);

    }

}
// END

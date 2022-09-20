package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {

    private TcpConnection tcp;

    public Disconnected(TcpConnection tcp) {

        this.tcp = tcp;

    }


    @Override
    public String getCurrentState() {

        return "disconnected";

    }

    @Override
    public void connect() {

        this.tcp.setConnect(new Connected(tcp));

    }

    @Override
    public void disconnect() {

        System.out.println("Error! Connection already disconnected");

    }

    @Override
    public void write(String data) {

        System.out.println("Error! Writing is not possible! Connection disconnected!");

    }
}
// END

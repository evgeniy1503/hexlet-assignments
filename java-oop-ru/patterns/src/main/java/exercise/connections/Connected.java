package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {

    private TcpConnection tcp;

    public Connected(TcpConnection tcp) {

        this.tcp = tcp;

    }

    @Override
    public String getCurrentState() {

        return "connected";

    }

    @Override
    public void connect() {

        System.out.println("Error! Connection already connected");

    }

    @Override
    public void disconnect() {

        this.tcp.setConnect(new Disconnected(tcp));

    }

    @Override
    public void write(String data) {

        this.tcp.addData(data);

    }
}
// END

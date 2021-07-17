import java.util.*;

public class Message {
    private String from;
    private String to;
    public String data;
    public long timeStamp;

    public Message(String from, String to, String data){
        this.from = from;
        this.to = to;
        this.data = data;
        this.timeStamp = new Date().getTime();
    }
    @Override
    public String toString() {
        return Long.toString(this.timeStamp)+":"+ this.data;
    }
}
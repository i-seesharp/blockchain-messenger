import java.util.*;

public class Message implements Comparable<Message> {
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
    @Override
    public int compareTo(Message other) {
        return (int)(this.timeStamp - other.timeStamp);
    }
}
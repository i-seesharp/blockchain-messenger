import java.util.*;
import java.lang.*;
import java.security.*;

public class Block {
    public String previousBlockHash;
    public long timeStamp;
    private long padding;
    public String currentBlockHash;
    public int difficulty;
    private String zeros;
    public TreeMap<String, TreeMap<String, ArrayList<Message>>> payload;

    public Block(String previousHash) {
        this.previousBlockHash = previousHash;
        this.padding = 0;
        this.timeStamp = new Date().getTime();
        this.payload = new TreeMap<>();
        this.currentBlockHash = calculateHash();
        this.difficulty = 1;
        this.zeros = null;
    }

    public String calculateHash() {
        String descriptor = Long.toString(padding) + previousBlockHash + payload.toString();
        this.currentBlockHash = Hashing.hash(descriptor);
        return this.currentBlockHash; 
    }

    public boolean isBlockValid() {
        String descriptor = Long.toString(padding) + previousBlockHash + payload.toString();
        String hashed = Hashing.hash(descriptor);
        if(!hashed.equals(this.currentBlockHash)) return false;
        String substr = this.currentBlockHash.substring(0, this.difficulty);
        if(!substr.equals(this.getZeros())) return false;
        return true;
    }

    public void mineBlock() {
        String hash = this.currentBlockHash.substring(0, this.difficulty);
        String zeros = this.getZeros();
        while(!hash.equals(new String(zeros))){
            if(this.padding <= 0) this.padding = -this.padding + 1;
            else this.padding = -this.padding;
            this.currentBlockHash = calculateHash();
            hash = this.currentBlockHash.substring(0, this.difficulty);
        }
        System.out.println(this.currentBlockHash);
    }

    public String getZeros() {
        if(this.zeros != null) return this.zeros;
        StringBuffer temp = new StringBuffer();
        for(int i=0;i < this.difficulty; i++){
            temp.append("0");
        }
        return new String(temp);
    }

    public void addMessage(String from, String to, String message) {
        Message data = new Message(from, to, message);
        if(payload.get(to) == null) payload.put(to, new TreeMap<>());
        if(payload.get(to).get(from) == null) payload.get(to).put(from, new ArrayList<>());
        payload.get(to).get(from).add(data);
    }
}
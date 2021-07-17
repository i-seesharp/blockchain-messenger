import java.util.*;
import java.lang.*;
import java.security.*;

public class Block {
    public String previousBlockHash;
    public long timeStamp;
    private int padding;
    public String currentBlockHash;
    public HashMap<String, HashMap<String, ArrayList<String>>> payload;

    public Block(String previousHash) {
        this.previousBlockHash = previousHash;
        this.padding = 0;
        this.timeStamp = new Date().getTime();
        this.payload = new HashMap<>();
        this.currentBlockHash = "";
    }

    public String calculateHash() {
        String descriptor = Integer.toString(padding) + previousBlockHash + Long.toString(timeStamp) + payload.toString();
        this.currentBlockHash = Hashing.hash(descriptor);
        return this.currentBlockHash; 
    }

    public boolean isBlockValid() {
        String descriptor = Integer.toString(padding) + previousBlockHash + Long.toString(timeStamp) + payload.toString();
        String hashed = Hashing.hash(descriptor);
        if(hashed.equals(this.currentBlockHash)) return true;
        return false;
    }
}
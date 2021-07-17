import java.util.*;
import java.lang.*;
import java.security.*;

public class Block {
    public String previousBlockHash;
    public long timeStamp;
    private long padding;
    public String currentBlockHash;
    public HashMap<String, HashMap<String, ArrayList<String>>> payload;

    public Block(String previousHash) {
        this.previousBlockHash = previousHash;
        this.padding = 0;
        this.timeStamp = new Date().getTime();
        this.payload = new HashMap<>();
        this.currentBlockHash = calculateHash();
    }

    public String calculateHash() {
        String descriptor = Long.toString(padding) + previousBlockHash + Long.toString(timeStamp) + payload.toString();
        this.currentBlockHash = Hashing.hash(descriptor);
        return this.currentBlockHash; 
    }

    public boolean isBlockValid() {
        String descriptor = Long.toString(padding) + previousBlockHash + Long.toString(timeStamp) + payload.toString();
        String hashed = Hashing.hash(descriptor);
        if(hashed.equals(this.currentBlockHash)) return true;
        return false;
    }

    public void mineBlock(int difficulty) {
        String hash = this.currentBlockHash.substring(0, difficulty);
        StringBuffer zeros = new StringBuffer();
        for(int i=0;i<difficulty;i++) zeros.append("0");
        while(!hash.equals(new String(zeros))){
            if(this.padding <= 0) this.padding = -this.padding + 1;
            else this.padding = -this.padding;
            this.currentBlockHash = calculateHash();
            hash = this.currentBlockHash.substring(0, difficulty);
        }
        System.out.println(this.currentBlockHash);
    }
}
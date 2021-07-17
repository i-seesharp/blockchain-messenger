import java.io.*;
import java.lang.*;
import java.util.*;
import javafx.util.*;

public class App {
    public static ArrayList<Block> blockchain;

    public static void main(String[] args){
        System.out.println("Welcome to The Decentralized Messaging Chain!");
        if(args.length < 1) return;
        blockchain = new ArrayList<>();
        int numBlocks = 0;
        try {
            numBlocks = Integer.parseInt(args[0]);
        } catch(Exception e){
            System.out.println("Argument needs to be an integer");
            return;
        }
        String previousHash = "";
        for(int i=0;i<numBlocks;i++) addBlock();
        System.out.println(isBlockChainValid());
        System.out.println(isBlockChainValid());
    }

    public static boolean isBlockChainValid() {
        int numBlocks = blockchain.size();
        boolean isValid = true;
        String previousHash = "0";
        for(int i=0;i<numBlocks;i++){
            Block currBlock = blockchain.get(i);
            isValid = isValid && (currBlock.isBlockValid());
            isValid = isValid && (previousHash.equals(currBlock.previousBlockHash));
            previousHash = currBlock.currentBlockHash;
            if(!isValid) return false;
        }
        return true;
    }

    public static void addBlock() {
        String previousHash = "";
        int numBlocks = blockchain.size();
        if(numBlocks == 0) previousHash = "0";
        else previousHash = blockchain.get(numBlocks - 1).currentBlockHash;
        Block currBlock = new Block(previousHash);
        currBlock.calculateHash();
        blockchain.add(currBlock);
    }
}
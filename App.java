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
        for(int i=0;i<numBlocks;i++){
            if(blockchain.size() == 0) previousHash = "0";
            else previousHash = blockchain.get(blockchain.size() - 1).currentBlockHash;
            Block currBlock = new Block(previousHash);
            System.out.println("Block " + i + ": " + currBlock.calculateHash());
            blockchain.add(currBlock);
        }
        
    }

    public static boolean isBlockChainValid() {
        int numBlocks = blockchain.size();
        boolean isValid = true;
        for(int i = numBlocks-1;i>=0;i--){
            isValid = isValid && (blockchain.get(i).isBlockValid());
            if(!isValid) return false;
        }
        return true;
    }
}
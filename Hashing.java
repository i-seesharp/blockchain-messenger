import java.security.*;

public class Hashing {
    public static String hash(String text){
        try {
            MessageDigest chainDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = chainDigest.digest(text.getBytes());
            StringBuffer hashed = new StringBuffer();
            for(int i=0;i<bytes.length;i+=2){
                String value = Integer.toString(Math.abs(bytes[i]));
                hashed.append(value);
            }
            for(int i=1;i<bytes.length;i+=2){
                String value = Integer.toString(Math.abs(bytes[i]));
                hashed.append(value);
            }
            return new String(hashed);
        } catch(Exception e){
            System.out.println("Block cannot be hashed!");
            return "N/A";
        }
        
    }
}
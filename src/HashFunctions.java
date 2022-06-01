import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.xml.bind.DatatypeConverter;

public class HashFunctions {
    public static String getHash(byte[] inputBytes, String algorithm){
        String hashValue = "";
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(inputBytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        }
        catch(Exception e){

        }
        return hashValue;
    }

}

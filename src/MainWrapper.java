import java.io.File;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.xml.bind.DatatypeConverter;

public class MainWrapper {
    public static void main(String[] args){
        ArrayList<String> names = new ArrayList<>();
        ListFiles.dirTree(new File("C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu"), names);
        // Iterate through and print the names arraylist from ListFiles
        for(String name : names){
            System.out.println(name);
            // For each iteration of the name, hash the file and append it to an arraylist

            try{
                File image = new File(name);
                byte[] imageBytes = Files.readAllBytes(image.toPath());
                System.out.println(HashFunctions.getHash(imageBytes, "MD5"));
            }
            catch(Exception e){
            }
        }
    }
}
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.io.InputStream;
import java.nio.file.Paths;

public class MainWrapper {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        ArrayList<String> names = new ArrayList<>();
        ListFiles.dirTree(new File("C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu"), names);
        // Iterate through and print the names arraylist from ListFiles
        for(String name : names){
            System.out.println(name);
            // For each iteration of the name, hash the file and append it to an arraylist
        }

        File test = new File("C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu\\test1.txt");
        //SHA-1 checksum
        String shaChecksum = HashFunctions.getFileChecksum("SHA-256", test);
    }
}
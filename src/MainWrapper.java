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
import java.util.HashMap;
import java.io.FileWriter;

public class MainWrapper {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        // Hashmap for data
        HashMap<String, String> data = new HashMap<String, String>();
        // Call ListFiles with the parameters of the directory and hashmap
        ListFiles.dirTree(new File("C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu"), data);


    }
}
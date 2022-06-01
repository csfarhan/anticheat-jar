import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class MainWrapper {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        // Hashmap for data
        HashMap<String, String> data = new HashMap<String, String>();
        // Call ListFiles with the parameters of the directory and hashmap
        ListFiles.dirTree(new File("C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu"), data);


    }
}
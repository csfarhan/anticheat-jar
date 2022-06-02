import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class ListFiles {
    public static void dirTree(File dir, SortedMap<String, String> data) throws NoSuchAlgorithmException, IOException {
        File[] subdirs = dir.listFiles();
        for (File subdir : subdirs) {
            if (subdir.isDirectory()) {
                dirTree(subdir, data);
            } else {
                data.put(subdir.getName(), HashFunctions.getFileChecksum("SHA-256", subdir));
                try {
                    serializeData(subdir.getName(),  HashFunctions.getFileChecksum("SHA-256", subdir));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  
    public static void serializeData(String name, String hash) throws IOException{
        FileWriter writer = new FileWriter("C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\writing.txt", true);
        writer.write(name + "," + hash);
        writer.write("\n");
        writer.close();
    }
}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;

public class ListFiles {
    public static void dirTree(File dir, SortedMap<String, String> data) throws NoSuchAlgorithmException, IOException {
        File[] subdirs = dir.listFiles();
        for (File subdir : subdirs) {
            if (subdir.isDirectory()) {
                dirTree(subdir, data);
            } else {
                data.put(subdir.getAbsolutePath(), HashFunctions.getFileChecksum("SHA-256", subdir));
                try {
                    serializeData(subdir.getAbsolutePath(),  HashFunctions.getFileChecksum("SHA-256", subdir));
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

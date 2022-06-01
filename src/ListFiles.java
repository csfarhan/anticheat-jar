import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class ListFiles {
    public static void dirTree(File dir, HashMap<String, String> data) throws NoSuchAlgorithmException, IOException {
        File[] subdirs = dir.listFiles();
        FileWriter writer = null;
        for (File subdir : subdirs) {
            if (subdir.isDirectory()) {
                dirTree(subdir, data);
            } else {
                data.put(subdir.getName(), HashFunctions.getFileChecksum("SHA-256", subdir));
                try {
                    writer = new FileWriter("C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\writing.txt", true);
                    writer.write(subdir.getName() + "," + HashFunctions.getFileChecksum("SHA-256", subdir));
                    writer.write("\n");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

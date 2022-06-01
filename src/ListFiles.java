import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.DigestInputStream;

public class ListFiles {
    public static void dirTree(File dir, ArrayList<String> names) throws NoSuchAlgorithmException, IOException {
        File[] subdirs=dir.listFiles();
        for(File subdir: subdirs) {
            if (subdir.isDirectory()) {
                dirTree(subdir, names);
            }
            else {
                names.add(subdir.getAbsolutePath());
                System.out.println(HashFunctions.getFileChecksum("SHA-256", subdir));
            }
        }

    }
}

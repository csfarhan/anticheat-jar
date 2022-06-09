import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AntiCheat {
    private String rootPath;
    private File referenceDataFile;
    public AntiCheat(String rootPath, String referenceData){
        this.rootPath = rootPath;
        this.referenceDataFile = new File(referenceData);
    }

    public void start() throws FileNotFoundException {
        ReferenceGenerator.generateReferenceFiles(rootPath, referenceDataFile.getAbsolutePath());
        System.out.println("Reference Files Obtained.");
    }
    public ArrayList<String> start2() throws FileNotFoundException{
        Boolean trigger = true;
        HashMap<String, String> referenceData = ReferenceGenerator.deserializeReferenceFile(referenceDataFile.getAbsolutePath());
        // Then hash whatever file is in the given location
        HashMap<String, String> dataNew  = new HashMap<>();
        // Call ListFiles with the parameters of the directory and hashmap
        ReferenceGenerator.generateFileHashMap(new File(rootPath), dataNew, "", false);
        // Then compare the two hashmaps
        if(referenceData.equals(dataNew)){
            System.out.println("Verified.");
            ArrayList<String> foundPaths = Comparer.generateOutput(referenceData, dataNew);
            return foundPaths;
        }
        else{
            ArrayList<String> foundPaths = Comparer.generateOutput(referenceData, dataNew);
            return foundPaths;
        }
    }
}

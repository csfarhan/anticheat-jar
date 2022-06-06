import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class AntiCheat {
    private String rootPath;
    private File referenceDataFile;
    public AntiCheat(String rootPath, String referenceData){
        this.rootPath = rootPath;
        this.referenceDataFile = new File(rootPath);
    }

    public void start(){
        // Obtain input from user
        Scanner myObj = new Scanner(System.in);
        System.out.println("Press '1' to determine reference files: ");
        String answer = myObj.nextLine();
        // If 1, run hashing and store in txt
        if (answer.equals("1")){
            ReferenceGenerator.generateReferenceFiles(rootPath, referenceDataFile.getAbsolutePath());
        }

        // Obtain input from user
        Scanner myObj2 = new Scanner(System.in);
        System.out.println("Press '2' to verify file integrity: ");

        String answer2 = myObj.nextLine();
        // If 2, convert reference from txt to hashmap, and hash current files and compare the two hashmaps
        if (answer2.equals("2")){
            HashMap<String, String> referenceData = ReferenceGenerator.deserializeReferenceFile(referenceDataFile.getAbsolutePath());
            // Then hash whatever file is in the given location
            HashMap<String, String> dataNew  = new HashMap<>();
            // Call ListFiles with the parameters of the directory and hashmap
            ReferenceGenerator.generateFileHashMap(new File(rootPath), dataNew, "", false);
            // Then compare the two hashmaps
            if(referenceData.equals(dataNew)){
                System.out.println("Verified");
            }
            else{
                Comparer.generateOutput(referenceData, dataNew);
            }
        }
    }
}

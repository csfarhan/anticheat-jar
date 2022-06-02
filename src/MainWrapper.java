import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;


public class MainWrapper {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        // Obtain input from user
        Scanner myObj = new Scanner(System.in);
        System.out.println("Press '1' to determine reference files: ");

        String answer = myObj.nextLine();

        // If 1, run hashing and store in txt
        if (answer.equals("1")){
            // Runs hashing for the reference folder
            // Hashmap for data
            SortedMap<String, String> data  = new TreeMap<String, String>();

            // Call ListFiles with the parameters of the directory and hashmap
            ListFiles.dirTree(new File("C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu"), data);
        }

        // Obtain input from user
        Scanner myObj2 = new Scanner(System.in);
        System.out.println("Press '2' to verify file integrity: ");

        String answer2 = myObj.nextLine();
        // If 2, convert reference from txt to hashmap, and hash current files and compare the two hashmaps
        if (answer2.equals("2")){
            // Read txt and write to a string
            String info = null;
            SortedMap<String, String> data  = new TreeMap<String, String>();
            try{
                File read = new File("writing.txt");
                Scanner myReader = new Scanner(read);
                while(myReader.hasNextLine()){
                    info = myReader.nextLine();
                    String[] out = info.split(",");
                    // Every iteration update the hashmap with the given key and value
                    data.put(out[0],out[1]);
                }
                myReader.close();
            } catch (FileNotFoundException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            // Then hash whatever file is in the given location
            SortedMap<String, String> dataNew  = new TreeMap<String, String>();
            // Call ListFiles with the parameters of the directory and hashmap
            ListFiles.dirTree(new File("C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu"), dataNew);
            // Then compare the two hashmaps
            System.out.println(data.equals(dataNew));
        }

    }
}
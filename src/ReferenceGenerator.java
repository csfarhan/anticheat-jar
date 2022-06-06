import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class ReferenceGenerator {
    public static void generateReferenceFiles(String rootPath, String referenceDataFile){
        //Erase whatever already exists in the reference data file if it exists
        clearReferenceFile(referenceDataFile);

        //generate the hash for the files in the rootPath
        HashMap<String, String> fileData = new HashMap<>();

        //Generate the hash and serialize
        File rootDir = new File(rootPath);
        generateFileHashMap(rootDir, fileData, referenceDataFile, true);
    }

    public static void generateFileHashMap(File rootDir, HashMap<String, String> fileData, String saveFile, boolean serialize){
        File[] subdirs = rootDir.listFiles();
        for (File subdir : subdirs) {
            if (subdir.isDirectory()) {
                generateFileHashMap(subdir, fileData, saveFile, serialize);
            } else {
                try {
                    fileData.put(subdir.getAbsolutePath(), getFileChecksum("SHA-256", subdir));
                    if(serialize)
                        serializeData(subdir.getAbsolutePath(),  getFileChecksum("SHA-256", subdir), saveFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getFileChecksum(String algorithm, File file) throws IOException{
        MessageDigest digest = null;
        try{
            digest = MessageDigest.getInstance(algorithm);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }

    public static void serializeData(String name, String hash, String fileName) throws IOException{
        FileWriter writer = new FileWriter(fileName, true);
        writer.write(name + "," + hash);
        writer.write("\n");
        writer.close();
    }

    public static void clearReferenceFile(String referenceDataFile){

        File referenceData = new File(referenceDataFile);
        if(referenceData.exists()){
            try{
                PrintWriter writer = new PrintWriter(referenceData);
                writer.print("");
                writer.close();
            }catch(FileNotFoundException e){
                System.out.println("File not found");
            }
        }
    }

    public static HashMap<String, String> deserializeReferenceFile(String referenceFile){
        // Read txt and write to a string
        String info = null;
        HashMap<String, String> data  = new HashMap<String, String>();
        try{
            File read = new File(referenceFile);
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
        return data;
    }

}

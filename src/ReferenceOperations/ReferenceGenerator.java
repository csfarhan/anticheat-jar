package ReferenceOperations;

import DatabaseAPI.DatabaseOperation;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class ReferenceGenerator {
    static DatabaseOperation db = DatabaseOperation.getInstance("jdbc:sqlserver://poromtest.mssql.somee.com;database=poromtest;user=PoromK_SQLLogin_1;password=prnclvbss7;encrypt=true;trustServerCertificate=true;loginTimeout=30;");
    public static void generateReferenceFiles(String rootPath, String userId, String gameName, boolean serialize){
        //generate the hash for the files in the rootPath
        HashMap<String, String> fileData = new HashMap<>();

        //Generate the hash and serialize
        File rootDir = new File(rootPath);
        generateFileHashMap(rootDir, fileData, userId, gameName, true);
    }

    public static void generateFileHashMap(File rootDir, HashMap<String, String> fileData, String userId, String gameName, boolean serialize){
        File[] subdirs = rootDir.listFiles();
        for (File subdir : subdirs) {
            if (subdir.isDirectory()) {
                generateFileHashMap(subdir, fileData, userId, gameName, serialize);
            } else {
                try {
                    String hash = getFileChecksum("SHA-256", subdir);
                    if(serialize)
                        db.insertFileData(userId, gameName, subdir.getAbsolutePath(), hash);
                    fileData.put(subdir.getAbsolutePath(), hash);
                } catch (IOException | SQLException e) {
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

}

package DatabaseAPI;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

import ReferenceOperations.ReferenceGenerator;

public class User {
    public static Boolean loggedIn = false;
    private static String userId = "000000";
    public static void registerUser(String email, String password, DatabaseOperation db) throws Exception {
        String errormsg = "";
        if (!checkValidEmail(email)) {
            errormsg += "Invalid Email. ";
        }
        if (!checkValidPass(password)) {
            errormsg += "Password must be 8 characters long, contains 1 number, contains 1 symbol (!@#$%&?), contains 1 uppercase, and 1 lowercase letter.";
        }
        if (errormsg.length() != 0) {
            throw new Exception(errormsg);
        }

        String hash = hashString(email+password);
        db.registerUserDatabase(email, hash);
    }

    /**
     *
     * @param email
     * @param password
     * @param db
     * @return return true or false, depending on whether the login attempt was successful
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    public static Boolean loginUser(String email, String password, DatabaseOperation db) throws NoSuchAlgorithmException, SQLException {
        String accountHash = hashString(email + password);

        String foundId = db.loginUser(email, accountHash);

        if(!foundId.equals("000000")){
            loggedIn = true;
            userId = foundId;
        }
        return loggedIn;
    }

    private static boolean checkValidPass(String password) {
        //Atleast 8 characters
        if (password.length() < 8) {
            return false;
        }
        //Atleast 1 number
        if (!password.matches(".*[0-9]+.*")) {
            return false;
        }
        //Atleast 1 symbol
        if (!password.matches(".*[!@#$%&?]+.*")) {
            return false;
        }
        //Atleast 1 uppercase
        if (!password.matches(".*[A-Z]+.*")) {
            return false;
        }
        //Atleast 1 lowercase
        if (!password.matches(".*[a-z]+.*")) {
            return false;
        }
        return true;
    }

    private static boolean checkValidEmail(String email) {
        if (!email.matches("[\\d\\.\\w]+@[\\w]+[\\.][\\w]+")) {
            return false;
        }
        return true;
    }

    private static String hashString(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();

        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void generateFileReference(String gameName, String rootDirectory){
        ReferenceGenerator.generateReferenceFiles(rootDirectory, userId, gameName, true);
    }

    private static HashMap<String, String> deserializeData(String gameName) throws SQLException {
        return DatabaseOperation.deserializeData(userId, gameName);
    }

    public static boolean verifyFiles(String gameName, String rootDir) throws SQLException {
        HashMap<String, String> currentFiles = new HashMap<>();
        HashMap<String, String> referenceFiles = deserializeData(gameName);
        ReferenceGenerator.generateFileHashMap(new File(rootDir), currentFiles, userId, gameName,false);

        if(currentFiles.size() != referenceFiles.size()){
            return false;
        }

        for(String key: currentFiles.keySet()){
            if(!referenceFiles.containsKey(key) || !referenceFiles.get(key).equals(currentFiles.get(key))){
                return false;
            }
        }

        return true;
    }

    public static void logOut(){
        userId = "000000";
        loggedIn = false;
    }

}

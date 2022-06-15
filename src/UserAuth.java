import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAuth {

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
        String emailsplit[] = email.split("@");
        String emailString = emailsplit[0] + "," + emailsplit[1];
        //db.executeStatement("INSERT INTO dbo.Auth (local,domain,hash) VALUES ("+emailString+","+hash+")");
        db.executeStatement("INSERT INTO dbo.Auth (local,domain,hash) VALUES (porom,kamal,123SexyGuy)");
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
        if (!email.matches("[\\d\\w]+@[\\w]+[\\.][\\w]+")) {
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
}

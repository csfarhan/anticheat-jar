import DatabaseAPI.DatabaseOperation;
import DatabaseAPI.User;

import java.util.Scanner;

public class MainWrapper {
    public static void main(String[] args) throws Exception {
        /*
                AntiCheat antiCheat = new AntiCheat(
                "C:\\Users\\rana_\\IdeaProjects\\anticheat-jar\\anticheat-jar\\testData",
                "referenceTest.txt");
                antiCheat.start();
         */
         AntiCheatFrame antiCheatFrame = new AntiCheatFrame( "C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu","referenceTest.txt" );
         antiCheatFrame.initWindow();
        //testDatabase.executeStatement("CREATE TABLE Auth (id INTEGER IDENTITY(1,1) not NULL PRIMARY KEY, email VARCHAR(MAX) not NULL,  hash VARCHAR(MAX) not NULL)");
        Scanner in = new Scanner(System.in);
        System.out.println("DEBUG MODE LOGIN/REGISTER");
        System.out.println("1 to register test data");
        System.out.println("2 to login test data");

        /*if(in.next().equals("1")){
            User.registerUser("Porom.Kamal@gmail.com", "@Porom2002", testDatabase); // Already registered don't call this again
        }else{
            User.loginUser("PoromKamal@gmail.com", "@Porom200222", testDatabase); //incorrect login test

            System.out.println(User.loggedIn);

            User.loginUser("PoromKamal@gmail.com", "@Porom2002", testDatabase);

            System.out.println(User.loggedIn);

            testDatabase.close();
        }
         */
    }

}
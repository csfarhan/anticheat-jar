import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;


public class MainWrapper {
    public static void main(String[] args) {
        /*
                AntiCheat antiCheat = new AntiCheat(
                "C:\\Users\\rana_\\IdeaProjects\\anticheat-jar\\anticheat-jar\\testData",
                "referenceTest.txt");
                antiCheat.start();
         */

        AntiCheatFrame antiCheatFrame = new AntiCheatFrame( "C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu","referenceTest.txt" );
        antiCheatFrame.initWindow();

    }
}
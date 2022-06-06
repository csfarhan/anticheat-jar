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
        AntiCheat antiCheat = new AntiCheat(
                "C:\\Users\\rana_\\Downloads\\2SI3-Data-Structures-and-Algorithms-main\\2SI3-Data-Structures-and-Algorithms-main\\Lab1&2",
                "referenceTest.txt");

        antiCheat.start();
    }
}
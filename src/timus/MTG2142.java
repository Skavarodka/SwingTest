package timus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MTG2142 {

    public static void main(String[] args) throws IOException {

        String inputFileName = "src/timus/input.txt";
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        BufferedReader bufferedReader =
                oj ? new BufferedReader(new InputStreamReader(System.in)) :
                        new BufferedReader(new FileReader(inputFileName));

        String[] vasyaMana = bufferedReader.readLine().split(" ");
        String[] castMana = bufferedReader.readLine().split(" ");

        long vR = Long.parseLong(vasyaMana[0]);
        long vB = Long.parseLong(vasyaMana[1]);
        long vBR = Long.parseLong(vasyaMana[2]);

        long cR = Long.parseLong(castMana[0]);
        long cB = Long.parseLong(castMana[1]);
        long cBR = Long.parseLong(castMana[2]);

        if (((vBR+vR+vB) - (cR+cBR+cB)) < 0) {

            System.out.println("There are no miracles in life");
        }

    }
}

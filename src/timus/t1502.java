package timus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class t1502 {

    public static void main(String[] args) throws IOException {

        String inputFileName = "src/timus/input.txt";
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        BufferedReader bufferedReader =
                oj ? new BufferedReader(new InputStreamReader(System.in)) :
                        new BufferedReader(new FileReader(inputFileName));

        long n = Long.parseLong(bufferedReader.readLine());
        long answer = (n+2)*((n+1)*n)/2;
        System.out.println(answer);
    }
}

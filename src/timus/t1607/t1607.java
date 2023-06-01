package timus.t1607;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class t1607 {

    public static void main(String[] args) throws IOException {

        String inputFileName = "src/timus/input.txt";
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        BufferedReader bufferedReader =
                oj ? new BufferedReader(new InputStreamReader(System.in)) :
                        new BufferedReader(new FileReader(inputFileName));

        String[] money = bufferedReader.readLine().split(" ");
        int petStart = Integer.parseInt(money[0]);
        int petStep = Integer.parseInt(money[1]);
        int taxStart = Integer.parseInt(money[2]);
        int taxStep = Integer.parseInt(money[3]);
        System.out.println(Arrays.toString(money));

        int answer = 0;

        if (petStart > taxStart) {

            System.out.println(petStart);
        } else {

            while (petStart <= taxStart) {

                if (petStart + petStep <= taxStart) {

                    petStart +=petStep;
                    answer = petStart;
                } else {

                    answer = taxStart;
                    break;
                }

                if (taxStart - taxStep >= petStart) {

                    taxStart -= taxStep;
                    answer = taxStart;
                } else {

                    answer = petStart;
                    break;
                }
            }
            System.out.println(answer);
        }
    }
}

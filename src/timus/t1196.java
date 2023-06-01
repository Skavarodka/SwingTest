package timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class t1196 {
    public static void main(String[] args) throws IOException {
        int answer = 0;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        int prepodN = Integer.parseInt(bufferedReader.readLine());

        HashSet<Integer> prepodL = new HashSet<>();
        for (int i = 0; i < prepodN; i++) {

            prepodL.add(Integer.parseInt(bufferedReader.readLine()));
        }

        int sN = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < sN; i++) {

            if (prepodL.contains(Integer.parseInt(bufferedReader.readLine()))) {

                answer++;
            }

        }

        System.out.println(answer);
    }
}

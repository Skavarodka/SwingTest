package timus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class t1563 {

    public static void main(String[] args) throws IOException {

        String inputFileName = "src/timus/input.txt";
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        BufferedReader bufferedReader =
                oj ? new BufferedReader(new InputStreamReader(System.in)) :
                        new BufferedReader(new FileReader(inputFileName));

        int numbers = Integer.parseInt(bufferedReader.readLine());

        HashSet<String> hashSet = new HashSet<>();

        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();

        for (int i = 0; i < numbers; i++) {

            list.add(bufferedReader.readLine());
        }

        list.forEach(o -> {

            if (!hashSet.add(o)) {

                list1.add(o);
            }
        });

        System.out.println(list1.size());
    }
}
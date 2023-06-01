package timus.t1493;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class t1493 {

    public static void main(String[] args) throws IOException {

        String inputFileName = "src/timus/input.txt";
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        BufferedReader bufferedReader =
                oj ? new BufferedReader(new InputStreamReader(System.in)) :
                        new BufferedReader(new FileReader(inputFileName));




        String userIn = bufferedReader.readLine();
        List<Integer> saebalo = new ArrayList<>();
        for (int i = 0; i < userIn.length(); i++){
            int x = Integer.parseInt(userIn.substring(i, (i + 1)));
            saebalo.add(x);
        }



        int bilet = Integer.parseInt(userIn);
        Integer biletM = bilet - 1;
        Integer biletP = bilet + 1;

        int luck = saebalo.get(0) + saebalo.get(1) + saebalo.get(2);
        List<Integer> m = DigitsInNumber(biletM);
        String bm = biletM.toString();
        for (int i = 0; i < bm.length(); i++){
            int x = Integer.parseInt(bm.substring(i, (i + 1)));
            m.add(x);
        }

        List<Integer> p = DigitsInNumber(biletP);
        int mm = m.get(m.size()-1) + m.get(m.size()-2) + m.get(m.size()-3);
        int pp = p.get(p.size()-1) + p.get(p.size()-2) + p.get(p.size()-3);

        if (mm == luck || pp == luck) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static List<Integer> DigitsInNumber(int number) {

        String n = Integer.toString(number);
        char[] charArray = n.toCharArray();
        List<Integer> cia = new ArrayList<>();
        for (int i = 0;i<charArray.length; i++){
            int c = Character.getNumericValue(charArray[i]);
            cia.add(c);
        }return cia;
    }
}

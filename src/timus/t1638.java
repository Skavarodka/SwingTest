package timus;

import java.util.Scanner;

public class t1638 {
//РЕЩИИИИЛ
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();
        int bookS = in.nextInt();
        int bookF = in.nextInt();
        int waySoFar = bookF - bookS;
        int way = 0;
        boolean fl = true;

        //
        while (fl) {


            if ((bookF - bookS) == 1) {
                System.out.println(b + b);
                break;
            }

            if ((bookF - bookS) == 0) {

                System.out.println(a);
                break;
            }

            if ((bookF - bookS) < 0) {

                for (int i = 0; i < Math.abs(waySoFar); i++) {

                    way += a + b + b;
                }
                System.out.println(way + a);
                break;
            }

            if (waySoFar > 1) {

                for (int i = 0; i < Math.abs(waySoFar); i++) {

                    way += a + b + b;
                }
                System.out.println(way - a);
                break;
            }
        }

    }
}

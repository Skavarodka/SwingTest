import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        int[][] array = new int[10][10]; // Создаем новый двумерный массив размером 10x10

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                int num = random.nextInt(400) + 5; // Генерируем случайное число от 10 до 300
                array[i][j] = num;
            }
        }

        // Выводим массив на экран
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
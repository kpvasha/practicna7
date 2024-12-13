import java.util.Random;
import java.util.Scanner;

public class Practice7{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nОберіть операцію:");
            System.out.println("1. Зубкова піраміда");
            System.out.println("2. Модифікація дійсного масиву");
            System.out.println("3. Визначник матриці 5x5");
            System.out.println("4. Мінор матриці");
            System.out.println("5. Транспонована матриця");
            System.out.println("0. Вихід");
            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Zubkovapiramidk();
                    break;
                case 2:
                    dregezavdannya();
                    break;
                case 3:
                    calcDet();
                    break;
                case 4:
                    calcMin();
                    break;
                case 5:
                    transMatrix();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
            }
        }
        scanner.close();
    }
    // 1.
    private static void Zubkovapiramidk() {
        int levels = 5;
        int[][] pyramid = new int[levels][];

        for (int i = 0; i < levels; i++) {
            pyramid[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                pyramid[i][j] = (int) (Math.random() * 100);
            }
        }

        System.out.println("Звичайний порядок:");
        for (int i = 0; i < levels; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(pyramid[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nЗворотний порядок:");
        for (int i = levels - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                System.out.print(pyramid[i][j] + " ");
            }
            System.out.println();
        }
    }
    // 2.
    private static void dregezavdannya() {
        int rows = 4, cols = 4;
        double[][] array = new double[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = random.nextDouble() * 100;
            }
        }

        System.out.println("Початковий масив:");
        printArray(array);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i % 2 != 0 || j % 2 != 0) && array[i][j] >= 0) {
                    array[i][j] = Math.sqrt(array[i][j]);
                }
            }
        }

        System.out.println("\nМодифікований масив:");
        printArray(array);
    }
    private static void printArray(double[][] array) {
        for (double[] row : array) {
            for (double value : row) {
                System.out.printf("%.2f ", value);
            }
            System.out.println();
        }
    }
    // 3.
    private static void calcDet() {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[5][5];

        System.out.println("Введіть елементи матриці 5x5 (25 чисел):");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int determinant = calcDet(matrix);
        System.out.println("Визначник матриці: " + determinant);
    }
    private static int calcDet(int[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int determinant = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            determinant += Math.pow(-1, i) * matrix[0][i] * calcDet(getMinor(matrix, 0, i));
        }
        return determinant;
    }
    private static int[][] getMinor(int[][] matrix, int row, int col) {
        int[][] minor = new int[matrix.length - 1][matrix[0].length - 1];
        int r = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == col) continue;
                minor[r][c] = matrix[i][j];
                c++;
            }
            r++;
        }
        return minor;
    }
    // 4.
    private static void calcMin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть розмір квадратної матриці (n): ");
        int size = scanner.nextInt();
        if (size <= 1) {
            System.out.println("Розмір матриці повинен бути більше 1.");
            return;
        }

        int[][] matrix = new int[size][size];
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Введіть номер рядка для мінору (1-" + size + "): ");
        int row = scanner.nextInt() - 1; // Для индексации от 0
        System.out.print("Введіть номер стовпця для мінору (1-" + size + "): ");
        int col = scanner.nextInt() - 1;

        if (row < 0 || row >= size || col < 0 || col >= size) {
            System.out.println("Невірні індекси рядка або стовпця.");
            return;
        }

        int[][] minor = getMinor(matrix, row, col);

        System.out.println("Мінор матриці:");
        for (int[] r : minor) {
            for (int val : r) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // 5.
    private static void transMatrix() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть розмір квадратної матриці (n): ");
        int size = scanner.nextInt();
        if (size <= 0) {
            System.out.println("Розмір матриці повинен бути більше 0.");
            return;
        }

        int[][] matrix = new int[size][size];
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        int[][] transpose = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }

        System.out.println("Транспонована матриця:");
        for (int[] row : transpose) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
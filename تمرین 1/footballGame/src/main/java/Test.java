import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter size your matrix: ");
        int sizeOfMatrix = scanner.nextInt();

        int[][] before = new int[sizeOfMatrix][sizeOfMatrix];

        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 0; j < sizeOfMatrix; j++) {
                before[i][j] = scanner.nextInt();
            }
        }
        System.out.println("before change: ");
        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 0; j < sizeOfMatrix; j++) {
                System.out.print(before[i][j] + " ");
            }
            System.out.println();
        }
        int[][] after = new int[sizeOfMatrix][sizeOfMatrix];

        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 0; j < sizeOfMatrix; j++) {
                after[j][i] = before[sizeOfMatrix-i-1][j];
            }
        }
        System.out.println("after 90 deg rotate: ");
        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 0; j < sizeOfMatrix; j++) {
                System.out.print(after[i][j] + " ");
            }
            System.out.println();
        }
    }
}

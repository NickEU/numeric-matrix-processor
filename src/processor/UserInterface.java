package processor;

import java.util.Scanner;

class UserInterface {
    private final Scanner sc = new Scanner(System.in);

    private Matrix readMatrixFromUser() {
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, sc.nextInt());
            }
        }
        return result;
    }

    public void runStage2() {
        Matrix a = readMatrixFromUser();
        int constant = sc.nextInt();
        Matrix result = Calculator.multiplyMatrixByConst(a, constant);
        System.out.println("\n" + result);
    }
}

package processor;

import java.util.Optional;
import java.util.Scanner;


class UserInterface {
    private final Scanner sc = new Scanner(System.in);

    void runStage1() {
        Matrix a = readMatrixFromUser();
        Matrix b = readMatrixFromUser();
        Optional<Matrix> result = Calculator.addMatrices(a, b);
        String output = result.isEmpty() ? "ERROR" : result.get().toString();
        System.out.println("\n" + output);
    }

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

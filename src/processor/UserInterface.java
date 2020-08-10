package processor;

import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

class UserInterface {
    private final Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    void runMainMenuLoop() {
        while (true) {
            System.out.print("1. Add matrices\n" +
                "2. Multiply matrix to a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "0. Exit\n" +
                "Your choice: ");
            String userChoice = sc.next();
            switch (userChoice) {
                case "1":
                    menuMatrixAddition();
                    break;
                case "2":
                    menuMultiplyByConstant();
                    break;
                case "3":
                    menuMatrixMultiplication();
                    break;
                case "4":
                    menuTransposeMatrix();
                    break;
                case "5":
                    menuCalcDeterminant();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Error! Unknown command!");
            }
        }
    }

    private void menuCalcDeterminant() {
        Matrix input = readMatrixFromUser("the");
        Optional<Double> result = Calculator.getDeterminant(input);
        String output = result.isEmpty()
            ? "ERROR! Can only calculate a determinant of square matrices"
            : Util.doubleToString(result.get());
        System.out.println("The result is:\n" + output);
    }

    private void menuTransposeMatrix() {
        Transposition type = null;
        do {
            System.out.print("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n" +
                "Your choice: ");
            String userChoice = sc.next();
            switch (userChoice) {
                case "1":
                    type = Transposition.MAIN_DIAGONAL;
                    break;
                case "2":
                    type = Transposition.SIDE_DIAGONAL;
                    break;
                case "3":
                    type = Transposition.VERTICAL_LINE;
                    break;
                case "4":
                    type = Transposition.HORIZONTAL_LINE;
                    break;
            }
        } while (type == null);
        Matrix toBeTransposed = readMatrixFromUser("the");
        Matrix result = Calculator.transposeMatrix(toBeTransposed, type);
        System.out.println("The result is:");
        System.out.println(result);
    }

    private void menuMatrixAddition() {
        Matrix a = readMatrixFromUser("first");
        Matrix b = readMatrixFromUser("second");
        Optional<Matrix> result = Calculator.addMatrices(a, b);
        String output = result.isEmpty() ? "ERROR" : result.get().toString();
        System.out.println("The result of addition is:\n" + output);
    }

    private void menuMultiplyByConstant() {
        Matrix a = readMatrixFromUser("the");
        System.out.println("Multiplied by: ");
        double constant = Util.parseNumber(sc.next());
        Matrix result = Calculator.multiplyMatrixByConst(a, constant);
        System.out.println("The result of multiplication by a constant is:");
        System.out.println(result);
    }

    private void menuMatrixMultiplication() {
        Matrix a = readMatrixFromUser("first");
        Matrix b = readMatrixFromUser("second");
        Optional<Matrix> result = Calculator.multiplyMatrices(a, b);
        String output = result.isEmpty() ? "ERROR" : result.get().toString();
        System.out.println("The multiplication result is:\n" + output);
    }

    private Matrix readMatrixFromUser(String name) {
        System.out.printf("Enter size of %s matrix: ", name);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        System.out.printf("Enter %s matrix:\n", name);
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, Util.parseNumber(sc.next()));
            }
        }
        System.out.println();
        return result;
    }
}

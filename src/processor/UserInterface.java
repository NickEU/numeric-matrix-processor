package processor;

import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

class UserInterface {
    private final Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    public void runMainMenuLoop() {
        while (true) {
            System.out.print("1. Add matrices\n" +
                "2. Multiply matrix to a constant\n" +
                "3. Multiply matrices\n" +
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
                case "0":
                    return;
                default:
                    System.out.println("Error! Unknown command!");
            }
        }
    }

    private void menuMatrixAddition() {
        Matrix a = readMatrixFromUser("first");
        Matrix b = readMatrixFromUser("second");
        Optional<Matrix> result = Calculator.addMatrices(a, b);
        String output = result.isEmpty() ? "ERROR" : result.get().toString();
        System.out.println("The result of addition is:\n" + output);
    }

    public void menuMultiplyByConstant() {
        Matrix a = readMatrixFromUser("the");
        System.out.println("Multiplied by: ");
        double constant = parseNumber(sc.next());
        Matrix result = Calculator.multiplyMatrixByConst(a, constant);
        System.out.println("The result of multiplication by a constant is:");
        System.out.println(result);
    }

    private double parseNumber(String userInput) {
        return Double.parseDouble(userInput.contains(",")
            ? userInput.replaceAll(",", ".")
            : userInput);
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
                result.setElement(i, j, parseNumber(sc.next()));
            }
        }
        System.out.println();
        return result;
    }
}

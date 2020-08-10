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
        Matrix a = readMatrixFromUser();
        Matrix b = readMatrixFromUser();
        Optional<Matrix> result = Calculator.addMatrices(a, b);
        String output = result.isEmpty() ? "ERROR" : result.get().toString();
        System.out.println("\n" + output);
    }

    public void menuMultiplyByConstant() {
        Matrix a = readMatrixFromUser();
        double constant = parseNumber(sc.next());
        Matrix result = Calculator.multiplyMatrixByConst(a, constant);
        System.out.println("\n" + result);
    }

    private double parseNumber(String userInput) {
        System.out.println(userInput);
        return Double.parseDouble(userInput.contains(",")
            ? userInput.replaceAll(",", ".")
            : userInput);
    }

    private void menuMatrixMultiplication() {
        System.out.println("Multiplication happens here");
    }

    private Matrix readMatrixFromUser() {
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, parseNumber(sc.next()));
            }
        }
        return result;
    }
}

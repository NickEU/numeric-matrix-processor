package processor;

import java.util.Optional;

class Calculator {
    static Optional<Matrix> addMatrices(Matrix a, Matrix b) {
        if (!a.isSameSizeAs(b)) {
            return Optional.empty();
        }
        int rows = a.getRows();
        int cols = a.getColumns();
        var result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, a.getElement(i, j)
                    + b.getElement(i, j));
            }
        }

        return Optional.of(result);
    }

    static Matrix multiplyMatrixByConst(Matrix a, double constant) {
        int rows = a.getRows();
        int cols = a.getColumns();
        var result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, a.getElement(i, j) * constant);
            }
        }
        return result;
    }

    static Optional<Matrix> multiplyMatrices(Matrix a, Matrix b) {
        int colsInA = a.getColumns();
        if (colsInA != b.getRows()) {
            return Optional.empty();
        }
        int rows = a.getRows();
        int cols = b.getColumns();
        Matrix result = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double sumOfProducts = 0;
                for (int k = 0; k < colsInA; k++) {
                    sumOfProducts += a.getElement(i, k) * b.getElement(k, j);
                }
                result.setElement(i, j, sumOfProducts);
            }
        }

        return Optional.of(result);
    }

    static Matrix transposeMatrix(Matrix toBeTransposed, Transposition type) {
        int rows = toBeTransposed.getColumns();
        int cols = toBeTransposed.getRows();
        Matrix result = new Matrix(rows, cols);
        switch (type) {
            case MAIN_DIAGONAL:
                transposeMainDiag(result, toBeTransposed);
                break;
            case SIDE_DIAGONAL:
                transposeSideDiag(result, toBeTransposed);
                break;
            case HORIZONTAL_LINE:
                transposeHorizontalLine(result, toBeTransposed);
                break;
            case VERTICAL_LINE:
                transposeVerticalLine(result, toBeTransposed);
                break;
        }
        return result;
    }

    private static void transposeVerticalLine(Matrix result, Matrix toBeTransposed) {
        for (int row = 0; row < result.getRows(); row++) {
            for (int col = result.getColumns() - 1; col >= 0; col--) {
                result.setElement(row, col,
                    toBeTransposed.getElement(row, toBeTransposed.getColumns() - 1 - col));
            }
        }
    }

    private static void transposeHorizontalLine(Matrix result, Matrix toBeTransposed) {
        for (int row = result.getRows() - 1; row >= 0; row--) {
            for (int col = 0; col < result.getColumns(); col++) {
                result.setElement(row, col,
                    toBeTransposed.getElement(toBeTransposed.getRows() - 1 - row, col));
            }
        }
    }

    private static void transposeSideDiag(Matrix result, Matrix toBeTransposed) {
        for (int col = result.getColumns() - 1; col >= 0; col--) {
            for (int row = result.getRows() - 1; row >= 0; row--) {
                result.setElement(row, col, toBeTransposed.getElement
                    (toBeTransposed.getRows() - 1 - col,
                        toBeTransposed.getColumns() - 1 - row));
            }
        }
    }

    private static void transposeMainDiag(Matrix result, Matrix toBeTransposed) {
        for (int col = 0; col < result.getColumns(); col++) {
            for (int row = 0; row < result.getRows(); row++) {
                result.setElement(row, col, toBeTransposed.getElement(col, row));
            }
        }
    }

    static Optional<Double> getDeterminant(Matrix input) {
        if (input.getColumns() != input.getRows()) {
            return Optional.empty();
        }
        Double result = calculateDeterminant(input);
        return Optional.of(result);
    }

    private static Double calculateDeterminant(Matrix input) {
        if (input.getRows() == 2) {
            return input.getElement(0, 0) * input.getElement(1, 1)
                - input.getElement(0, 1) * input.getElement(1, 0);
        }

        return 1.0;
    }
}

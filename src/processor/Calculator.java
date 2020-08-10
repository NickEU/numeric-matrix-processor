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

    public static Optional<Matrix> multiplyMatrices(Matrix a, Matrix b) {
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

    public static Matrix transposeMatrix(Matrix toBeTransposed, Transposition type) {
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

    private static Matrix transposeVerticalLine(Matrix result, Matrix toBeTransposed) {
        return toBeTransposed;
    }

    private static Matrix transposeHorizontalLine(Matrix result, Matrix toBeTransposed) {
        return toBeTransposed;
    }

    private static void transposeSideDiag(Matrix result, Matrix toBeTransposed) {
        for (int j = 0; j < result.getColumns(); j++) {
            for (int i = 0; i < result.getRows(); i++) {
                result.setElement(i, j, toBeTransposed.getElement(j, i));
            }
        }
    }

    private static void transposeMainDiag(Matrix result, Matrix toBeTransposed) {
        for (int j = 0; j < result.getColumns(); j++) {
            for (int i = 0; i < result.getRows(); i++) {
                result.setElement(i, j, toBeTransposed.getElement(j, i));
            }
        }
    }
}

package processor;

class Matrix {
    private final int rows;
    private final int columns;
    private final double[][] matrix;

    Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
    }

    Matrix(int rows, int columns, Matrix matrix, int excludeRow, int excludeCol) {
        this(rows, columns);
        for (int rowNew = 0, rowOld = 0; rowNew < rows; rowNew++, rowOld++) {
            if (rowNew == excludeRow) {
                rowOld++;
            }
            for (int colNew = 0, colOld = 0; colNew < columns; colNew++, colOld++) {
                if (colNew == excludeCol) {
                    colOld++;
                }
                setElement(rowNew, colNew, matrix.getElement(rowOld, colOld));
            }
        }
    }

    int getRows() {
        return rows;
    }

    int getColumns() {
        return columns;
    }

    double getElement(int row, int col) {
        return matrix[row][col];
    }

    void setElement(int row, int col, double newValue) {
        matrix[row][col] = newValue == -0.0 ? 0 : newValue;
    }

    boolean isSameSizeAs(Matrix another) {
        return this.getRows() == another.getRows() && this.getColumns() == another.getColumns();
    }

    @Override
    public String toString() {
        var result = new StringBuilder();
        for (double[] row : matrix) {
            for (double el : row) {
                result.append(el == (double) Math.round(el)
                    ? String.format("%-7.0f", el)
                    : String.format("%-7.2f", el));
                result.append(" ");
            }
            result.setLength(result.length() - 1);
            result.append("\n");
        }
        return result.toString();
    }
}

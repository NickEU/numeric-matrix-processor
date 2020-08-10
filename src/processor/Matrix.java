package processor;

class Matrix {
    private final int rows;
    private final int columns;
    private final double[][] matrix;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
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
        matrix[row][col] = newValue;
    }

    boolean isSameSizeAs(Matrix another) {
        return this.getRows() == another.getRows() && this.getColumns() == another.getColumns();
    }

    @Override
    public String toString() {
        var result = new StringBuilder();
        for (double[] row : matrix) {
            for (double el : row) {
                result.append(Double.valueOf(el).longValue() == Math.round(el)
                    ? String.format("%.0f", el) : el);
                result.append(" ");
            }
            result.setLength(result.length() - 1);
            result.append("\n");
        }
        return result.toString();
    }
}

package processor;

class Matrix {
    private final int rows;
    private final int columns;
    private final int[][] matrix;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new int[rows][columns];
    }

    int getRows() {
        return rows;
    }

    int getColumns() {
        return columns;
    }

    int getElement(int row, int col) {
        return matrix[row][col];
    }

    void setElement(int row, int col, int newValue) {
        matrix[row][col] = newValue;
    }

    boolean isSameSizeAs(Matrix another) {
        return this.getRows() == another.getRows() && this.getColumns() == another.getColumns();
    }

    @Override
    public String toString() {
        var result = new StringBuilder();
        for (int[] row : matrix) {
            for (int el : row) {
                result.append(el).append(" ");
            }
            result.append("\n");
        }
        return result.toString().stripTrailing();
    }
}

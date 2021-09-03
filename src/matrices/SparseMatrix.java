package matrices;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrix {

    public static final String INVALID_DIMENSIONS_EXCEPTION = "INVALID DIMENSIONS";

    private int m;
    private int n;
    private List<Triplet> rowFormat;

    public SparseMatrix(double[][] matrix) {
        if (matrix.length == 0)
            throw new RuntimeException(INVALID_DIMENSIONS_EXCEPTION);
        this.m = matrix.length;
        this.n = matrix[0].length;
        toDenseFormat(matrix);
    }

    private void toDenseFormat(double[][] matrix) {
        rowFormat = new ArrayList<>();
        for (int i = 0, j; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (matrix[i][j] != 0)
                    rowFormat.add(new Triplet(i, j, matrix[i][j]));
            }
        }
    }

    public double getEntry(int i, int j) {
        double value = 0;
        for (Triplet t : rowFormat) {
            if (t.i == i && t.j == j) {
                value = t.value;
                break;
            }
        }
        return value;
    }

    public void setEntry(int i, int j, double value) {
        if (i > m || j > n)
            throw new RuntimeException(INVALID_DIMENSIONS_EXCEPTION);

        boolean wasFound = false;
        for (Triplet t : rowFormat) {
            if (t.i == i && t.j == j) {
                t.value = value;
                wasFound = true;
            }
        }
        if (!wasFound)
            rowFormat.add(new Triplet(i, j, value));
    }

    public SparseMatrix addition(SparseMatrix b) {
        if (m != b.m || n != b.n)
            throw new RuntimeException(INVALID_DIMENSIONS_EXCEPTION);

        double[][] c = new double[m][n];
        for (int i = 0, j; i < m; i++) {
            for (j = 0; j < n; j++) {
                c[i][j] = this.getEntry(i, j) + b.getEntry(i, j);
            }
        }
        return new SparseMatrix(c);
    }

    public SparseMatrix multiplication(SparseMatrix b) {
        if (n != b.m)
            throw new RuntimeException(INVALID_DIMENSIONS_EXCEPTION);

        double[][] c = new double[m][b.n];
        for (int i = 0, j, k; i < m; i++) {
            for (j = 0; j < b.n; j++) {
                for (k = 0; k < n; k++) {
                    c[i][j] += this.getEntry(i, k) * b.getEntry(k, j);
                }
            }
        }
        return new SparseMatrix(c);
    }

    public SparseMatrix transpose() {
        double[][] transposed = new double[n][m];
        for (Triplet t : rowFormat)
            transposed[t.j][t.i] = t.value;
        return new SparseMatrix(transposed);
    }

    public double[][] asMatrix() {
        double[][] matrix = new double[m][n];
        for (Triplet t : rowFormat)
            matrix[t.i][t.j] = t.value;
        return matrix;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

}

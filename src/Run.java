import matrices.SparseMatrix;

import java.util.Arrays;

public class Run {

    private static void printMatrix(double[][] m) {
        for (double[] row : m) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {

        SparseMatrix a = new SparseMatrix(
                new double[][]{
                        new double[]{0, 0, 0},
                        new double[]{0, 1, 1},
                        new double[]{0, 0, 1}
                }
        );

        SparseMatrix b = new SparseMatrix(
                new double[][]{
                        new double[]{0, 0, 0, 0, 1},
                        new double[]{0, 0, 2, 0, 1},
                        new double[]{0, 4, 0, 0, 1}
                }
        );

        SparseMatrix c = a.transpose();

        printMatrix(a.multiplication(b).asMatrix());
        //printMatrix(a.addition(c).asMatrix());

    }

}

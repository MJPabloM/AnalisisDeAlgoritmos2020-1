package Vendedor;
import java.util.*;


public class Vendedor {

    private final int N, Comienzo;
    private final double[][] distancia;
    private List<Integer> Recorrido = new ArrayList<>();
    private double minRecorridoCost = Double.POSITIVE_INFINITY;
    private boolean ranSolver = false;

    public Vendedor(double[][] distancia) {
        this(0, distancia);
    }

    public Vendedor(int Comienzo, double[][] distancia) {
        N = distancia.length;

        if (N <= 2) throw new IllegalStateException("N <= 2 not yet supported.");
        if (N != distancia[0].length) throw new IllegalStateException("Matrix must be square (n x n)");
        if (Comienzo < 0 || Comienzo >= N) throw new IllegalArgumentException("Invalid Comienzo node.");
        if (N > 32)
            throw new IllegalArgumentException(
                    "Matrix too large! A matrix that size for the DP TSP problem with a time complexity of"
                            + "O(n^2*2^n) requires way too much computation for any modern home computer to handle");

        this.Comienzo = Comienzo;
        this.distancia = distancia;
    }

    // Returns the optimal Recorrido for the traveling salesman problem.
    public List<Integer> getRecorrido() {
        if (!ranSolver) solve();
        return Recorrido;
    }

    // Returns the minimal Recorrido cost.
    public double getRecorridoCost() {
        if (!ranSolver) solve();
        return minRecorridoCost;
    }

    // Solves the traveling salesman problem and caches solution.
    public void solve() {

        if (ranSolver) return;

        final int END_STATE = (1 << N) - 1;
        Double[][] memo = new Double[N][1 << N];

        // Add all outgoing edges from the Comienzoing node to memo table.
        for (int end = 0; end < N; end++) {
            if (end == Comienzo) continue;
            memo[end][(1 << Comienzo) | (1 << end)] = distancia[Comienzo][end];
        }

        for (int r = 3; r <= N; r++) {
            for (int subset : combinations(r, N)) {
                if (notIn(Comienzo, subset)) continue;
                for (int next = 0; next < N; next++) {
                    if (next == Comienzo || notIn(next, subset)) continue;
                    int subsetWithoutNext = subset ^ (1 << next);
                    double minDist = Double.POSITIVE_INFINITY;
                    for (int end = 0; end < N; end++) {
                        if (end == Comienzo || end == next || notIn(end, subset)) continue;
                        double newdistancia = memo[end][subsetWithoutNext] + distancia[end][next];
                        if (newdistancia < minDist) {
                            minDist = newdistancia;
                        }
                    }
                    memo[next][subset] = minDist;
                }
            }
        }

        // Connect Recorrido back to Comienzoing node and minimize cost.
        for (int i = 0; i < N; i++) {
            if (i == Comienzo) continue;
            double RecorridoCost = memo[i][END_STATE] + distancia[i][Comienzo];
            if (RecorridoCost < minRecorridoCost) {
                minRecorridoCost = RecorridoCost;
            }
        }

        int lastIndex = Comienzo;
        int state = END_STATE;
        Recorrido.add(Comienzo);

        // Reconstruct TSP path from memo table.
        for (int i = 1; i < N; i++) {

            int index = -1;
            for (int j = 0; j < N; j++) {
                if (j == Comienzo || notIn(j, state)) continue;
                if (index == -1) index = j;
                double prevDist = memo[index][state] + distancia[index][lastIndex];
                double newDist = memo[j][state] + distancia[j][lastIndex];
                if (newDist < prevDist) {
                    index = j;
                }
            }

            Recorrido.add(index);
            state = state ^ (1 << index);
            lastIndex = index;
        }

        Recorrido.add(Comienzo);
        Collections.reverse(Recorrido);

        ranSolver = true;
    }

    private static boolean notIn(int elem, int subset) {
        return ((1 << elem) & subset) == 0;
    }

    // This method generates all bit sets of size n where r bits
    // are set to one. The result is returned as a list of integer masks.
    public static List<Integer> combinations(int r, int n) {
        List<Integer> subsets = new ArrayList<>();
        combinations(0, 0, r, n, subsets);
        return subsets;
    }

    // To find all the combinations of size r we need to recurse until we have
    // selected r elements (aka r = 0), otherwise if r != 0 then we still need to select
    // an element which is found after the position of our last selected element
    private static void combinations(int set, int at, int r, int n, List<Integer> subsets) {

        // Return early if there are more elements left to select than what is available.
        int elementsLeftToPick = n - at;
        if (elementsLeftToPick < r) return;

        // We selected 'r' elements so we found a valid subset!
        if (r == 0) {
            subsets.add(set);
        } else {
            for (int i = at; i < n; i++) {
                // Try including this element
                set ^= (1 << i);

                combinations(set, i + 1, r - 1, n, subsets);

                // Backtrack and try the instance where we did not include this element
                set ^= (1 << i);
            }
        }
    }

    public static void main(String[] args) {
        // Create adjacency matrix
        int n = 10;
        double[][] distanciaMatrix = new double[n][n];
        for (double[] row : distanciaMatrix) java.util.Arrays.fill(row, 10000);
        distanciaMatrix[0][0] = 0;
        distanciaMatrix[0][1] = 13;
        distanciaMatrix[0][2] = 33;
        distanciaMatrix[0][3] = 28;
        distanciaMatrix[0][4] = 37;
        distanciaMatrix[0][5] = 7;
        distanciaMatrix[0][6] = 32;
        distanciaMatrix[0][7] = 40;
        distanciaMatrix[0][8] = 80;
        distanciaMatrix[0][9] = 26;
        distanciaMatrix[1][0] = 13;
        distanciaMatrix[1][1] = 0;
        distanciaMatrix[1][2] = 39;
        distanciaMatrix[1][3] = 83;
        distanciaMatrix[1][4] = 50;
        distanciaMatrix[1][5] = 68;
        distanciaMatrix[1][6] = 16;
        distanciaMatrix[1][7] = 98;
        distanciaMatrix[1][8] = 81;
        distanciaMatrix[1][9] = 55;
        distanciaMatrix[2][0] = 33;
        distanciaMatrix[2][1] = 39;
        distanciaMatrix[2][2] = 0;
        distanciaMatrix[2][3] = 80;
        distanciaMatrix[2][4] = 88;
        distanciaMatrix[2][5] = 49;
        distanciaMatrix[2][6] = 53;
        distanciaMatrix[2][7] = 75;
        distanciaMatrix[2][8] = 63;
        distanciaMatrix[2][9] = 55;
        distanciaMatrix[3][0] = 28;
        distanciaMatrix[3][1] = 83;
        distanciaMatrix[3][2] = 80;
        distanciaMatrix[3][3] = 0;
        distanciaMatrix[3][4] = 94;
        distanciaMatrix[3][5] = 4;
        distanciaMatrix[3][6] = 20;
        distanciaMatrix[3][7] = 6;
        distanciaMatrix[3][8] = 59;
        distanciaMatrix[3][9] = 76;
        distanciaMatrix[4][0] = 37;
        distanciaMatrix[4][1] = 50;
        distanciaMatrix[4][2] = 88;
        distanciaMatrix[4][3] = 94;
        distanciaMatrix[4][4] = 0;
        distanciaMatrix[4][5] = 81;
        distanciaMatrix[4][6] = 87;
        distanciaMatrix[4][7] = 85;
        distanciaMatrix[4][8] = 4;
        distanciaMatrix[4][9] = 19;
        distanciaMatrix[5][0] = 7;
        distanciaMatrix[5][1] = 68;
        distanciaMatrix[5][2] = 49;
        distanciaMatrix[5][3] = 4;
        distanciaMatrix[5][4] = 81;
        distanciaMatrix[5][5] = 0;
        distanciaMatrix[5][6] = 96;
        distanciaMatrix[5][7] = 53;
        distanciaMatrix[5][8] = 40;
        distanciaMatrix[5][9] = 37;
        distanciaMatrix[6][0] = 32;
        distanciaMatrix[6][1] = 16;
        distanciaMatrix[6][2] = 53;
        distanciaMatrix[6][3] = 20;
        distanciaMatrix[6][4] = 87;
        distanciaMatrix[6][5] = 96;
        distanciaMatrix[6][6] = 0;
        distanciaMatrix[6][7] = 80;
        distanciaMatrix[6][8] = 57;
        distanciaMatrix[6][9] = 68;
        distanciaMatrix[7][0] = 40;
        distanciaMatrix[7][1] = 98;
        distanciaMatrix[7][2] = 75;
        distanciaMatrix[7][3] = 6;
        distanciaMatrix[7][4] = 85;
        distanciaMatrix[7][5] = 53;
        distanciaMatrix[7][6] = 80;
        distanciaMatrix[7][7] = 0;
        distanciaMatrix[7][8] = 65;
        distanciaMatrix[7][9] = 41;
        distanciaMatrix[8][0] = 80;
        distanciaMatrix[8][1] = 81;
        distanciaMatrix[8][2] = 63;
        distanciaMatrix[8][3] = 59;
        distanciaMatrix[8][4] = 4;
        distanciaMatrix[8][5] = 40;
        distanciaMatrix[8][6] = 57;
        distanciaMatrix[8][7] = 65;
        distanciaMatrix[8][8] = 0;
        distanciaMatrix[8][9] = 97;
        distanciaMatrix[9][0] = 26;
        distanciaMatrix[9][1] = 55;
        distanciaMatrix[9][2] = 55;
        distanciaMatrix[9][3] = 76;
        distanciaMatrix[9][4] = 19;
        distanciaMatrix[9][5] = 37;
        distanciaMatrix[9][6] = 68;
        distanciaMatrix[9][7] = 41;
        distanciaMatrix[9][8] = 97;
        distanciaMatrix[9][9] = 0;


        int ComienzoNode = 0;
        Vendedor solver =
                new Vendedor(ComienzoNode, distanciaMatrix);

        System.out.println("Recorrido: " + solver.getRecorrido());


        System.out.println("Distancia recorrida: " + solver.getRecorridoCost());
    }
}

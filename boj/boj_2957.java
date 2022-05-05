import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] level = new int[N + 2];

        TreeSet<Integer> tree = new TreeSet<>();
        tree.add(0);
        tree.add(N + 1);
        level[0] = -1;
        level[N + 1] = -1;

        StringBuilder sb = new StringBuilder();
        long C = 0;
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            level[n] = Math.max(level[tree.lower(n)], level[tree.higher(n)]) + 1;
            tree.add(n);

            C += level[n];
            sb.append(C + "\n");
        }

        System.out.println(sb.toString());
    }
}
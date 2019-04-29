public class EditDistance {
    // BOTTOM UP DP

    public static int minEditDist(String a, String b) {
        return getDist(a, b);
    }

    public static int getDist(String a, String b) {
        if (a.length() == 0) {
            return b.length();
        }
        if (b.length() == 0) {
            return a.length();
        }

        //Set up DP table
        int[][] DP = new int[a.length()+1][b.length()+1];
        int cost;

        for (int i = 0; i < a.length(); i++) {
            DP[i+1][0] = DP[i][0] + 1;
        }
        for (int i = 0; i < b.length(); i++) {
            DP[0][i+1] = DP[0][i] + 1;
        }


        for (int i = 0; i < a.length(); i++) { // String 1
            for (int j = 0; j < b.length(); j++) { // String 2
                cost = (a.charAt(i) == b.charAt(j)) ? 0 : 1; // Cost is 0 if same char, but 2 if char must be replaced.
                DP[i+1][j+1] = Math.min(Math.min(DP[i][j+1] + 1, DP[i+1][j] + 1), DP[i][j] + cost);
//                System.out.print(DP[i+1][j+1]);
            }
//            System.out.println("");
        }
        return DP[a.length()][b.length()];
    }

}

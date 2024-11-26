import java.util.Scanner;

class KMPAlgorithm {
    int KMPSearch(String pat, String txt) {
        int M = pat.length(), N = txt.length();

        int[] lps = new int[M];
        computeLPSArray(pat, M, lps);

        int i = 0,j = 0; // i == index for text , j == index for pattern
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                return (i-j);
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
        return -1;
    }

    void computeLPSArray(String pat, int M, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String txt = scanner.nextLine();

        System.out.print("Enter the pattern to search for: ");
        String pat = scanner.nextLine();

        int ans = new KMPAlgorithm().KMPSearch(pat, txt);
        if(ans >= 0)
            System.out.println("Pattern Found at position "+ans);
        else
            System.out.println("Pattern Not Found\n");
        

        scanner.close();
    }
}

//T.C O(m+n) m = len(patt),n = len(txt)   ,  S.C O(m) prefix table


package jikaoTest.honorTest;

import java.io.*;

public class ChooseTimes {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        in.nextToken();
        int rows = (int) in.nval;
        int[][] arr = new int[rows][];
        for (int i = 0; i < rows; i++) {
            in.nextToken();
            int length = (int) in.nval;
            arr[i] = new int[length];
            for (int j = 0; j < arr[i].length; j++) {
                in.nextToken();
                arr[i][j] = (int) in.nval;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            out.println(countTimes(arr[i]));
        }
        out.flush();
    }

    public static int countTimes(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if ((arr[i] + arr[j]) == (arr[i] ^ arr[j])) {
                    count++;
                }
            }
        }
        return count;
    }
}

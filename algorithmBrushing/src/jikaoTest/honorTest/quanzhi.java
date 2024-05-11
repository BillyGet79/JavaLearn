package jikaoTest.honorTest;

import java.io.*;

public class quanzhi {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        in.nextToken();
        int N = (int) in.nval;
        in.nextToken();
        int M = (int) in.nval;

        float[][] U = new float[N][2];
        float[][][] V = new float[M][N][2];

        for (int i = 0; i < N; i++) {
            in.nextToken();
            U[i][0] = (float) in.nval;
            in.nextToken();
            U[i][1] = (float) in.nval;
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                in.nextToken();
                V[i][j][0] = (float) in.nval;
                in.nextToken();
                V[i][j][1] = (float) in.nval;
            }
        }

        out.println(getMinValueUser(U, V));
        out.flush();
    }

    public static int getMinValueUser(float[][] U, float[][][] V) {
        int M = V.length;
        int ans = 0;
        float min = Float.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            //先进行向量相乘
            float[] cur = culMatrix(U, V[i]);
            //得到结果后计算权值
            float value = culValue(cur);
            //与当前的值进行比对
            if (value < min) {
                ans = i;
                min = value;
            }
        }
        return ans;
    }

    public static float[] culMatrix(float[][] U, float[][] V){
        int N = U.length;
        float[] ans = new float[2];
        for (int i = 0; i < N; i++) {
            //计算实部
            ans[0] += U[i][0] * V[i][0] - U[i][1] * V[i][1];
            //计算虚部
            ans[1] += U[i][1] * V[i][0] + U[i][0] * V[i][1];
        }
        return ans;
    }

    public static float culValue(float[] a) {
        return a[0] * a[0] + a[1] * a[1];
    }
}

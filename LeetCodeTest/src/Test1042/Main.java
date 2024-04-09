package Test1042;

import java.util.ArrayList;

/**
 * 1042.不邻接植花
 *      有 n 个花园，按从1到 n 标记。另有数组 paths ，其中 paths[i] = [xi, yi]描述了花园xi 到花园yi 的双向路径。在每个花园中，你打算种下四种花之一。
 *      另外，所有花园 最多 有 3 条路径可以进入或离开.
 *      你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 *      以数组形式返回 任一 可行的方案作为答案answer，其中answer[i]为在第(i+1)个花园中种植的花的种类。花的种类用 1、2、3、4 表示。保证存在答案。
 */

public class Main {
    public static void main(String[] args) {
        int n = 4;
        int[][] paths = {{1,2},{2,3},{3,4},{4,1},{1,3},{2,4}};
        Solution solution = new Solution();
        print(solution.gardenNoAdj(n, paths));
    }

    public static void print(int[] arr){
        System.out.print("[");
        if(arr != null){
            for (int i = 0; i < arr.length; i++) {
                System.out.print(i == arr.length-1 ? arr[i] : arr[i] + ", ");
            }
        }
        System.out.println("]");
    }
}
class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        ArrayList<Integer>[] adList = new ArrayList[n+1];
        for(int i=0;i<adList.length;i++){
            adList[i] = new ArrayList<>();
        }
        for (int[] path : paths) {
            adList[path[0]].add(path[1]);
            adList[path[1]].add(path[0]);
        }
        int[] answer = new int[n];
        answer[0] = 1;
        for(int i=1;i<adList.length;i++){
            if(answer[i-1]==0){
                boolean[] color = new boolean[n+1];     //获取邻接元素的颜色
                for(int j=0;j<adList[i].size();j++){
                    int index = adList[i].get(j);
                    int colors = answer[index-1];
                    if (colors != 0){
                        color[colors] = true;
                    }
                }
                for(int j=1;j<color.length;j++){
                    if(!color[j]){
                        answer[i-1]=j;
                        break;
                    }
                }
            }
        }
        return answer;
    }
}

package class47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Code03_DinicAlgorithm {

    public static class Edge {
        //从哪个点来
        public int from;
        //到哪个点去
        public int to;
        //这条边还剩余的可用容载量
        public int available;

        public Edge(int from, int to, int available) {
            this.from = from;
            this.to = to;
            this.available = available;
        }
    }

    public static class Dinic {
        //有多少个点
        private int N;
        //记录每一个节点的出边在edges中的编号
        private ArrayList<ArrayList<Integer>> nexts;
        //所有的边
        //这里的所有的边包括所有的反向边，初始值都为0
        //对于所有边找到其反向边，只需要对边的下标大小异或上1即可。
        private ArrayList<Edge> edges;
        private int[] depth;
        private int[] cur;

        public Dinic(int nums) {
            //假设多一个点来处理
            //因为传入的值可能会比真实的点的个数少一个
            N = nums + 1;
            nexts = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                nexts.add(new ArrayList<>());
            }
            edges = new ArrayList<>();
            depth = new int[N];
            cur = new int[N];
        }

        //传入的参数：from，to以及权值
        public void addEdge(int u, int v, int r) {
            int m = edges.size();
            //建立正向边
            edges.add(new Edge(u, v, r));
            nexts.get(u).add(m);
            //建立反向边
            edges.add(new Edge(v, u, 0));
            nexts.get(v).add(m + 1);
        }

        //最大流算法
        public int maxFlow(int s, int t) {
            int flow = 0;
            //通过bfs，如果能从s到t，那么就算出一个流量出来
            //如果通过遍历发现到不了了，那么就跳出循环返回即可。
            while (bfs(s, t)) {
                Arrays.fill(cur, 0);
                //在dfs中，是会调整边的权值的，通过边的权值的调整，可能会使得bfs检查发现不可达
                flow += dfs(s, t, Integer.MAX_VALUE);
            }
            return flow;
        }

        private boolean bfs(int s, int t) {
            // depth[s] == 0    所以这里就不写了
            //宽度优先，一定要用队列来实现
            LinkedList<Integer> queue = new LinkedList<>();
            queue.addFirst(s);
            //记录遍历过的节点
            boolean[] visited = new boolean[N];
            visited[s] = true;
            while (!queue.isEmpty()) {
                //u就是当前节点
                int u = queue.pollLast();
                //在它所有的边中一个一个遍历
                for (int i = 0; i < nexts.get(u).size(); i++) {
                    //在nexts数组当中获取到边号，再从edges数组当中获取到边
                    Edge e = edges.get(nexts.get(u).get(i));
                    //找到边的去向
                    int v = e.to;
                    //如果这条边没被遍历过，并且权值大于0，则说明可以遍历
                    if (!visited[v] && e.available  > 0) {
                        //先调整visited数组对应的值
                        visited[v] = true;
                        //在调整depth数组的值，在前一个节点的基础上+1即可
                        depth[v] = depth[u] + 1;
                        queue.addFirst(v);
                    }
                }
            }
            return visited[t];
        }

        //当前来到了s点，最终目标是t
        //r，收到的任务
        //返回值：收集到的流，flow <= r
        private int dfs(int s, int t, int r) {
            //当到达t的情况下，收到多少任务那就返回多少任务
            if (s == t || r == 0) {
                return r;
            }
            //记录单次dfs收集到的流
            int f = 0;
            //记录总收集到的流
            int flow = 0;
            //s从哪条边开始尝试
            for (; cur[s] < nexts.get(s).size(); cur[s]++) {
                //获取边和反向边
                int ei = nexts.get(s).get(cur[s]);
                Edge e = edges.get(ei);
                Edge o = edges.get(ei ^ 1);
                //看下一条边是否为bfs遍历的下一层
                //并且下发下去的任务最后是否完成了一定的量
                if (depth[e.to] == depth[s] + 1 && (f = dfs(e.to, t, Math.min(e.available, r))) != 0) {
                    //调整节点权值
                    e.available -= f;
                    o.available += f;
                    //调整收集到最大流
                    flow += f;
                    r -= f;
                    //如果当前任务完成了，直接break，后面的不用再看了
                    if (r <= 0) {
                        break;
                    }
                }
            }
            return flow;
        }
    }

}

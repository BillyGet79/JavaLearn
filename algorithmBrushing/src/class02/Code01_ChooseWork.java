package class02;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 题目1
 * 给定数组hard和money，长度都为N
 * hard[i]表示i号的难度，money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力
 * 每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班
 * 返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 */
public class Code01_ChooseWork {

    //利用封装思想将hard和money封装起来，然后根据难度从小到大排序
    //如果难度值相同，那么钱多的排前面
    //我们考虑实际情况，因为每一份工作都可以有无数个人去工作，所以难度相同的情况下报酬少的工作可以不存在
    //当然，如果某些工作难度大的情况下收入还比难度小的小，那么也可以不做保留
    //这样我们最后留下来的工作难度和报酬都是递增的
    //在这种情况下我们就可以遍历ability数组去寻找每个人能够获得的最大收入是多少

    public static class Job {
        public int hard;
        public int money;

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    public static int[] getMoneys(int[] hard, int[] money, int[] ability) {
        int N = hard.length;
        //封装
        Job[] jobs = new Job[N];
        for (int i = 0; i < N; i++) {
            jobs[i] = new Job(hard[i], money[i]);
        }
        //排序
        Arrays.sort(jobs, (o1, o2) -> o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money));
        //因为我们要根据难度去重，最好的去重方式就是使用有序表去重
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(jobs[0].hard, jobs[0].money);
        //通过pre保存上一个工作来确保报酬和难度都是递增的
        Job pre = jobs[0];
        for (int i = 1; i < N; i++) {
            if (jobs[i].hard != pre.hard && jobs[i].money > pre.money) {
                map.put(jobs[i].hard, jobs[i].money);
                pre = jobs[i];
            }
        }
        //在这种情况下，我们只需要根据难度获取工作即可
        //可以直接使用有序表当中的floorKey方法，找到离ability[i]最近并且<=ability[i]的工作
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;
    }
}

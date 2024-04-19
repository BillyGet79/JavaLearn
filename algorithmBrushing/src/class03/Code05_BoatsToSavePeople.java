package class03;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给定一个正数数组arr，代表若干人的体重
 * 再给定一个正数limit，表示所有船共同拥有的载重量
 * 每艘船最多坐两人，且不能超过载重
 * 想让所有的人同时过河，并且用最好的分配方法让船尽量少
 * 返回最少的船数
 * 测试链接 : <a href="https://leetcode.cn/problems/boats-to-save-people/">...</a>
 */
public class Code05_BoatsToSavePeople {

    /**
     * 算法主要思路：
     * 首先先对数组进行排序，然后检索这个数组，如果有成员体重大于limit，直接返回无穷大
     * 然后我们找到<=limit/2最右侧的下标，以这个下标以及这个数的右侧一个数的中间做分界线进行遍历判别
     * 如果两个指针指向的元素不能进行匹配，左指针指向的元素标记为×，左指针向左移动，
     * 如果两个指针指向的元素能够进行匹配，右指针向右移动，并且记录移动长度
     * 直到移动到下一位不能匹配，则停止移动，将记录下的移动长度与左侧指针向左相同的长度的成员进行匹配（如果左侧有的话）
     * 在这种情况下，有两种情况，即左侧先消耗完和右侧先消耗完的情况
     * 如果右侧先消耗完，则最终数量为匹配人数/2+(左侧剩余人数+标记为×人数)/2，并且向上取整
     * 如果左侧先消耗完，则最终数量为匹配人数/2+标记为×人数/2+右侧剩余人数，并且向上取整
     * 这个代码建议自己去写写，很考验coding能力
     * @param people
     * @param limit
     * @return
     */
    public static int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) {
            return 0;
        }

        int N = people.length;
        Arrays.sort(people);
        //如果最后一个元素的体重没有大于limit的话，那么所有人的体重都没有大于limit
        if (people[N - 1] > limit) {
            return -1;
        }
        //首先找到<=limit/2最右侧的下标
        //答案当中使用的是顺序查找的方式，但是这里我要搞骚的（没错，二分查找）
        //注意：虽然顺序查找时间复杂度更高，但是上面有一个排序，时间复杂已经为O(nlogn)了，所以不用担心这里顺序查找会影响时间复杂度
        int left = 0;
        int right = N - 1;
        int index = -1; //用来记录所要找的下标
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (people[mid] <= limit / 2) {
                index = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        left = index;
        //这个时候left与right都已经指向了<=limit/2最右侧的下标
        //看看left指向的是否是真的<=limit/2
        if (left == 0 && people[left] > limit / 2) {
            return N;
        }
        //用一个副本保存left位置
        int lessR = left;
        //这个时候我们正式开始算法执行
        right = left + 1;
        int noUsed = 0;
        while (left >= 0) {
            int solved = 0;
            while (right < N && people[left] + people[right] <= limit) {
                right++;
                solved++;
            }
            if (solved == 0) {
                noUsed++;
                left--;
            } else {
                left = Math.max(-1, left - solved);
            }
        }
        int all = lessR + 1;
        int used = all - noUsed;
        int moreUnsolved = (N - all) - used;
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }

    public static void main(String[] args) {
        int[] people = {251,910,983,811,903};
        int limit = 1000;
        System.out.println(numRescueBoats(people, limit));
    }
}

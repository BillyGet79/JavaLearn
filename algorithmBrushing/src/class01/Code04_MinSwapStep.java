package class01;

/**
 * 题目4
 * 一个数组中只有两种字符'G'和'B'
 * 可以让所有的G都放在左侧，所有的B都放在右侧
 * 也可以让所有的B都放在左侧，所有的G都放在右侧
 * 但是只能在相邻字符之间进行交换操作
 * 返回至少需要交换几次
 */
public class Code04_MinSwapStep {

    //贪心算法
    //（这道题千万不要用冒泡的思想，不然直接没分（仔细想想为什么））
    //我们可以随意举例一个序列，我们可以随意进行交换操作，我们会发现，如果要让交换次数最少，我们没必要让后面的'G'到前面的'G'的前面
    //所以字符串中第一个出现的'G'交换到最后一定会出现在字符串的第一个位置，第二个'G'出现在第二个位置，后面以此类推
    //我们设置两个指针，一个left指向现在处理的下标，一个指针index来进行处理当前left指向的下标
    //初始时，left和index全部指向最左侧元素，如果两者指向的元素为'G',那么两者全部右移
    //直到二者指向的元素为'B'开始，进行算法操作
    //让left不动，index向右移动
    //如果index指向的元素为'B'，则继续向右移动
    //如果index指向的元素为'G'，则计算left与index的差值，并且定义一个变量做统计，left和index全部向右移动
    //直到index指向了最后一个元素并且判断或统计完成之后，算法结束，返回最终结果
    //这个算法好好思考一下“贪心”贪在了哪里，重点在于理解如何让交换次数最少，而不是上来用冒泡方式解决，因为那样一定不是交换最少的策略
    public static int minSteps1(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] str = s.toCharArray();
        //首先考虑G在左B在右
        int left = 0;
        int index = 0;
        int ans1 = 0;
        while (index != str.length) {
            if (str[index] == 'G') {
                ans1 += index - left;
                index++;
                left++;
            } else {
                index++;
            }
        }
        //然后考虑B在左G在右
        left = 0;
        index = 0;
        int ans2 = 0;
        while (index != str.length) {
            if (str[index] == 'B') {
                ans2 += index - left;
                index++;
                left++;
            } else {
                index++;
            }
        }
        return Math.min(ans1, ans2);
    }

    public static int minSteps2(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int step1 = 0;
        int step2 = 0;
        int gi = 0;
        int bi = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'G') { // 当前的G，去左边   方案1
                step1 += i - (gi++);
            } else {// 当前的B，去左边   方案2
                step2 += i - (bi++);
            }
        }
        return Math.min(step1, step2);
    }

    // 为了测试
    public static String randomString(int maxLen) {
        char[] str = new char[(int) (Math.random() * maxLen)];
        for (int i = 0; i < str.length; i++) {
            str[i] = Math.random() < 0.5 ? 'G' : 'B';
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            String str = randomString(maxLen);
            int ans1 = minSteps1(str);
            int ans2 = minSteps2(str);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}

package class02;

/**
 * 题目2
 * 贩卖机只支持硬币支付，且收退都只支持10，50，100三种面额
 * 一次购买只能出一瓶可乐，且投钱和找零都遵循优先使用大钱的原则
 * 需要购买的可乐数量是m
 * 其中手头拥有的10、50、100的数量分别是a、b、c
 * 可乐的价格是x（x是10的倍数）
 * 请计算出需要投入硬币的次数
 */
public class Code02_Cola {

    //携程的笔试题
    //这个问题由于只给了三个面额，而且在这道笔试题中可乐的数量给的非常庞大，所以我们必须在O(1)的时间复杂度下解决这个问题
    //整体的思路流程如下：
    //  我们先看100面额的硬币能够买多少瓶可乐，然后再看50面额的硬币能够买多少瓶可乐，最后看10面额的硬币能够买多少瓶可乐
    //  前两个面额买完可乐之后找零的钱全部加到后面的面额上去
    //  这三步中只要有一个面额计算完刚好购买的可乐数量大于m，则停止运算，得出结果
    //但是在coding层面上可能会出现问题：当前最大面额的硬币可能无法全部消耗完
    //例如：当前有4个100面额的硬币，一瓶可乐250块钱，所以我们只能消耗掉3个100硬币，有一个100硬币是消耗不掉的

    //暴力方法，用来测试的
    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = { 100, 50, 10 };
        int[] zhang = { c, b, a };
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }

    //正式方法
    //难度有些大，直接看代码
    //m：要买的可乐数量
    //100元有a张
    //50元有b张
    //10元有c张
    //可乐单价x
    public static int putTimes(int m, int a, int b, int c, int x) {
        //0 1 2
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        //总共需要多少次投币
        int puts = 0;
        //之前面值的钱还剩下多少总钱数
        int preQianRest = 0;
        //之前面值的钱还剩下多少总张数
        int preQianZhang = 0;
        for (int i = 0; i < 3 && m != 0; i++) {
            //由于每次购买会遗留下历史问题，所以我们要特殊处理当前面值购买的第一瓶可乐
            //要用之前剩下的钱、当前面值的钱，共同买第一瓶可乐
            //之前的面值剩下多少钱，是preQianRest
            //之前的面值剩下多少张，是preQianZhang
            //之所以之前的面值会剩下来，一定是剩下的钱，一直攒不出一瓶可乐的单价
            //当前的面值付出一些钱+之前剩下的钱，此时有可能凑出一瓶可乐来
            //那么当前面值参与搞定第一瓶可乐，需要掏出多少张呢？就是curQianFirstBuyZhang
            int curQianFirstBuyZhang = (x - preQianRest + qian[i] - 1) / qian[i];   //这里利用了向上取整的技巧，即a / x向上取整，则将计算表达式调整为(a + x - 1) / x
            if (zhang[i] >= curQianFirstBuyZhang) { //如果之前的钱和当前面值的钱，能凑出第一瓶可乐
                //凑出来了一瓶可乐也可能存在找钱的情况
                giveRest(qian, zhang, i + 1, (preQianRest + qian[i] * curQianFirstBuyZhang) - x , 1);
                puts += curQianFirstBuyZhang + preQianZhang;
                zhang[i] -= curQianFirstBuyZhang;
                m--;
            } else {    //如果之前的钱和当前面值的钱，不能凑出第一瓶可乐
                //更新preQianRest与preQianZhang，把当前的张和当前的总面额全部变成历史
                preQianRest += qian[i] * zhang[i];
                preQianZhang += zhang[i];
                //并且跳过此次循环，直接找下一个面值
                continue;
            }
            //历史问题清完了，所以我们要开始看当前面额能买多少瓶可乐了
            //凑出第一瓶可乐之后，当前的面值有可能能继续买更多的可乐
            //以下过程就是后续的可乐怎么用当前面值的钱来买
            //用当前面值的钱，买一瓶可乐需要几张
            int curQianBuyOneColaZhang = (x + qian[i] - 1) / qian[i];
            //用当前面值的钱，一共可以搞定几瓶可乐
            int curQianBuyColas = Math.min(zhang[i] / curQianBuyOneColaZhang, m);
            //用当前面值的钱，每搞定一瓶可乐，收货机会吐出多少零钱
            int oneTimeRest = qian[i] * curQianBuyOneColaZhang - x;
            //每次买一瓶可乐，吐出的找零总钱是oneTimeRest
            //一共买的可乐数是curQianBuyColas，所以把零钱去提升后面几种面值的硬币数
            //就是giveRest的含义
            giveRest(qian, zhang, i + 1, oneTimeRest, curQianBuyColas);
            //当前面值去搞定可乐这件事，一共投了几次币
            puts += curQianBuyOneColaZhang * curQianBuyColas;
            //还剩下多少瓶可乐需要去搞定，继续用后面的面值搞定去吧
            m -= curQianBuyColas;
            //当前面值可能剩下若干张，需要参与到后续买可乐的过程中去
            //所以要更新preQianRest和preQianZhang
            zhang[i] -= curQianBuyOneColaZhang * curQianBuyColas;
            preQianRest = qian[i] * zhang[i];
            preQianZhang = zhang[i];
        }
        return m == 0 ? puts : -1;
    }

    public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
        for (; i < 3; i++) {
            zhang[i] += (oneTimeRest / qian[i]) * times;
            oneTimeRest %= qian[i];
        }
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("如果错误会打印错误数据，否则就是正确");
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int ans1 = putTimes(m, a, b, c, x);
            int ans2 = right(m, a, b, c, x);
            if (ans1 != ans2) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");
                break;
            }
        }
        System.out.println("test end");
    }


}

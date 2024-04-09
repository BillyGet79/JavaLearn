package class01;


public class Test {

    public static void main(String[] args) {
        int test = 7;
        int test2 = -test;
        System.out.println(test2);
        // a & (-a)
        System.out.println(test & test2);


        int a = 6;
        int b = 3;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        int[] arr = {3, 1, 100};

        int i = 0;
        int j = 0;

        //此处的逻辑是：
        //第一步，arr[i]直接变为0
        //第二步和第三步：0 ^ 0 = 0
        //出现这样情况的原因：所进行操作的数为存储在内存的同一个位置的数
        //注：java中数组变量存储的是指向堆内存的地址
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

        System.out.println(arr[i] + " ," + arr[j]);

    }
}

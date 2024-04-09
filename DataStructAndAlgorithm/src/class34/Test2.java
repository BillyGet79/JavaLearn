package class34;

public class Test2 {

    public static void main(String[] args) {
        int[] arr = new int[10];
        //arr[0]    int 32位
        //...
        //arr[9]    int 32位
        int i = 179;

        //i%32
        int status = (arr[i / 32] & (1 << (i % 32)));


    }
}

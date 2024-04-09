package class38;

public class Code02_EatGrass {

    //如果n份草，最终先手赢，返回“先手”
    //反之，则返回“后手”
    public static String whoWin(int n) {
        if (n < 5) {
            return n == 0 || n == 2 ? "后手" : "先手";
        }
        //进到这个过程里来，当前的先手，先选
        int want = 1;
        while (want <= n) {
            //want  n-want
            //如果后续过程中后手赢了，那么就是当前的羊赢了
            if (whoWin(n - want).equals("后手")) {
                return "先手";
            }
            want *= 4;
        }
        return "后手";
    }

    public static String winner(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + " : " + whoWin(i));
        }
    }
}

package class29;

public class Code03_ReservoirSampling {


    //请等概率返回i~i中的一个数字
    public static int random(int i){
        return (int)(Math.random() * i) + 1;
    }

    public static void main(String[] args) {
        int test = 1000000;
        int[] count = new int[17];
        for (int i = 0; i < test; i++){
            int[] bag = new int[10];
            int bagi = 0;
            for (int num = 1; num <= 16; num++){
                if (num <= 10){
                    bag[bagi++] = num;
                } else {    //num > 10;
                    if (random(num) <= 10){ //一定要把num球入袋子
                        bagi = (int)(Math.random() * 10);
                        bag[bagi] = num;
                    }
                }
            }
            for (int num : bag){
                count[num]++;
            }
        }
        for (int i = 1; i <= 16; i++){
            System.out.print(count[i] + " ");
        }
        System.out.println();
    }

}

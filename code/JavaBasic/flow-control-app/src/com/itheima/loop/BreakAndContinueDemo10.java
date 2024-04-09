package com.itheima.loop;

public class BreakAndContinueDemo10 {
    public static void main(String[] args) {
        //目标：理解break和continue的作用
        //场景：胶乳你又有老婆了，然后你犯错了，你老婆罚你做5天家务，每天都是洗碗
        //但是在第三天后心软了原谅你了不用洗了
        for (int i = 0; i < 5; i++) {
            System.out.println("快乐的洗碗~~~~");
            if (i == 2){
                break;  //跳出并结束当前循环的执行
            }
        }

        //continue 跳出当前循环的档次执行，进入循环的下一次
        //场景：假如你又有老婆了，然后你犯错了，你老婆罚你做5天家务，
        //每天都是洗碗。但是洗碗到第三天后心软了，原谅你了不用洗了，但是依然不解恨，
        //继续洗第四天第五天
        for (int i = 1; i <= 5; i++) {
            if (i == 3){
                continue;
            }
            System.out.println("快乐的洗碗~~~~~");
        }
    }
}

package com.itheima;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * ATM系统的入口类
 */
public class ATMSystem {
    public static void main(String[] args) {
        //1、定义账户类
        //2、定义一个集合容器，负责以后存储全部的账户对象，进行相关的业务操作
        ArrayList<Account> accounts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        //3、展示系统的首页
        while (true) {
            System.out.println("===================黑马ATM系统========================");
            System.out.println("1、账户登录");
            System.out.println("2、账户开户");

            System.out.println("请您选择操作");
            int command = sc.nextInt();

            switch (command){
                case 1:
                    //用户登录操作
                    login(accounts, sc);
                    break;
                case 2:
                    //用户账户开户
                    register(accounts, sc);
                    break;
                default:
                    System.out.println("您输入的操作命令不存在~~");
            }
        }
    }

    /**
     * 登录功能
     * @param accounts  全部账户对象的集合
     * @param sc    扫描器
     */
    private static void login(ArrayList<Account> accounts, Scanner sc) {
        System.out.println("==================系统登录操作======================");
        //1、判断账户集合中是否存在账户，如果不存在账户，登录功能不能运行
        if (accounts.size() == 0){
            System.out.println("对不起，当前系统中，无任何账户，请先开户，再来登录~~");
            return;
        }

        //2、正式进入登陆操作
        while (true) {
            System.out.println("请您输入登陆卡号：");
            String cardId = sc.next();
            //3、判断卡号是否存在
            Account acc = getAccountByCardId(accounts, cardId);
            if (acc != null){
                while (true) {
                    //卡号存在
                    //4、让用户输入密码，认证密码
                    System.out.println("请您输入登陆密码：");
                    String passWord = sc.next();
                    if (acc.getPassWord().equals(passWord)){
                        //登陆成功了
                        System.out.println("恭喜您，" + acc.getUserName() + "，先生/女士进入系统，您的卡号是：" + acc.getCarId());
                        //展示登陆后的操作页
                        showUserCommand(sc, acc, accounts);
                        return;
                    }else {
                        System.out.println("对不起，您输入的密码有误");
                    }
                }
            }else {
                System.out.println("对不起，系统中不存在该账户卡号~~");
            }
        }
    }

    /**
     * 展示登陆后的操作页
     */
    private static void showUserCommand(Scanner sc, Account acc, ArrayList<Account> accounts) {
        while (true) {
            System.out.println("===============用户操作页=====================");
            System.out.println("1、查询账户");
            System.out.println("2、存款");
            System.out.println("3、取款");
            System.out.println("4、转账");
            System.out.println("5、修改密码");
            System.out.println("6、退出");
            System.out.println("7、注销账户");
            System.out.println("请选择：");
            int command = sc.nextInt();
            switch (command){
                case 1:
                    //查询账户
                    showAccount(acc);
                    break;
                case 2:
                    //存款
                    depositMoney(acc, sc);
                    break;
                case 3:
                    //取款
                    drawMoney(acc, sc);
                    break;
                case 4:
                    //转账
                    transferMoney(acc, sc, accounts);
                    break;
                case 5:
                    //修改密码
                    updatePassWord(acc, sc);
                    return;
                case 6:
                    //退出
                    System.out.println("推出成功，欢迎下次光临~~");
                    return;
                case 7:
                    //注销账户
                    if (deleteAccount(acc, sc, accounts)){
                        return;
                    }else {
                        break;
                    }
                default:
                    System.out.println("您输入的操作命令不正确~~");
            }
        }
    }

    /**
     * 销户功能
     * @param acc
     * @param sc
     * @param accounts
     */
    private static boolean deleteAccount(Account acc, Scanner sc, ArrayList<Account> accounts) {
        System.out.println("===========用户销户=============");
        System.out.println("您真的要销户？y/n");
        String rs = sc.next();
        switch (rs){
            case "y":
                if (acc.getMoney() > 0){
                    System.out.println("您账户中还有钱没有取完，不允许销户~~");
                }else {
                    accounts.remove(acc);
                    System.out.println("您的账户销户完成~~");
                    return true;
                }
                break;
            default:
                System.out.println("好的，当前账户继续保留");
        }
        return false;
    }

    /**
     * 修改密码
     * @param acc   当前登陆成功的账户对象
     * @param sc    扫描器
     */
    private static void updatePassWord(Account acc, Scanner sc) {
        System.out.println("=================用户密码修改=================");
        while (true) {
            System.out.println("请您输入当前密码：");
            String passWord = sc.next();
            //1、判断这个密码是否正确
            if (acc.getPassWord().equals(passWord)){
                while (true) {
                    //密码正确
                    //2、输入新密码
                    System.out.println("请您输入新密码：");
                    String newPassWord = sc.next();

                    System.out.println("请您确认新密码：");
                    String okPassWord = sc.next();

                    if (newPassWord.equals(okPassWord)){
                        //2次密码一致，可以修改了
                        acc.setPassWord(newPassWord);
                        System.out.println("恭喜您，您密码修改成功~~");
                        return;
                    }else {
                        System.out.println("您输入的两次密码不一致~~");
                    }
                }


            }else {
                System.out.println("您输入的密码不正确");
            }
        }
    }

    /**
     * 转账功能
     * @param acc   自己的账户对象
     * @param sc    扫描器
     * @param accounts  全部账户的集合
     */
    private static void transferMoney(Account acc, Scanner sc, ArrayList<Account> accounts) {
        System.out.println("=============用户转账操作=================");
        //1、判断是否足够两个账户
        if (accounts.size() < 2){
            System.out.println("当前系统中，不足2个账户，不能进行转账，请去开户吧~~");
            return;
        }

        //2、判断自己的账户是否有钱
        if (acc.getMoney() == 0){
            System.out.println("对不起，您自己都没钱，就别转了吧~~");
            return;
        }

        while (true) {
            //3、真正开始转账
            System.out.println("请您输入对方账户的卡号");
            String cardId = sc.next();

            //这个卡号不能是自己的卡号
            if (cardId.equals(acc.getCarId())){
                System.out.println("对不起，您不可以给自己进行转账~~");
                continue;
            }

            //判断这个卡号是否存在，根据这个卡号去查询对方账户对象
            Account account = getAccountByCardId(accounts, cardId);
            if (account == null){
                System.out.println("对不起，您输入的这个账号不存在~~");
            }else {
                //这个账户对象存在，继续认证
                String userName = account.getUserName();
                String tip = "*" + userName.substring(1);
                System.out.println("请您输入[" + tip + "]的形式");
                String preName = sc.next();

                //认证姓氏是否输入正确
                if (userName.startsWith(preName)){
                    while (true) {
                        //认证通过，真正开始转账
                        System.out.println("请您输入转账金额：");
                        double money = sc.nextDouble();
                        //判断余额是否足够
                        if (money > acc.getMoney()){
                            System.out.println("对不起，您余额不足，您最多可以转账：" + acc.getMoney());
                        }else {
                            //余额足够，可以转账
                            acc.setMoney(acc.getMoney() - money);
                            account.setMoney(account.getMoney() + money);
                            System.out.println("转账成功，您的帐户还剩余：" + acc.getMoney() + "元");
                            return;
                        }
                    }
                }else {
                    System.out.println("对不起，您输入的信息有误");
                }
            }
        }
    }

    /**
     * 取钱功能
     * @param acc   当前账户对象
     * @param sc    扫描器
     */
    private static void drawMoney(Account acc, Scanner sc) {
        System.out.println("===============用户取钱操作================");
        //1、判断是否足够100元
        if (acc.getMoney() < 100){
            System.out.println("对不起，当前账户中不够100元，不能取钱");
            return;
        }

        while (true) {
            //2、提示用户输入取钱金额
            System.out.println("请您输入取款金额");
            double money = sc.nextDouble();

            //3、判断这个金额是否满足要求
            if (money > acc.getQuotaMoney()){
                System.out.println("对不起，您当前取款金额炒作每次限额，每次最多取：" + acc.getQuotaMoney());
            }else {
                //没有超过当次限额
                //4、判断是否超过了账户的总余额
                if (money > acc.getMoney()){
                    System.out.println("余额不足，您账户目前总余额是：" + acc.getMoney());
                }else {
                    //可以取钱了
                    System.out.println("恭喜您，取钱:" + money + "元成功");
                    acc.setMoney(acc.getMoney() - money);
                    showAccount(acc);
                    return;
                }
            }
        }
    }

    /**
     * 存钱
     * @param acc   当前账户对象
     * @param sc    扫描器
     */
    private static void depositMoney(Account acc, Scanner sc) {
        System.out.println("=================用户存钱操作===================");
        System.out.println("请您输入存款金额");
        double money = sc.nextDouble();

        //更新账户余额
        acc.setMoney(acc.getMoney() + money);
        System.out.println("恭喜您，存钱成功，当前账户信息如下：");
        showAccount(acc);
    }

    /**
     * 展示账户信息
     * @param acc
     */
    private static void showAccount(Account acc) {
        System.out.println("================当前账户信息================");
        System.out.println("卡号：" + acc.getCarId());
        System.out.println("户主：" + acc.getUserName());
        System.out.println("余额：" + acc.getMoney());
        System.out.println("限额：" + acc.getQuotaMoney());
    }

    /**
     * 用户开户功能地实现
     * @param accounts  接收的账户集合
     */
    private static void register(ArrayList<Account> accounts, Scanner sc) {
        System.out.println("==================系统开户功能==================");
        //1、创建一个账户对象，用于后期封装账户信息
        Account account = new Account();
        //2、录入当前这个账户的信息，注入到账户对象中去
        System.out.println("请您输入账户用户名：");
        String userName = sc.next();
        account.setUserName(userName);

        while (true) {
            System.out.println("请您输入账户密码：");
            String passWord = sc.next();
            System.out.println("请您输入确认密码");
            String okPassWord = sc.next();
            if (okPassWord.equals(passWord)){
                //密码认证通过
                account.setPassWord(okPassWord);
                break;
            }else {
                System.out.println("对不起，您输入的两次密码不一致，请重新确认~~~");
            }
        }

        System.out.println("请您输入账户当次限额：");
        double quotaMoney = sc.nextDouble();
        account.setQuotaMoney(quotaMoney);

        //为账户随机一个8位且与其他账户的卡号不重复的号码（独立功能）
        String cardId = getRandomCardId(accounts);
        account.setCarId(cardId);

        //3、把账户对象添加到账户集合中去
        accounts.add(account);
        System.out.println("恭喜您，" + userName + "先生/女士，您开户成功，您的卡号是：" + cardId + "，请妥善保管卡号");
    }

    /**
     * 为账户生成8位与其他账户卡号不同的号码
     * @return
     */
    private static String getRandomCardId(ArrayList<Account> accounts) {
        //1、先生成8位数字
        String cardId = "";
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            cardId += r.nextInt(10);
        }

        //2、判断这个8位的卡号是否与其他账户的卡号重复了
        //根据这个卡号去查询账户对象
        while (getAccountByCardId(accounts, cardId) != null){
            for (int i = 0; i < 8; i++) {
                cardId += r.nextInt(10);
            }
        }
        return cardId;
    }

    /**
     * 根据卡号查询出一个账户对象出来
     * @param accounts  全部账户的集合
     * @param cardId    卡号
     * @return  账户对象| null
     */
    private static Account getAccountByCardId(ArrayList<Account> accounts, String cardId){
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getCarId().equals(cardId)){
                return acc;
            }
        }
        return null;
    }
}

package com.itheima.d11_interface_implements;

/**
 * 实现类
 */
public class PingPongMan implements SportMan, Law{
    private String name;

    public PingPongMan(String name) {
        this.name = name;
    }
    public PingPongMan() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "必须跑步训练！");
    }

    @Override
    public void competition() {
        System.out.println(name + "要参加比赛，为国争光~！");
    }

    @Override
    public void rule() {
        System.out.println(name + "必须守法~~");
    }
}

package com.itheima.service;

import org.springframework.transaction.annotation.Transactional;

public interface AccountService {

    /**
     * 转账操作
     * @param out   传出方
     * @param in    转入方
     * @param money 金额
     */
    @Transactional
    public void transfer(String out, String in, Double money);

}

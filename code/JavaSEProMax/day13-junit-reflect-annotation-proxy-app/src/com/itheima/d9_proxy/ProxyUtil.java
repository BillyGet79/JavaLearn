package com.itheima.d9_proxy;

import java.lang.reflect.Proxy;

/**
 * public static Object newProxyInstance(ClassLoader loader,
 *                                           Class<?>[] interfaces,
 *                                           InvocationHandler h)
 * 参数一：类加载器，负责加载代理类到内存中使用
 * 参数二：获取被代理对象实现的全部接口
 * 参数三：代理的核心处理逻辑
 */

public class ProxyUtil {
    /**
     * 生成业务对象的代理对象
     * @param obj
     * @return
     */
    public static UserService getProxy(UserServiceImpl obj) {
        //返回了一个代理对象
        return (UserService) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), (proxy, method, args) -> {
                    //参数一：代理对象本身，一般不管
                    //参数二：正在被代理的方法
                    //参数三：被代理方法，应该传入的参数
                    long startTimer = System.currentTimeMillis();
                    //马上触发方法的真正执行（触发真正的业务功能）
                    Object result = method.invoke(obj, args);

                    long endTimer = System.currentTimeMillis();
                    System.out.println(method.getName() + "方法耗时：" + (endTimer - startTimer) / 1000.0 + "s");

                    //把业务功能方法执行的结果返回给调用者
                    return result;
                });
    }
}

package com.training.proxy.dynamic;

import com.training.proxy.KindWoman;
import com.training.proxy.PanJinLian;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * 测试动态代理
 *
 * @Author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class DynamicProxyTest {

    @Test
    public void dynamicProxyTest() {
        // 做一个PanJinLian的代理.
        final KindWoman woman = new PanJinLian();
        KindWoman womanProxy = (KindWoman) Proxy.newProxyInstance(woman.getClass()
                        .getClassLoader(), woman.getClass().getInterfaces(),
                //woman.方法名(参数)
                (proxy, method, args) -> method.invoke(woman, args));
        womanProxy.throwEye();

        final KindMan man = new ChenGuanXi();
        KindMan manProxy = (KindMan) Proxy.newProxyInstance(man.getClass()
                        .getClassLoader(), man.getClass().getInterfaces(),
                (proxy, method, args) -> method.invoke(man, args));
        manProxy.pz();
    }
}

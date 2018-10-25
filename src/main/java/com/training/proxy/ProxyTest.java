package com.training.proxy;

/**
 * 测试代理
 *
 * @Author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class ProxyTest {

    public static void main(String[] args) {

        KindWoman woman = new PanJinLian();

        WangPo wp = new WangPo(woman);

        //真实执行的是潘金莲，但是我们看不到，所以屏蔽了真实行为。
        wp.throwEye();
    }
}

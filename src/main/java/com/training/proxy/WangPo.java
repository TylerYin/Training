package com.training.proxy;

/**
 * 定义代理类
 *
 * @Author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class WangPo implements KindWoman {

    private KindWoman woman;

    public WangPo(KindWoman woman) {
        this.woman = woman;
    }

    public void throwEye() {
        //在这里做操作，可以控制是否调用真实行为。

        woman.throwEye();

        //在这个位置，可以在真实行为调用完成后，在做操作。
    }

    public void doSomething() {
        woman.doSomething();
    }

}

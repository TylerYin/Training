package com.training.generic;

/**
 * 定义泛型类
 *
 * @author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class GenericClass<T> {
    private T t;

    public T run1(T t){
        return t;
    }

    //当方法要操作的类型不确定并且和类上的泛型不一样的时候，这时可以把泛型定义在方法上
    //带返回值的泛型方法定义
    public <Q> void run2(Q q){
        System.out.println(q);
    }

    //不带返回值的泛型方法定义
    public <Q> Q run3(Q q){
        return q;
    }

    //static可以修饰带泛型的静态方法
    public static <Q> void run4(Q q){
        System.out.println(q);
    }

    /**
     *  如下的定义就是不正确的，也就是说：
     *  如果方法是静态，还想用泛型，那么泛型必需定义在方法上。
        public static Q void run5(Q q){
            System.out.println(q);
        }
    */
}

//定义泛型接口
interface GenericInterface<E>{
    void show(E e);
    E getResult(E e);
}

class GenericInterfaceImpl implements GenericInterface<String> {
    @Override
    public void show(String s) {
        System.out.println(s);
    }

    @Override
    public String getResult(String s) {
        return s;
    }
}

//泛型Class实现泛型接口，那么在使用时就需要在实现类中给出具体的类型，如下所示：
//
class GenericInterfaceImpl_1<T> implements GenericInterface<T> {
    @Override
    public void show(T s) {
        System.out.println(s);
    }

    @Override
    public T getResult(T s) {
        return s;
    }

    //如何使用实现泛型接口的泛型类
    private String show1(){
        return new GenericInterfaceImpl_1<String>().getResult("haha");
    }
}
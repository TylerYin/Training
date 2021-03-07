package com.lambda;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.function.*;

/**
 * @Description
 * @Author Tyler Yin
 * @Create 2021-03-07 11:53
 **/
public class LambdaTest {

    @Test
    public void testUnaryOperator() {
        UnaryOperator<String> unaryOperator = unary -> "我爱你，" + unary;
        System.out.println("unaryOperator.apply('中国') : " + unaryOperator.apply("中国"));
    }

    @Test
    public void testFunction() {
        Function<Integer, Integer> fun1 = x -> x + x;
        Function<Integer, Integer> fun2 = x -> x * x;
        System.out.println("fun1.andThen(fun2).apply(5) : " + fun1.andThen(fun2).apply(5));
        System.out.println("fun1.compose(fun2).apply(5) :  " + fun1.compose(fun2).apply(5));
    }

    @Test
    public void testBiFunction() {
        BiFunction<Integer, Integer, String> biFun1 = (x, y) -> (x + y) + "";
        System.out.println("biFun1.apply(3, 6) : " + biFun1.apply(3, 6));

        BiFunction<Integer, Integer, Integer> biFun2 = (x, y) -> x * y;
        Function<Integer, Integer> fun = x -> x * x * x;
        System.out.println("biFun2.andThen(fun).apply(2, 3) : " + biFun2.andThen(fun).apply(2, 3));
    }

    @Test
    public void testBinaryOperator() {
        BinaryOperator<Integer> bin1 = (x, y) -> x + y;
        System.out.println("bin1 : " + bin1.apply(4, 5));

        Function<Integer, Integer> fun = x -> x * x;
        BinaryOperator<Integer> bin2 = (x, y) -> x * y;
        System.out.println("bin2 : " + bin2.andThen(fun).apply(5, 6));
    }

    @Test
    public void testConsume() {
        Consumer<Integer> con1 = con -> System.out.println(con + con);
        Consumer<Integer> con2 = con -> System.out.println(con * con);
        con1.andThen(con2).accept(5);
    }

    @Test
    public void testSupplier() {
        Supplier<Integer> supplier = () -> RandomUtils.nextInt(1, 100);
        System.out.println(supplier.get());
    }

    @Test
    public void testPredicate() {
        Predicate<Integer> predicate = con -> con > 0;
        System.out.println(predicate.test(10));
    }
}
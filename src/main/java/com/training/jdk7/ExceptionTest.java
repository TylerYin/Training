package com.training.jdk7;

/**
 * @Description Exception Demo
 * @Author Tyler Yin
 * @Create 2017-11-19 20:50
 **/
public class ExceptionTest {
    public static void main(String[] args) {
        int[] arr = new int[3];

        /**
         * 一个try对应多个catch。
         * 特殊之处：多个catch内部的处理方式都相同。
         * 在Java se7中建议，不用写多个catch。
         * 多个catch异常合并。多个catch变成一个catch，在catch(异常1 | 异常2 ... 异常变量名)
         */
        try {
            int element = getElement(arr, 10);
            System.out.println("element:" + element);
        } catch (final NullPointerException | ArrayIndexOutOfBoundsException e) {
            //e = null;//error;
            System.out.println(e.toString());
        }

        //try {
        //	int element = getElement(arr, 18);
        //	System.out.println("element:" + element);
        //} catch (NullPointerException e) {
        //
        //	System.out.println(e.toString());
        //
        //} catch (ArrayIndexOutOfBoundsException e) {

        //	e = new ArrayIndexOutOfBoundsException("hahah");
        //	System.out.println(e.toString());
        //}
    }

    /**
     * 从给定的数组中获取指定的角标的元素。
     */
    public static int getElement(int[] arr, int index)
            throws NullPointerException, ArrayIndexOutOfBoundsException {
        if (arr == null) {
            throw new NullPointerException("数组不存在");
        }
        if (index < 0 || index >= arr.length) {
            throw new ArrayIndexOutOfBoundsException("数组角标不存在 ");
        }
        return arr[index];
    }
}

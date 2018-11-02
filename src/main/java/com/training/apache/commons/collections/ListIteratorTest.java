package com.training.apache.commons.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @Description List Iterator测试
 *
 * 获取集合中的元素。
 * 如果集合中有元素等于 itcast2.那么就插入一个新的元素。
 * @Author Tyler Yin
 * @Create 2017-11-18 23:43
 **/
public class ListIteratorTest {
    @Test
    public void ListIteratorTest() {
        final List<String> list = new ArrayList();
        list.add("itcast1");
        list.add("itcast2");
        list.add("itcast3");
        list.add("itcast4");

        /**
         *  引发了java.util.ConcurrentModificationException
         *  在迭代过程中，使用了集合的方法对元素进行操作。导致迭代器并不知道集合中的变化，容易引发数据的不确定性。
         */
        final Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            final String item = it.next();
            if ("itcast2".equals(item)) {
                //下面这行会出异常
                //list.add("java");
            }
        }
        System.out.println(list);

        /**
         *  解决:在迭代时，不要使用集合的方法操作元素。
         *  那么想要在迭代时对元素操作咋办？可以使用迭代器的方法操作。
         *  Iterator有一个子接口ListIterator可以完成该问题的解决，该列表迭代器只有List接口有。而且这个迭代器可以完成在迭代过程中的增删改查动作。
         */
        final ListIterator<String> listIt = list.listIterator();
        while (listIt.hasNext()) {
            final String item = listIt.next();
            if ("itcast2".equals(item)) {
                listIt.add("java");
            }
        }
        System.out.println(list);
    }
}

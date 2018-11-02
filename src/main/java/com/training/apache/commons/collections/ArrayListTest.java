package com.training.apache.commons.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description 去除ArrayList中的重复元素
 * @Author Tyler Yin
 * @Create 2017-11-19 7:42
 **/
public class ArrayListTest {

    @Test
    public void testRemoveDuplicateObject(){
        List list = new ArrayList();
        list.add("abc1");
        list.add("abc2");
        list.add("abc1");
        list.add("abc2");
        list.add("abc1");
        list.add("abc2");

        //去除重复元素
        getSingleElement(list);
        System.out.println(list);

        list.clear();
        list.add(new Student("lisi1", 21));
        list.add(new Student("lisi2", 22));
        list.add(new Student("lisi1", 21));
        list.add(new Student("lisi2", 22));
        list.add(new Student("lisi1", 21));

        /**
         * 去除重复元素
         * 对于复杂对象元素的去重，要求对象必须要重写equals方法
         *
         */
        getSingleElement(list);
        System.out.println(list);
    }

    public static void getSingleElement(List list) {
        List temp = new ArrayList();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (!temp.contains(obj)) {
                temp.add(obj);
            }
        }
        list.clear();
        list.addAll(temp);
    }
}

/**
 * |--Vector：可以增长的数组结构。同步的。效率非常低。已被ArrayList替代。
 * |--ArrayList：是数组结构，长度是可变的（原理是创建新数组+复制数组），查询速度很快，增删较慢，不同步的。
 * |--LinkedList：是链表结构，不同步的，增删速度很快，查询速度较慢。
 * 可用于实现堆栈，队列。
 * 堆栈：先进后出  First in Last Out  FILO 手枪弹夹。
 * 队列：先进先出  First in First Out FIFO 排队买票。
 * List可以存储重复元素的，如果需求中要求容器中的元素必须保证唯一性。
 *
 * |--Set:不包含重复元素的集合，不保证顺序。而且方法和Collection一致。Set集合取出元素的方式只有一种：迭代器。
 * |--HashSet:哈希表结构，不同步，保证元素唯一性的方式依赖于：hashCode(),equals()方法。查询速度快。
 * |--TreeSet:可以对Set集合中的元素进行排序。使用的是二叉树结构。如何保证元素唯一性的呢？
 * 使用的对象比较方法的结果是否为0，是0，视为相同元素不存。
 * 元素的排序比较有两种方式：
 * 1，元素自身具备自然排序，其实就是实现了Comparable接口重写了compareTo方法。
 * 如果元素自身不具备自然排序，或者具备的自然排序不是所需要的，这时只能用第二种方式。
 * 2，比较器排序，其实就是在创建TreeSet集合时，在构造函数中指定具体的比较方式。
 * 需要定义一个类实现Comparator接口，重写compare方法。
 * 到此为止：再往集合中存储对象时，通常该对象都需要覆盖hashCode，equals，
 * 同时实现Comparale接口，建立对象的自然排序。通常还有一个方法也会复写toString();
 *
 *
 * 集合分体系。List  Set
 * 子类对象的后缀名是所属体系，前缀名是数据结构名称。
 * List：新出的子类都是以List结尾的，通常都是非同步的。
 * |--ArrayList ：看到array，就知道数组，查询速度快。
 * |--LinkedList：看到link，就知道链表，增删速度快。
 *
 * Set:
 * |--HashSet:看到hash，就知道哈希表，查询速度更快，并想到元素唯一，通过hashCode(),equals方法保证唯一性。
 * |--TreeSet:看到tree，就知道二叉树，可以排序，排序想到Comparable-compareTo Comparator--compare方法。
 */
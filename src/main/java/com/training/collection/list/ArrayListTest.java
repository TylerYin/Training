package com.training.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description 去除ArrayList中的重复元素
 * @Author Tyler Yin
 * @Create 2017-11-19 7:42
 **/
public class ArrayListTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        List list = new ArrayList();
        //list.add("abc1");
        //list.add("abc2");
        //list.add("abc1");
        //list.add("abc2");
        //list.add("abc1");
        //list.add("abc2");

        list.add(new Student("lisi1", 21));
        list.add(new Student("lisi2", 22));
        list.add(new Student("lisi1", 21));
        list.add(new Student("lisi2", 22));
        list.add(new Student("lisi1", 21));

        getSingleElement(list);//去除重复元素。
        System.out.println(list);
    }

    /**
     * 案例：去除List集合中的重复元素。
     *
     * 思路：
     * 1，先创建一个临时容器。用于存储唯一性的元素。
     * 2，遍历原容器，将遍历到的元素到临时容器中去判断，是否存在。
     * 3，如果存在，不存储到临时容器，如果不存在，存储到临时容器中。
     * 4，遍历结束后，临时容器中存储的就是唯一性的元素。
     * 5，如果需要将这些唯一性的元素保留到原容器中，只要将原容器清空，将临时容器中的元素添加到原容器中即可。
     */
    public static void getSingleElement(List list) {
        //1,创建一个临时容器。
        List temp = new ArrayList();

        //2,遍历原容器。
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Object obj = it.next();

            //对遍历到的每一个元素都到临时容器中去判断是否包含。
            if (!temp.contains(obj)) {//如果不存在，
                temp.add(obj);//添加到临时容器。
            }
        }
        //唯一性的元素已经被记录到临时容器中。
        //清空原容器中的元素。
        list.clear();

        //把临时容器中的元素添加到原容器中。
        list.addAll(temp);
    }
}
/**
Collection:
    |--List:有序的，带索引的，通过索引就可以精确的操作集合中的元素，元素是可以重复的。
    List提供了增删改查动作
    增加add(element) add(index,element)  ;
    删除remove(element) remove(index);
    修改set(index,element);
    查询get(index);
    |--Vector：可以增长的数组结构。同步的。效率非常低。已被ArrayList替代。
    |--ArrayList：是数组结构，长度是可变的（原理是创建新数组+复制数组），查询速度很快，增删较慢，不同步的。
    |--LinkedList：是链表结构，不同步的，增删速度很快，查询速度较慢。
    可用于实现堆栈，队列。
    堆栈：先进后出  First in Last Out  FILO 手枪弹夹。
    队列：先进先出  First in First Out FIFO 排队买票。
    List可以存储重复元素的，如果需求中要求容器中的元素必须保证唯一性。

    |--Set:不包含重复元素的集合，不保证顺序。而且方法和Collection一致。Set集合取出元素的方式只有一种：迭代器。
    |--HashSet:哈希表结构，不同步，保证元素唯一性的方式依赖于：hashCode(),equals()方法。查询速度快。
    |--TreeSet:可以对Set集合中的元素进行排序。使用的是二叉树结构。如何保证元素唯一性的呢？
    使用的对象比较方法的结果是否为0，是0，视为相同元素不存。
    元素的排序比较有两种方式：
    1，元素自身具备自然排序，其实就是实现了Comparable接口重写了compareTo方法。
    如果元素自身不具备自然排序，或者具备的自然排序不是所需要的，这时只能用第二种方式。
    2，比较器排序，其实就是在创建TreeSet集合时，在构造函数中指定具体的比较方式。
    需要定义一个类实现Comparator接口，重写compare方法。
    到此为止：再往集合中存储对象时，通常该对象都需要覆盖hashCode，equals，
    同时实现Comparale接口，建立对象的自然排序。通常还有一个方法也会复写toString();

    看集合对象的小技巧：★★★★★★
    集合分体系。List  Set
    子类对象的后缀名是所属体系，前缀名是数据结构名称。
    List：新出的子类都是以List结尾的，通常都是非同步的。
    |--ArrayList ：看到array，就知道数组，查询速度快。
    |--LinkedList：看到link，就知道链表，增删速度快。

    Set:
    |--HashSet:看到hash，就知道哈希表，查询速度更快，并想到元素唯一，通过hashCode(),equals方法保证唯一性。
    |--TreeSet:看到tree，就知道二叉树，可以排序，排序想到Comparable-compareTo Comparator--compare方法。
*/
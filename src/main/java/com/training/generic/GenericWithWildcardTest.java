package com.training.generic;


import com.training.generic.domain.Person;
import com.training.generic.domain.Student;
import com.training.generic.domain.Worker;

import java.util.*;

/**
 * @Description Define generic with wildcard
 * @Author Tyler Yin
 * @Create 2017-11-12 14:47
 **/
public class GenericWithWildcardTest {
    public static void main(String args[]) {
        final List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Lisi", 22));
        studentList.add(new Student("Wangwu", 21));
        studentList.add(new Student("Zhangsan", 23));

        printList(studentList);
        printListWithGenericLimit(studentList);

        final Set<Worker> workerList = new HashSet<>();
        workerList.add(new Worker("Lisi", 22));
        workerList.add(new Worker("Wangwu", 21));
        workerList.add(new Worker("Zhangsan", 23));
        printList(workerList);
        printListWithGenericLimit(workerList);

        final List<String> strList = new ArrayList<>();
        strList.add("haha");
        strList.add("xixi");
        strList.add("hehe");

        printList(strList);
        //因为方法对泛型做了限制，所以下面的方法不能用来打印字符串集合
        //printListWithGenericLimit(strList);
    }

    /**
     * 打印集合中的元素。
     * <p>
     * 当使用泛型类或者接口时，传递的具体的类型不确定，可以通过通配符(?)表示。
     * 其实(?)就相当于（？extends Object）
     *
     * @param studentList
     */
    private static void printList(final Collection<?> studentList) {
        System.out.println("以泛型的方式打印");
        studentList.stream().forEach(System.out::println);
        System.out.println();
    }

    /**
     * 打印集合中的元素。
     * 当使用泛型类或者接口时，传递的具体的类型不确定，可以通过通配符(?)表示。
     * 如果想要对被打印的集合中的元素类型进行限制，只在指定的一些类型，进行打印。
     * 使用泛型的限定。
     * <p>
     * 只需要打印学生和工人的集合。找到学生和工人的共性类型Person。
     * ? extends Person : 接收Person类型或者Person的子类型。
     * <p>
     * 总结：
     * ? super E:接收E类型或者E的父类型。下限。
     * ? extends E:接收E类型或者E的子类型。上限。
     */
    private static void printListWithGenericLimit(final Collection<? extends Person> studentList) {
        System.out.println("以泛型带限定符的方式打印");
        studentList.stream().forEach(System.out::println);
        System.out.println();
    }
}

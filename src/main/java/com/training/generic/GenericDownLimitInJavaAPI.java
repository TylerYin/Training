package com.training.generic;

import com.training.generic.domain.Person;
import com.training.generic.domain.Student;
import com.training.generic.domain.Worker;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Description Generic limit in java API
 * @Author Tyler Yin
 * @Create 2017-11-12 14:50
 **/
public class GenericDownLimitInJavaAPI {

    @Test
    public void sortTreeSet() {
        //创建一个集合存储的是学生对象, 想要按照姓名排序
        final TreeSet<Student> ts = new TreeSet<>(new ComparatorByStudentName());
        ts.add(new Student("abc", 26));
        ts.add(new Student("aaa", 29));
        ts.add(new Student("lisi", 20));
        ts.stream().forEach(System.out::println);

        //让工人按照姓名排序
        final TreeSet<Worker> ts2 = new TreeSet<>(new ComparatorByWorkerName());
        ts2.add(new Worker("abc", 26));
        ts2.add(new Worker("aaa", 29));
        ts2.add(new Worker("lisi", 20));
        ts2.stream().forEach(System.out::println);
    }

    @Test
    public void sortTreeSet1() {
        /**
         *  泛型的限定在api中的使用。下限的体现。
         *  TreeSet(Comparator<? super E> comparator)
         */

        //创建一个集合存储的是学生对象, 想要按照姓名排序
        final TreeSet<Student> ts = new TreeSet<>(new ComparatorPerson());
        ts.add(new Student("abc", 26));
        ts.add(new Student("aaa", 29));
        ts.add(new Student("lisi", 20));
        ts.stream().forEach(System.out::println);

        //让工人按照姓名排序
        final TreeSet<Worker> ts2 = new TreeSet<>(new ComparatorPerson());
        ts2.add(new Worker("abc", 26));
        ts2.add(new Worker("aaa", 29));
        ts2.add(new Worker("lisi", 20));
        ts2.stream().forEach(System.out::println);
    }

}

class ComparatorByStudentName implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (null != o1 && null != o2) {
            return o2.getName().compareTo(o1.getName());
        } else if (o1 == null) {
            return 1;
        }
        return -1;
    }
}

class ComparatorByWorkerName implements Comparator<Worker> {
    @Override
    public int compare(Worker o1, Worker o2) {
        if (null != o1 && null != o2) {
            return o2.getName().compareTo(o1.getName());
        } else if (o2 == null) {
            return 1;
        }
        return -1;
    }
}

/**
 * 以上两个比较器，都是通过姓名排序，就是类型不同，一个是student，一个是worker
 * 既然使用的都是Person的内容，为什么不定义一个Person的比较器。
 */
class ComparatorPerson implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if (null != o1 && null != o2) {
            return o2.getName().compareTo(o1.getName());
        } else if (o1 == null) {
            return 1;
        }
        return -1;
    }
}

/**
 * class TreeSet<E>{
 *      TreeSet(Comparator<? super E>  c){
 *
 *      }
 * }
 */
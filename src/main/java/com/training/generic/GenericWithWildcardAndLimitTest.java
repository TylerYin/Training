package com.training.generic;

import com.training.generic.domain.Person;
import com.training.generic.domain.Student;
import com.training.generic.domain.Worker;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * @Description Generic with wildcard and limit
 * @Author Tyler Yin
 * @Create 2017-11-12 16:47
 **/
public class GenericWithWildcardAndLimitTest {
    /**
     * 写一个工具类，用来查找任意集合中的最大元素
     */
    @Test
    public void FindMaxValueInCollection(){
        /**
		 * 案例：获取集合中元素的最大值。
		 * 思路：
         * 1. 定义变量记录每次比较后较大的值，初始化元素中任意一个
         * 2. 遍历容器
		 * 3. 在遍历中和变量中记录的元素进行比较。并将较大的值记录到变量中
         * 4. 遍历结束，变量中记录的就是最大值
		 */

        final Collection<Student> c1 = new ArrayList<>();
        c1.add(new Student("xiaoming1", 30));
        c1.add(new Student("xiaoming2", 36));
        c1.add(new Student("xiaoming3", 22));

        //有了泛型的类型限定，下面的代码就会在编译时出错，防止了在运行时出现类型转换异常的错误
        //c1.add(new Dog());

        System.out.println(getMax(c1));

        final Collection<Worker> c2 = new ArrayList<>();
        c2.add(new Worker("xiaoming11", 23));
        c2.add(new Worker("xiaoming22", 36));
        c2.add(new Worker("xiaoming33", 22));
        System.out.println(getMax(c2));

        final Collection<Person> c3 = new ArrayList<>();
        c3.add(new Person("xiaoming111", 12));
        c3.add(new Person("xiaoming222", 36));
        c3.add(new Person("xiaoming333", 13));
        System.out.println(getMax(c3));

        Collection<String> c4 = new ArrayList<>();
        c4.add("abcd");
        c4.add("java");
        c4.add("z");
        c4.add("nba");
        System.out.println("String s = " + getMax(c4));

        Collection<Dog> c5 = new ArrayList<>();
        //已经做了限定，说明传递的集合中的元素类型必须是Comparable的子类。否则编译失败。
        //getMax(c5);
    }

    //升级版
    private static <T extends Comparable<? super T>> T getMax(Collection<? extends T> collection, Comparator<? super T> comparator) {
        if (null == comparator) {
            return getMax(collection);
        }

        final Iterator<? extends T> it = collection.iterator();
        T max = it.next();

        while (it.hasNext()) {
            T temp = it.next();
            if (comparator.compare(temp, max) > 0) {
                max = temp;
            }
        }

        return max;
    }

    // 要操作的元素的类型确定不？不确定。使用泛型限定。getMax方法接收的集合中的元素无论时什么类型，必须具备自然排序，必须是Comparable的子类。
    private static <T extends Comparable<? super T>> T getMax(Collection<? extends T> collection) {
        final Iterator<? extends T> it = collection.iterator();
        T max = it.next();

        while (it.hasNext()) {
            T temp = it.next();
            if (temp.compareTo(max) > 0) {
                max = temp;
            }
        }

        return max;
    }

    /**
	* 不加泛型时，无法明确集合中的元素时什么类型，为了便于操作用Object
    public static Object getMax(Collection c1) {
        // 1,定义变量，记录集合中任意一个元素，Collection集合取出元素的方式只有迭代器。
        Iterator it = c1.iterator();
        Object max = it.next();

        // 2,遍历容器。
        while (it.hasNext()) {
            Object o = it.next();
            Comparable temp = (Comparable)o;
            if (temp.compareTo(max) > 0) {
                max = temp;
            }
        }
        return max;
    }

	这个功能虽然实现，但是有局限性，因为这个功能只能对存储了Student对象的集合进行最大值的获取
    public static Student getMax(Collection<Student> c1) {
        //1,定义变量，记录集合中任意一个元素，Collection集合取出元素的方式只有迭代器。
        Iterator<Student> it = c1.iterator();
        Student max = it.next();

        //2,遍历容器
        while(it.hasNext()){
            Student temp = it.next();
            if(temp.compareTo(max) > 0){
                max = temp;
            }
        }
        return max;
    }
	*/
}

class Dog{

}

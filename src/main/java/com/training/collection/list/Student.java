package com.training.collection.list;

public class Student implements Comparable {

    private String name;
    private int age;

    public Student() {
        super();
    }

    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    /**
     * 重写hashCode方法，建立Student对象的hash值算法内容。 通过学生对象特有数据姓名和年龄值来算出hash值。
     */
    @Override
    public int hashCode() {
        final int NUMBER = 24;
        return name.hashCode() + age * NUMBER;// 乘以24(随意)为了尽量保证hash值不同
        // return 1;
    }

    /**
     * 重写了equals方法，建立Student对象判断相同的依据。
     */
    @Override
    public boolean equals(Object obj) {
        System.out.println("equals");
        if (this == obj)
            return true;

        if (!(obj instanceof Student)) {
            throw new ClassCastException();
        }

        Student stu = (Student) obj;
        return this.name.equals(stu.name) && this.age == stu.age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name = " + name + ", age = " + age + "]";
    }

    /**
     * 重写compareTo方法，建立学生的自然排序(对象的默认排序方式)。
     * 按照学生年龄排序。
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Student)) {
            throw new ClassCastException();
        }
        Student stu = (Student) o;

        //if(this.age>stu.age)
        //	return 1;
        //if(this.age<stu.age)
        //	return -1;

        /**
         * 注意：在比较时，必须明确主次。主要条件相同，继续比较次要条件。
         */
        int temp = this.age - stu.age;
        return temp == 0 ? this.name.compareTo(stu.name) : temp;
    }
}


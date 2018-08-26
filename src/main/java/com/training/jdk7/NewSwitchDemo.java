package com.training.jdk7;

/**
 * @Description New Switch Demo
 * @Author Tyler Yin
 * @Create 2017-11-19 21:17
 **/
public class NewSwitchDemo {
    public static void main(String[] args) {
        oldSwitchDemo();
        newSwitchDemo();
    }

    public static void newSwitchDemo() {
        String sex = "男";
        System.out.println(sex.hashCode());

        if (sex.equals("男")) {
            System.out.println("先生 ，你好");
        } else if (sex.equals("女")) {
            System.out.println("女士，你好");
        } else {
            System.out.println("你真好吗？");
        }

        switch (sex) {
            case "男":
                System.out.println("先生 ，你好");
                break;
            case "女":
                System.out.println("女士，你好");
                break;
            default:
                System.out.println("你真好吗？");
        }
    }

    private static void oldSwitchDemo() {
        int week = 2;
        if (week == 1) {
            System.out.println(week + "对应的是星期一");
        } else if (week == 2) {
            System.out.println(week + "对应的是星期二");
        } else {
            System.out.println(week + "，no week");
        }

        switch (week) {
            case 1:
                System.out.println(week + "对应的是星期一");
                System.out.println(week + "对应的是星期一");
                System.out.println(week + "对应的是星期一");
                System.out.println(week + "对应的是星期一");
                System.out.println(week + "对应的是星期一");
                break;
            case 2:
                System.out.println(week + "对应的是星期二");
                break;
            default:
                System.out.println(week + "，no week");
        }
    }
}

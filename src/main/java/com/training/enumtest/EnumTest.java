package com.training.enumtest;

/**
 * 定义枚举类型
 *
 * @Author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class EnumTest {

    public static void main(String args[]) {
        Sex.MEAL.run();
        Sex.FEMALE.run();
    }

    public enum Sex {
        MEAL("男性") {
            @Override
            public void run() {
                System.out.println(this.getDescription());
            }
        },

        FEMALE("女性") {
            @Override
            public void run() {
                System.out.println(this.getDescription());
            }
        };

        private String description;

        /**
         * 默认都是私有的, 并且必须放置在变量声明的下面
         *
         * @param description
         */
        Sex(String description) {
            this.description = description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public abstract void run();
    }
}

package jdk18;

import org.junit.Test;
//::使用的介绍
public class Fengefu {
    //实体类User和它的构造方法
    public class User {
        private String name;

        private String sex;

        public User(String name, String sex) {
            super();
            this.name = name;
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }
    //User工厂
    public interface UserFactory {
        User get(String name, String sex);
    }

    @Test
    public void test() {
        //测试类
        UserFactory uf = User::new;
        User u = uf.get("ww", "man");
        System.out.println(u);
    }
//================示例2=========================================================================
    @FunctionalInterface
    public interface TestConverT<T, F> {
        F convert(T t);
    }

    @Test
    public void test2(){
        TestConverT<String, Integer> t = Integer::valueOf;
        Integer i = t.convert("111");
        System.out.println(i);
    }
}

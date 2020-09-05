package jdk18;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 	方法引用和构造器调用
 * 		静态方法引用　　　　　　　：　 　ClassName :: staticMethodName
 * 		构造器引用　　　　　　　　：　 　ClassName :: new
 * 		类的任意对象的实例方法引用：　 　ClassName :: instanceMethodName
 * 		特定对象的实例方法引用　　：　 　object :: instanceMethodName
 */
public class MethodReferenceTest {
    @Test
    public void test() {
        String[] stringsArray = new String[]{"aa", "bb"};
        Arrays.sort(stringsArray, String::compareToIgnoreCase);

        //lambda表达式使用：
        Arrays.asList(new String[] {"a", "c", "b"}).stream().forEach(s -> MethodReferenceTest.println(s));
        //静态方法引用：
        Arrays.asList(new String[] {"a", "c", "b"}).stream().forEach(MethodReferenceTest::println);

        //--------------------------------------------------------------------------------
        Supplier<List<String>> supplier = ArrayList<String>::new;
        List<String> list = supplier.get();

        Supplier<List<String>> supplier1= () -> new  ArrayList<String>();

        //--------------------------------------------------------------------------
        Arrays.sort(stringsArray, String::compareToIgnoreCase);



    }

    public static void println(String s)
    {
        System.out.println(s);
    }

}

class Student
{

    private String name;

    private Integer score;

    public void setNameAndScore(String name, Integer score)
    {
        this.name = name;
        this.score = score;
        System.out.println("Student "+  name +"'s score is " + score);
    }

    public static void main(String[] args)
    {
        /*lambda表达式的用法：
        TestInterface testInterface = (student, name, score) -> student.setNameAndScore(name, score);*/
        //类的任意对象的实例方法引用的用法:
        TestInterface testInterface = Student::setNameAndScore;
        testInterface.set(new Student(), "DoubleBin", 100);
    }

    @FunctionalInterface
    interface TestInterface
    {
        // 注意：入参比Student类的setNameAndScore方法多1个Student对象，除第一个外其它入参类型一致
        public void set(Student d, String name, Integer score);
    }
}

class Test2
{
    public static void main(String[] args)
    {
        Test2 test2 = new Test2();
        // lambda表达式使用：
        Arrays.asList(new String[] {"a", "c", "b"}).stream().forEach(s -> test2.println(s));
        // 特定对象的实例方法引用：
        Arrays.asList(new String[] {"a", "c", "b"}).stream().forEach(test2::println);
    }

    public void println(String s)
    {
        System.out.println(s);
    }
}
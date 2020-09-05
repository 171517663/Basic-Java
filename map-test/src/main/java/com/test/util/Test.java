package com.test.util;

public class Test {
    public static void main(String[] args) {
        String str = "nihao";
        String str2 = "nihao2";
        char ch = 'q';
        char ch2 = 'q';
        System.out.println(ch2 == ch);

        int int3 = 3;
        Integer integer3 = new Integer(3);
        Integer integer3_2 = new Integer(3);
        Integer integer3_3 = 3;
        Integer integer3_4 = 3;
        Integer integer321 = 321;
        Integer integer321_2 = 321;
        int int321 = 321;
        int int321_2 = 321;
        Long long3 = 3l;
        System.out.println(integer3==integer3_2);          //false #1
        System.out.println(integer3_3==integer3_4);        //true  #2
        System.out.println(integer3==int3);               //true   #3
        System.out.println(integer3_3.equals(integer3_4)); //true   #4

        System.out.println(integer321==integer321_2);      //false #5
        System.out.println(int321==int321_2);              //true  #6

        System.out.println(long3==int3);                  //true  #7
        System.out.println(long3.equals(int3));           //false #8


        Integer integer3_31 = Integer.valueOf(3);

// 原来-128<=int<=127的值java栈中有缓存都指向同一个地址，不在这个范围内的int值都是new一个新的Integer,所以#2为true,#5为false
//#3 基础类型和包装类运算时，包装类会拆包，所以就是两个值运算
//#7 btye,short,int,long运算时，都会向上转型到同一类型运算
//#8 equals()比较是先比较类型在比较值，类型不一样所以为false
    }
}

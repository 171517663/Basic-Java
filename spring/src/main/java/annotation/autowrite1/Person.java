package annotation.autowrite1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

//@Autowired先是byType,匹配不到就用byName,全部匹配不到就用@Qualifier("dog222")
public class Person {
    @Autowired
    Cat cat;

    @Autowired
    @Qualifier("dog222")
    //@Resource("dog222")可以达到同样效果javax.annotation.Resource
    Dog dog;

    public Person(Cat cat, Dog dog) {
        this.cat = cat;
        this.dog = dog;
    }

    public Person() {
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "cat=" + cat +
                ", dog=" + dog +
                '}';
    }
}

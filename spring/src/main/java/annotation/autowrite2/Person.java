package annotation.autowrite2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Scope("prototype")
//@Service
//@Repository
//@Controller
public class Person {
    @Autowired
    Cat cat;

    @Autowired
    @Qualifier("dog")
    Dog dog;

    @Value("xiaohuang")
    String name;

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
                ", name='" + name + '\'' +
                '}';
    }
}

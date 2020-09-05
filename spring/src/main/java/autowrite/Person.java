package autowrite;

public class Person {
    Cat cat;
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

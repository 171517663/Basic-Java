package clazz;

public class CastTeat {

    public static void main(String[] args) {
        Animal a = new Dog("Hachi","yellow");
        System.out.println(a.getName());
        //System.out.println(a2.getFurColor()); error
        System.out.println(a instanceof Dog);

        Dog d = Dog.class.cast(a);
        System.out.println(d.getFurColor());
    }
}
class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

class Dog extends Animal{
    private String furColor;
    public Dog(String name,String furColor) {
        super(name);
        this.furColor = furColor;
    }


    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }


}


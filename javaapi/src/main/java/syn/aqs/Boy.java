package syn.aqs;

public class Boy {
    int age;
    String name;

    public Boy(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Boy boy = new Boy("xixi");
        System.out.println(boy);
    }

    @Override
    public String toString() {
        return "Boy{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

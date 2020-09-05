package object;
//clone()是浅拷贝
public class User implements Cloneable {
    String name;
    StringBuilder id;
    Pet pet;

    public Pet getPet() {
        return pet;
    }

    public User(String name, StringBuilder id, Pet pet) {
        this.name = name;
        this.id = id;
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", pet=" + pet.getType() +
                '}';
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User(String name, StringBuilder id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StringBuilder getId() {
        return id;
    }

    public void setId(StringBuilder id) {
        this.id = id;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        User user1 = (User)super.clone();
        user1.setPet((Pet)pet.clone());
        return user1;
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        User user = new User("xiaohuang", new StringBuilder("12"), new Pet("dog"));
        User user1 = (User) user.clone();
        System.out.println(user==user1);
        user1.getId().append("xx");
        user1.getPet().setType("bird");
        System.out.println(user);
        System.out.println(user1);
    }
}

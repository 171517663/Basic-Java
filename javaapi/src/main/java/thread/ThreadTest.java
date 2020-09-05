package thread;

public class ThreadTest {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        for(int i = 0; i<100*100*100; i++){
            System.out.print("");
        }
        System.out.println(Thread.interrupted());

        System.out.println(Thread.interrupted());

        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
    }
}

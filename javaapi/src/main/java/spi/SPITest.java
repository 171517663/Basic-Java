package spi;

import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPITest{
    public static void main(String[] args) {
        spiTest();
    }

    static void spiTest() {
        Iterator<InterfaceA> providers = Service.providers(InterfaceA.class);
        ServiceLoader<InterfaceA> load = ServiceLoader.load(InterfaceA.class);

        while(providers.hasNext()) {
            InterfaceA ser = providers.next();
            ser.print();
        }

        System.out.println("--------------------------------");
        Iterator<InterfaceA> iterator = load.iterator();
        while(iterator.hasNext()) {
            InterfaceA ser = iterator.next();
            ser.print();
        }
    }

    void withoutSPI() {

    }
}

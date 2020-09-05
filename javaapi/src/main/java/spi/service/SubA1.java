package spi.service;

import spi.InterfaceA;

public class SubA1  implements InterfaceA {

    @Override
    public void print() {
        System.out.println("SubA1===========");
    }
}

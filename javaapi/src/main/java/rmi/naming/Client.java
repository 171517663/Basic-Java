package rmi.naming;

import java.rmi.Naming;

public class Client {

    public static void main(String [] args)throws Exception

    {

        IServer iServer = (IServer) Naming.lookup("//127.0.0.1:1009/iServer");

        System.out.println(iServer.add(5, 6));

    }

}

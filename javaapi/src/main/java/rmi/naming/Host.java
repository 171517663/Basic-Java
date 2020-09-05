package rmi.naming;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Host {

    public static void main(String[] args)throws Exception

    {

        LocateRegistry.createRegistry(1009);//绑定端口

        IServer iServer = new ServerImpl();

        Naming.rebind("//127.0.0.1:1009/iServer" , iServer);

    }

}

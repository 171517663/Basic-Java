package rmi.naming;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements IServer {

    public ServerImpl() throws RemoteException {

    }

    public int add(int a, int b) {

        return a+b;

    }

}

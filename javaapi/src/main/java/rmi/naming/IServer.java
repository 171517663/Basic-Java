package rmi.naming;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {

    int add(int a,int b)throws RemoteException;

}

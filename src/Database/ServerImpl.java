package Database;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerImpl implements Server
{
  private DatabaseConnection DBConn;

  public ServerImpl()
  {
    this.DBConn = new DatabaseConnection();
  }

  public void startServer()
  {
    try
    {
      UnicastRemoteObject.exportObject(this,0);
      Registry registry = LocateRegistry.createRegistry(1099);
      registry.bind("Server", this);
      DBConn.startDB();
      System.out.println("Server started.");
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    catch (AlreadyBoundException e)
    {
      e.printStackTrace();
    }


  }

}

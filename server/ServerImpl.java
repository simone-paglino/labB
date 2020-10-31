package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RMISecurityManager;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

import server.base.interfaces.Server;
import server.base.classes.AccessController;

import server.threads.playerThread.PlayerThread;
import server.threads.playerThread.interfaces.PlayerCredentials;


public class ServerImpl extends UnicastRemoteObject implements Server {
  public ServerImpl () throws RemoteException {
    super();
  }

  public void createPlayerAccount (
    String name,
    String surname,
    String username,
    String email,
    String password,
    PlayerCredentials player
  ) throws RemoteException {
    PlayerThread playerThread = new PlayerThread(name, surname, username, email, password, player, "create");
    Thread thread = new Thread(playerThread);
    thread.start();
  }

  public void confirmPlayerAccount (String confirmationCode, PlayerCredentials player) throws RemoteException {
    PlayerThread playerThread = new PlayerThread(confirmationCode, player, "confirm");
    Thread thread = new Thread(playerThread);
    thread.start();
  }

  public void loginPlayerAccount (
    String email,
    String password,
    PlayerCredentials player
  ) throws RemoteException {
    PlayerThread playerThread = new PlayerThread(email, password, player, "login");
    Thread thread = new Thread(playerThread);
    thread.start();
  }

  public void resetPlayerPassword (String email, PlayerCredentials player) throws RemoteException {
    PlayerThread playerThread = new PlayerThread(player, email, "reset");
    Thread thread = new Thread(playerThread);
    thread.start();
  }

  public void changePlayerData (
    String email,
    String name,
    String surname,
    String username,
    String password,
    String oldPassword,
    PlayerCredentials player
  ) throws RemoteException {
    PlayerThread playerThread = new PlayerThread(
      email,
      name,
      surname,
      username,
      password,
      oldPassword,
      player,
      "change"
    );
    Thread thread = new Thread(playerThread);
    thread.start();
  }

  public static void main (String[] args) throws RemoteException {
    /*if (System.getSecurityManager() == null) {
      System.setSecurityManager(new RMISecurityManager());
    }*/

    AccessController accessController = new AccessController();
    accessController.handleAccessProcess();

    try {
      ServerImpl server = new ServerImpl();
      Registry registry = LocateRegistry.createRegistry(1099);
      registry.rebind("server", server);
      System.out.println("Server is listening...");
    } catch (Exception e) {
      System.err.println("Error while starting the server");
      System.exit(1);
    }
  }
}

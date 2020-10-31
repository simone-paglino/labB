package server.base.interfaces;


import server.threads.playerThread.interfaces.PlayerCredentials;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Server extends Remote {
    void createPlayerAccount (
        String name,
        String surname,
        String username,
        String email,
        String password,
        PlayerCredentials player
    ) throws RemoteException;

    void confirmPlayerAccount (String confirmationCode, PlayerCredentials player) throws RemoteException;

    void loginPlayerAccount (
        String email,
        String password,
        PlayerCredentials player
    ) throws RemoteException;

    void resetPlayerPassword (String email, PlayerCredentials player) throws RemoteException;

    void changePlayerData (
        String email,
        String name,
        String surname,
        String username,
        String password,
        String oldPassword,
        PlayerCredentials player
    ) throws RemoteException;
}

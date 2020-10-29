package server.threads.playerThread.abstracts;


import java.rmi.RemoteException;
import java.sql.SQLException;
import javax.mail.MessagingException;

import server.threads.playerThread.interfaces.PlayerCredentials;


public abstract class Player  {
    protected abstract void createPlayerAccount (
        String name,
        String surname,
        String username,
        String email,
        String password,
        PlayerCredentials player
    ) throws InterruptedException, RemoteException, SQLException, MessagingException;
}

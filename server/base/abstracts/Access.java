package server.base.abstracts;


public abstract class Access {
  protected abstract String[] askForCredentials();

  protected abstract String[] askAdministratorCredentials();

  protected abstract boolean checkAdminProfile();

  protected abstract void createAdminProfile();
}

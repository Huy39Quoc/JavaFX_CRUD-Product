package main.dojavafx.Service;

import main.dojavafx.Entity.SonyAccounts;

public interface AccountsInterface {
    boolean Login(String phone, String password);
    SonyAccounts LoginAcc(String phone, String password);
}

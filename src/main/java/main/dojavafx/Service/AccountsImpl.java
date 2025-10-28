package main.dojavafx.Service;

import main.dojavafx.Entity.SonyAccounts;
import main.dojavafx.Repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsImpl implements AccountsInterface {
    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }
    public boolean Login(String phone, String password){
        return accountsRepository.findByPhoneAndPassword(phone, password)!=null;
    }

    @Override
    public SonyAccounts LoginAcc(String phone, String password) {
        return accountsRepository.findByPhoneAndPassword(phone, password);
    }
}

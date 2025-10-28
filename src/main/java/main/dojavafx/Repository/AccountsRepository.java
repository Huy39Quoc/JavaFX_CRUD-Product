package main.dojavafx.Repository;

import main.dojavafx.Entity.SonyAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<SonyAccounts, Integer> {
    SonyAccounts findByPhoneAndPassword(String phone, String password);
}

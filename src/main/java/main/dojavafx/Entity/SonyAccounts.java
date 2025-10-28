package main.dojavafx.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="SonyAccounts")
public class SonyAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AccountID")
    private int accountID;

    @Column(name = "Phone", length = 13)
    private String phone;

    @Column(name = "Password", length = 10)
    private String password;

    @Column(name = "RoleID")
    private int roleID;

    public SonyAccounts() {
    }

    public SonyAccounts(String phone, String password, int roleID) {
        this.phone = phone;
        this.password = password;
        this.roleID = roleID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}

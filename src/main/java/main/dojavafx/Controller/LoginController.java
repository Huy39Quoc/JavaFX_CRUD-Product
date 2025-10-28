package main.dojavafx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.dojavafx.App;
import main.dojavafx.Entity.SonyAccounts;
import main.dojavafx.Service.AccountsInterface;
import org.springframework.context.ApplicationContext;

public class LoginController {

    @FXML private TextField txtPhone;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;

    private Stage stage;
    private ApplicationContext springContext;
    private AccountsInterface accountService;

    public void init(Stage stage, ApplicationContext context){
        this.stage = stage;
        this.springContext = context;
        this.accountService = context.getBean(AccountsInterface.class);
    }

    @FXML
    public void handleLogin(){
        String phone = txtPhone.getText().trim();
        String password = txtPassword.getText().trim();

        SonyAccounts user = accountService.LoginAcc(phone, password);
        if(user != null){
            // Login thành công, load Home
            try {
                App.loadHome(stage, user);
            } catch (Exception e) {
                lblError.setText("Error loading Home scene: " + e.getMessage());
            }
        } else {
            lblError.setText("Invalid phone or password!");
        }
    }
}

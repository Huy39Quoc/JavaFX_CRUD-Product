package main.dojavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.dojavafx.Controller.HomeController;
import main.dojavafx.Controller.LoginController;
import main.dojavafx.Controller.ProductController;
import main.dojavafx.Entity.SonyAccounts;
import main.dojavafx.Entity.SonyProducts;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class App {

    private static ApplicationContext springContext;

    // Khởi tạo Spring context (gọi 1 lần khi app chạy)
    public static void initSpring() {
        if (springContext == null) {
            springContext = new AnnotationConfigApplicationContext(SpringBootApp.class);
        }
    }

    public static ApplicationContext getSpringContext() {
        return springContext;
    }

    // Load Login Scene
    public static void loadLogin(Stage stage) throws IOException {
        initSpring();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(loader.load());
        LoginController controller = loader.getController();
        controller.init(stage, springContext);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    // Load Home Scene
    public static void loadHome(Stage stage, SonyAccounts user) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/Home.fxml"));
        Scene scene = new Scene(loader.load());
        HomeController controller = loader.getController();
        controller.init(stage, springContext, user);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    // Load Product Form Scene (dùng cho Add hoặc Edit)
    public static void loadProductForm(Stage stage, SonyAccounts user, SonyProducts product, HomeController homeController) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/ProductForm.fxml"));
        Scene scene = new Scene(loader.load());
        ProductController controller = loader.getController();
        controller.init(stage, springContext, user, product, homeController);
        stage.setTitle(product.getProductID() == 0 ? "Add Product" : "Edit Product");
        stage.setScene(scene);
        stage.show();
    }

}

package main.dojavafx.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.dojavafx.Entity.SonyAccounts;
import main.dojavafx.Entity.SonyCategories;
import main.dojavafx.Entity.SonyProducts;
import main.dojavafx.Repository.CategoriesRepository;
import main.dojavafx.Service.ProductInterface;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ProductController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtStock;
    @FXML
    private ComboBox<SonyCategories> comboCategory;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    private Stage stage;
    private ApplicationContext springContext;
    private SonyAccounts currentUser;
    private SonyProducts product;
    private HomeController homeController;
    private ProductInterface productService;
    private CategoriesRepository categoriesRepository;

    public void init(Stage stage, ApplicationContext context, SonyAccounts user, SonyProducts product, HomeController homeController) {
        this.stage = stage;
        this.springContext = context;
        this.currentUser = user;
        this.product = product;
        this.homeController = homeController;

        // Lấy service và repository từ Spring context
        this.productService = springContext.getBean(ProductInterface.class);
        this.categoriesRepository = springContext.getBean(CategoriesRepository.class);

        // Bind dữ liệu nếu là Edit
        if (product.getProductID() != 0) {
            txtName.setText(product.getProductName());
            txtPrice.setText(String.valueOf(product.getPrice()));
            txtStock.setText(String.valueOf(product.getStock()));
        }

        // Load categories từ repository
        List<SonyCategories> categories = categoriesRepository.findAll();
        comboCategory.setItems(FXCollections.observableArrayList(categories));

        if (product.getCategory() != null) {
            comboCategory.setValue(product.getCategory());
        }
    }

    @FXML
    private void handleSave() {
        try {
            // Lấy dữ liệu từ form
            product.setProductName(txtName.getText().trim());
            product.setPrice(Integer.parseInt(txtPrice.getText().trim()));
            product.setStock(Integer.parseInt(txtStock.getText().trim()));
            product.setCategory(comboCategory.getValue());

            // Kiểm tra Add hay Edit
            boolean success;
            if (product.getProductID() == 0) {
                success = productService.createProduct(product);
            } else {
                success = productService.updateProduct(product);
            }

            if (!success) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Duplicate name or invalid data.");
                alert.show();
                return;
            }

            // Refresh Home table
            if (homeController != null) homeController.loadProducts();

            stage.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Price and Stock must be numbers.");
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "An unexpected error occurred.");
            alert.show();
        }
    }

    @FXML
    private void handleCancel() {
        stage.close();
    }
}

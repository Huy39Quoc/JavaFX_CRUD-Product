package main.dojavafx.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.dojavafx.App;
import main.dojavafx.Entity.SonyAccounts;
import main.dojavafx.Entity.SonyCategories;
import main.dojavafx.Entity.SonyProducts;
import main.dojavafx.Service.ProductInterface;
import org.springframework.context.ApplicationContext;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class HomeController {

    @FXML
    private Label userLabel;

    @FXML
    private TableView<SonyProducts> productTable;

    @FXML
    private TableColumn<SonyProducts, Long> colId;
    @FXML
    private TableColumn<SonyProducts, String> colName;
    @FXML
    private TableColumn<SonyProducts, Integer> colPrice;
    @FXML
    private TableColumn<SonyProducts, Integer> colStock;
    @FXML
    private TableColumn<SonyProducts, String> colCreatedAt;
    @FXML
    private TableColumn<SonyProducts, String> colCategory;
    @FXML
    private TableColumn<SonyProducts, Void> colEdit;
    @FXML
    private TableColumn<SonyProducts, Void> colDelete;

    private ApplicationContext springContext;
    private SonyAccounts currentUser;
    private ProductInterface productService;
    private Stage stage;

    // Khởi tạo controller
    public void init(Stage stage, ApplicationContext context, SonyAccounts user) {
        this.stage = stage;
        this.springContext = context;
        this.currentUser = user;
        this.productService = springContext.getBean(ProductInterface.class);

        userLabel.setText("Welcome, " + currentUser.getPhone());

        setupTable();
        loadProducts();
    }

    private void setupTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colCreatedAt.setCellValueFactory(cell -> {
            if (cell.getValue().getCreatedAt() != null) {
                return new SimpleStringProperty(cell.getValue().getCreatedAt()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            } else return new SimpleStringProperty("");
        });
        colCategory.setCellValueFactory(cell -> {
            SonyCategories cat = cell.getValue().getCategory();
            return new SimpleStringProperty(cat != null ? cat.getCateName() : "N/A");
        });

        // Nút Edit
        colEdit.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Edit");
            {
                btn.setOnAction(event -> {
                    SonyProducts product = getTableView().getItems().get(getIndex());
                    try {
                        main.dojavafx.App.loadProductForm(stage, currentUser, product, HomeController.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        // Nút Delete
        colDelete.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Delete");
            {
                btn.setOnAction(event -> {
                    SonyProducts product = getTableView().getItems().get(getIndex());
                    boolean confirmed = confirmDialog("Are you sure to delete this product?");
                    if (confirmed) {
                        productService.deleteProduct(product);
                        loadProducts();
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    private boolean confirmDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    public void loadProducts() {
        List<SonyProducts> products = productService.getProductList();
        productTable.setItems(FXCollections.observableArrayList(products));
    }

    @FXML
    private void handleAdd() {
        try {
            main.dojavafx.App.loadProductForm(stage, currentUser, new SonyProducts(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() {
        try {
            main.dojavafx.App.loadLogin(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

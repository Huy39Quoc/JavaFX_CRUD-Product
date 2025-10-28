package main.dojavafx.Service;

import main.dojavafx.Entity.SonyProducts;

import java.util.List;

public interface ProductInterface {
    List<SonyProducts> getProductList();
    boolean createProduct(SonyProducts product);
    boolean updateProduct(SonyProducts product);
    boolean deleteProduct(SonyProducts product);
    List<SonyProducts> getTop3EachCategory();
}

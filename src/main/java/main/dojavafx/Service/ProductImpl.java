package main.dojavafx.Service;

import main.dojavafx.Entity.SonyProducts;
import main.dojavafx.Repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductImpl implements ProductInterface{
    private ProductsRepository productsRepository;

    public ProductImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<SonyProducts> getProductList() {
        return productsRepository.findAll();
    }

    @Override
    public boolean createProduct(SonyProducts product) {
        if(validateProduct(product)){
            boolean existed = getProductList().stream()
                    .anyMatch(products -> products.getProductID() == product.getProductID());
            if(existed){
                return false;
            }else{
                product.setCreatedAt(LocalDateTime.now());
                productsRepository.save(product);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateProduct(SonyProducts product) {
        if(validateProduct(product)){
            SonyProducts existed = getProductList().stream()
                    .filter(products -> products.getProductID() == product.getProductID())
                    .findAny().orElse(null);
            if(existed == null){
                return false;
            }else{
                existed.setProductName(product.getProductName());
                existed.setPrice(product.getPrice());
                existed.setStock(product.getStock());
                existed.setCategory(product.getCategory());
                existed.setCreatedAt(LocalDateTime.now());
                productsRepository.save(existed);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteProduct(SonyProducts product) {
        if(validateProduct(product)){
            boolean existed = getProductList().stream()
                    .anyMatch(products -> products.getProductID() == product.getProductID());
            if(!existed){
                return false;
            }else{
                productsRepository.deleteById(product.getProductID());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SonyProducts> getTop3EachCategory() {
        List<SonyProducts> allProducts = productsRepository.findAll();

        return allProducts.stream()
                .collect(Collectors.groupingBy(p -> p.getCategory().getCateID()))
                .values().stream()
                .flatMap(group -> group.stream()
                        .sorted(Comparator.comparingInt(SonyProducts::getStock).reversed())
                        .limit(3))
                .collect(Collectors.toList());
    }

    private boolean validateProduct(SonyProducts product) {
        if (product.getProductName() == null || product.getProductName().isBlank()) return false;
        int length = product.getProductName().length();
        if (length < 5 || length > 50) return false;
        if (product.getPrice() < 100) return false;
        if (product.getStock() < 0 || product.getStock() > 1000) return false;
        return true;
    }
}

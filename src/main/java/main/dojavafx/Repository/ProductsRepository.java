package main.dojavafx.Repository;

import main.dojavafx.Entity.SonyProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<SonyProducts, Long> {
    SonyProducts findByProductName(String productName);
}

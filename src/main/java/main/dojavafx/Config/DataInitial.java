package main.dojavafx.Config;

import main.dojavafx.Entity.SonyAccounts;
import main.dojavafx.Entity.SonyCategories;
import main.dojavafx.Entity.SonyProducts;
import main.dojavafx.Repository.AccountsRepository;
import main.dojavafx.Repository.CategoriesRepository;
import main.dojavafx.Repository.ProductsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitial implements CommandLineRunner {
    private AccountsRepository accountsRepository;
    private CategoriesRepository categoriesRepository;
    private ProductsRepository productsRepository;

    public DataInitial(AccountsRepository accountsRepository, CategoriesRepository categoriesRepository, ProductsRepository productsRepository) {
        this.accountsRepository = accountsRepository;
        this.categoriesRepository = categoriesRepository;
        this.productsRepository = productsRepository;
    }
    public void run(String... args) throws Exception {
        accountsRepository.save(new SonyAccounts("0905111111", "@1", 1));
        accountsRepository.save(new SonyAccounts("0905222222", "@1", 2));
        accountsRepository.save(new SonyAccounts("0905333333", "@1", 3));

        SonyCategories headphone = categoriesRepository.save(new SonyCategories("HeadPhone", "active"));
        SonyCategories camera = categoriesRepository.save(new SonyCategories("Cameras", "active"));
        SonyCategories tv = categoriesRepository.save(new SonyCategories("TVs", "active"));

        productsRepository.save((new SonyProducts("Alpha 1 II-Full-frame Mirrorless", 6000, 3, LocalDateTime.of(2025, 3, 3, 0, 0), camera)));
        productsRepository.save((new SonyProducts("Alpha 7C II-Full-frame", 2000, 5, LocalDateTime.of(2025, 4, 4, 0, 0), camera)));
        productsRepository.save((new SonyProducts("BRAVIA 8 OLED 4K HDR TV", 2500, 10, LocalDateTime.of(2025, 1, 1, 0, 0), tv)));
        productsRepository.save((new SonyProducts("LinkBuds Fit Truly Wireless Noise Canceling", 180, 15, LocalDateTime.of(2025, 3, 3, 0, 0), headphone)));
    }
}

package main.dojavafx.Repository;

import main.dojavafx.Entity.SonyCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<SonyCategories, Integer> {
}

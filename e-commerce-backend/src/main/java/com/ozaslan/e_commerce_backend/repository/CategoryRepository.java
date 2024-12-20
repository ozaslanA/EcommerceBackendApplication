package com.ozaslan.e_commerce_backend.repository;

import com.ozaslan.e_commerce_backend.model.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    public Category findByName(String name);

    @Query(value = "SELECT * FROM category c WHERE c.name = :name AND c.parent_category_name = :parentCategoryName", nativeQuery = true)
    Category findByNameAndParant(@Param("name") String name, @Param("parentCategoryName") String parentCategoryName);

}
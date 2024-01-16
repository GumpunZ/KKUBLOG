package com.cp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{
    
}

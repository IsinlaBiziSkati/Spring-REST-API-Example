package com.rest.school.repository;

import com.rest.school.model.ClassInventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassInventoryRepository extends JpaRepository<ClassInventory, Integer> {
    
}
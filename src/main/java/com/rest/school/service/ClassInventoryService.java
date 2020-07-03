package com.rest.school.service;

import java.util.List;

import com.rest.school.model.ClassInventory;
import com.rest.school.repository.ClassInventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class ClassInventoryService {
    
    @Autowired
    ClassInventoryRepository classInventoryRepository;

    public List<ClassInventory> getAllClassInventory(){
        return classInventoryRepository.findAll();
    }
    
    public ClassInventory getClassInventory(int id) throws NotFoundException{
        return classInventoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Class inventory not found with id: " + id));
    }

    public ClassInventory postClassInventory(ClassInventory classInventory){
        return classInventoryRepository.save(classInventory);
    }

    public ClassInventory putClassInventory(int id, ClassInventory classInventory) throws NotFoundException{
        return classInventoryRepository.findById(id).map(classInventoryObject ->{
            classInventoryObject.setName(classInventory.getName());
            classInventoryObject.setCode(classInventory.getCode());
            classInventoryObject.setClassroom(classInventory.getClassroom());
            classInventoryObject.setCount(classInventory.getCount());
            return classInventoryRepository.save(classInventoryObject);
        }).orElseThrow(() -> new NotFoundException("Could not find ClassInventory with id" + id));
    }

    public void deleteClassInventory(int id) {
        classInventoryRepository.deleteById(id);
    }
}
package com.rest.school.controller;

import java.util.List;

import javax.validation.Valid;

import com.rest.school.model.ClassInventory;
import com.rest.school.service.ClassInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("/classInventory")
public class ClassInventoryController {
    
    @Autowired
    ClassInventoryService classInventoryService;

    @GetMapping("/")
    public ResponseEntity<List<ClassInventory>> getClassInventoryList(){
        return ResponseEntity.ok(classInventoryService.getAllClassInventory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassInventory> getClassInventory(@PathVariable("id") int id) throws NotFoundException {
        return ResponseEntity.ok(classInventoryService.getClassInventory(id));
    }
    
    @PostMapping("/")
    public ResponseEntity<ClassInventory> postClassInventory(@Valid @RequestBody ClassInventory classInventory) throws NotFoundException {
        return new ResponseEntity<ClassInventory>(classInventoryService.postClassInventory(classInventory),HttpStatus.CREATED); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassInventory> putClassInventory(@PathVariable("id") int id, @Valid @RequestBody ClassInventory classInventory) throws NotFoundException {
        return new ResponseEntity<ClassInventory>(classInventoryService.putClassInventory(id, classInventory),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassInventory(@PathVariable("id")int id) {
        classInventoryService.deleteClassInventory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
package com.example.productmanager.service;

import com.example.productmanager.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> selectAll();

    void save(Product product);

    Product selectById(int id);

    void update(int id, Product product);

    void delete(int id);
    List<Product> search(String name);
}

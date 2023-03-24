package com.example.productmanager.service;

import com.example.productmanager.model.Product;

import java.util.*;

public class ProductService implements IProductService {
    private static final Map<Integer, Product> products = new HashMap<>();

    static {
        products.put(1, new Product(1, "Nam", 2000, "like new", "china"));
        products.put(2, new Product(2, "Nam", 2000, "like new", "china"));
        products.put(3, new Product(3, "Nam", 2000, "like new", "china"));
        products.put(4, new Product(4, "Nam", 2000, "like new", "china"));
        products.put(5, new Product(5, "Nam", 2000, "like new", "china"));
    }


    @Override
    public List<Product> selectAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product selectById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void delete(int id) {
        products.remove(id);
    }

    @Override
    public List<Product> search(String name) {
        String regex = ".*" + name.toLowerCase() + ".*";
        List<Product> products1 = new ArrayList<>();
        Set<Map.Entry<Integer, Product>> entries = products.entrySet();
        for (Map.Entry<Integer, Product> entry : entries) {
            if (entry.getValue().getName().toLowerCase().matches(regex)) {
                products1.add(entry.getValue());
            }
        }
        return products1;
    }
}



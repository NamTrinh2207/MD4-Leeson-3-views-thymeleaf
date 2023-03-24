package com.example.productmanager.controller;

import com.example.productmanager.model.Product;
import com.example.productmanager.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private IProductService productService;

    @GetMapping()
    public ModelAndView index() {
        List<Product> products = productService.selectAll();
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("products", new Product());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(Product product) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        product.setId((int) (Math.random() * 10000));
        productService.save(product);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("products", productService.selectById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect) {
        productService.delete(product.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/update")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("products", productService.selectById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(Product product) {
        productService.update(product.getId(), product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("products", productService.selectById(id));
        return "/view";
    }

    @GetMapping("/search")
    public String view(@RequestParam String name, Model model) {
        model.addAttribute("products", productService.search(name));
        return "/index";
    }
}

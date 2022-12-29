package com.example.testopenai.service;

import com.example.testopenai.model.dao.Category;
import com.example.testopenai.model.dao.Item;
import com.example.testopenai.model.dao.Supplier;
import com.example.testopenai.repository.CategoryRepository;
import com.example.testopenai.repository.ItemRepository;
import com.example.testopenai.repository.PurchaseRepository;
import com.example.testopenai.repository.SupplierRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StartupService {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ItemRepository itemRepository;

    @PostConstruct
    public void fillDb() {

        Map<String, Double> items = Map.of("CodeWorks IDE", 99.99D,
                "CodeWorks Debugger", 49.99D,
                "CodeWorks Code Formatter", 29.99D,
                "CodeWorks Git Integration", 19.99D,
                "CodeWorks Code Snippets", 9.99D,
                "TechTronix Desktop Computer", 999.99D,
                "TechTronix Laptop", 799.99D,
                "TechTronix Tablet", 499.99D,
                "TechTronix Smartphone", 399.99D,
                "TechTronix Smartwatch",299.9D);

        Map<String, Double> items2 = Map.of("Delicious Eats Co. Gourmet Pizza", 29.99D,
                "Delicious Eats Co. Spaghetti Bolognese", 19.99D,
                "Delicious Eats Co. Grilled Chicken Salad", 9.99D,
                "Delicious Eats Co. BBQ Ribs", 24.99D,
                "Delicious Eats Co. Sushi Platter", 39.99D,
                "Fashion Avenue Designer Dress", 199.99D,
                "Fashion Avenue Silk Blouse", 99.99D,
                "Fashion Avenue Leather Jacket", 299.99D,
                "Fashion Avenue Jeans", 79.99D,
                "Fashion Avenue Sunglasses", 49.99D);

        Map<String, Double> items3 = Map.of("ElectroWorld 65-inch 4K TV", 799.99D,
                "ElectroWorld Soundbar", 299.99D,
                "ElectroWorld Smart Home Hub", 99.99D,
                "ElectroWorld Wireless Headphones", 149.99D,
                "ElectroWorld Smartwatch", 199.99D,
                "Adventure Outfitters Hiking Backpack", 99.99D,
                "Adventure Outfitters Sleeping Bag", 79.99D,
                "Adventure Outfitters Tent", 129.99D,
                "Adventure Outfitters Trekking Poles", 49.99D,
                "Adventure Outfitters Headlamp", 29.99D);

        Map<String, Double> items4 = Map.of("FreshMart Organic Apples", 3.99D,
                "FreshMart Organic Spinach", 4.99D,
                "FreshMart Organic Milk", 5.99D,
                "FreshMart Organic Chicken Breasts", 8.99D,
                "FreshMart Organic Whole Wheat Bread", 3.99D,
                "Paws & Claws Co. Dog Collar", 19.99D,
                "Paws & Claws Co. Cat Scratching Post", 29.99D,
                "Paws & Claws Co. Fish Tank", 99.99D,
                "Paws & Claws Co. Bird Feeder", 14.99D,
                "Paws & Claws Co. Hamster Habitat", 49.99D);

        List<String> categoryNames = List.of(
                "Software",
                "Hardware",
                "Food",
                "Clothing", "Electronics",
                "Outdoor gear",
                "Grocery",
                "Pet supplies"
        );

        List<String> supplierNames = List.of(
                "CodeWorks",
                "TechTronix",
                "Delicious Eats Co.",
                "Fashion Avenue",
                "ElectroWorld",
                "Adventure Outfitters",
                "FreshMart",
                "Paws & Claws Co."
        );

        for(int i = 0; i < categoryNames.size(); i++) {
            Category category = new Category();
            category.setName(categoryNames.get(i));
            categoryRepository.save(category);
            Supplier supplier = new Supplier();
            supplier.setCompanyName(supplierNames.get(i));
            supplier.setCategory(category);
            supplierRepository.save(supplier);
        }

        items.forEach((s, aDouble) -> {
            Item item = new Item();
            item.setName(s);
            item.setPrice(aDouble);
            itemRepository.save(item);
        });


        items2.forEach((s, aDouble) -> {
            Item item = new Item();
            item.setName(s);
            item.setPrice(aDouble);
            itemRepository.save(item);
        });


        items3.forEach((s, aDouble) -> {
            Item item = new Item();
            item.setName(s);
            item.setPrice(aDouble);
            itemRepository.save(item);
        });


        items4.forEach((s, aDouble) -> {
            Item item = new Item();
            item.setName(s);
            item.setPrice(aDouble);
            itemRepository.save(item);
        });

    }
}

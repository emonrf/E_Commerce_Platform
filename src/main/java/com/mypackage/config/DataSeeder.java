package com.mypackage.config;

import com.mypackage.entity.*;
import com.mypackage.repository.CartRepository;
import com.mypackage.repository.CategoryRepository;
import com.mypackage.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CartRepository cartRepository;

    public DataSeeder(ProductRepository productRepository,
                      CategoryRepository categoryRepository,
                      CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Clear all existing data
        cartRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();

        // Create Categories
        Category electronics = new Category();
        electronics.setName("Electronics");

        Category clothing = new Category();
        clothing.setName("Clothing");

        Category home = new Category();
        home.setName("Home and Kitchen");

        Category books = new Category();
        books.setName("Books");

        Category sports = new Category();
        sports.setName("Sports & Outdoors");

        Category beauty = new Category();
        beauty.setName("Beauty & Personal Care");

        categoryRepository.saveAll(Arrays.asList(electronics, clothing, home, books, sports, beauty));

        // Create Products
        List<Product> products = Arrays.asList(
                // Electronics
                createProduct("Phone", "Latest model smartphone with amazing features.",
                        "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?q=80&w=1160&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 699.99, 10, electronics),

                createProduct("Laptop", "High-performance laptop for work and play.",
                        "https://images.unsplash.com/photo-1531297484001-80022131f5a1?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fGxhcHRvcHxlbnwwfHwwfHx8MA%3D%3D", 999.99, 5, electronics),

                createProduct("Headphones", "Noise-cancelling wireless headphones.",
                        "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=600", 249.99, 12, electronics),

                createProduct("Tablet", "Ultra-thin tablet with vibrant display.",
                        "https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?q=80&w=600", 399.99, 8, electronics),

                createProduct("Smartwatch", "Fitness tracking and notifications on your wrist.",
                        "https://images.unsplash.com/photo-1579586337278-3befd40fd17a?q=80&w=600", 199.99, 15, electronics),

                createProduct("Camera", "Digital camera with 4K video recording.",
                        "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?q=80&w=600", 549.99, 6, electronics),

                // Clothing
                createProduct("Jacket", "Warm and cozy jacket for the holidays.",
                        "https://images.unsplash.com/photo-1551488831-00ddcb6c6bd3?q=80&w=600", 129.99, 15, clothing),

                createProduct("T-shirt", "Comfortable cotton t-shirt.",
                        "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?q=80&w=600", 24.99, 50, clothing),

                createProduct("Jeans", "Classic denim jeans with perfect fit.",
                        "https://images.unsplash.com/photo-1542272604-787c3835535d?q=80&w=600", 59.99, 30, clothing),

                createProduct("Sneakers", "Lightweight running sneakers.",
                        "https://images.unsplash.com/photo-1600185365926-3a2ce3cdb9eb?q=80&w=600", 89.99, 20, clothing),

                createProduct("Sweater", "Soft wool sweater for winter.",
                        "https://images.unsplash.com/photo-1620799140408-edc6dcb6d633?q=80&w=600", 79.99, 25, clothing),

                createProduct("Dress", "Elegant evening dress.",
                        "https://images.unsplash.com/photo-1568252542512-9fe8fe9c87bb?q=80&w=600", 149.99, 10, clothing),

                // Home and Kitchen
                createProduct("Blender", "High-speed and robust blender.",
                        "https://plus.unsplash.com/premium_photo-1663853294058-3f85f18a4bed?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YmxlbmRlcnxlbnwwfHwwfHx8MA%3D%3D", 209.99, 8, home),

                createProduct("Coffee Maker", "Programmable coffee maker with thermal carafe.",
                        "https://images.unsplash.com/photo-1608354580875-30bd4168b351?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Y29mZmVlJTIwbWFrZXJ8ZW58MHx8MHx8fDA%3D", 129.99, 10, home),

                createProduct("Toaster", "4-slice toaster with multiple settings.",
                        "https://plus.unsplash.com/premium_photo-1719452894874-3da9fa3b882c?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8dG9hc3RlcnxlbnwwfHwwfHx8MA%3D%3D", 49.99, 15, home),

                createProduct("Vacuum Cleaner", "Cordless vacuum with powerful suction.",
                        "https://images.unsplash.com/photo-1558317374-067fb5f30001?q=80&w=600", 299.99, 7, home),

                createProduct("Cookware Set", "10-piece non-stick cookware set.",
                        "https://plus.unsplash.com/premium_photo-1664391825760-17aacf4cb3b4?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8Y29va3dhcmUlMjBzZXR8ZW58MHx8MHx8fDA%3D", 179.99, 5, home),

                createProduct("Air Fryer", "Digital air fryer for healthier cooking.",
                        "https://plus.unsplash.com/premium_photo-1711752902713-e49e0ecb5c97?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8YWlyZnJ5ZXJ8ZW58MHx8MHx8fDA%3D", 119.99, 12, home),

                // Books
                createProduct("Fiction Novel", "Bestselling fiction novel of the year.",
                        "https://images.unsplash.com/photo-1544947950-fa07a98d237f?q=80&w=600", 19.99, 30, books),

                createProduct("Cookbook", "Gourmet recipes from around the world.",
                        "https://images.unsplash.com/photo-1546552580-9427ccb15af4?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fGNvb2tib29rfGVufDB8fDB8fHww", 24.99, 20, books),

                createProduct("Self-Help Book", "Guide to personal development and growth.",
                        "https://images.unsplash.com/photo-1531988042231-d39a9cc12a9a?q=80&w=600", 15.99, 25, books),

                createProduct("Biography", "Inspiring story of a historical figure.",
                        "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?q=80&w=600", 22.99, 15, books),

                // Sports & Outdoors
                createProduct("Tennis Racket", "Professional grade tennis racket.",
                        "https://plus.unsplash.com/premium_photo-1664304642821-31278f3ff186?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8dGVubmlzJTIwcmFja2V0fGVufDB8fDB8fHww", 129.99, 10, sports),

                createProduct("Basketball", "Official size and weight basketball.",
                        "https://images.unsplash.com/photo-1519861531473-9200262188bf?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGJhc2tldGJhbGx8ZW58MHx8MHx8fDA%3D", 29.99, 20, sports),

                createProduct("Yoga Mat", "Non-slip exercise yoga mat.",
                        "https://images.unsplash.com/photo-1591291621164-2c6367723315?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8eW9nYSUyMG1hdHxlbnwwfHwwfHx8MA%3D%3D", 39.99, 25, sports),

                createProduct("Camping Tent", "4-person waterproof camping tent.",
                        "https://images.unsplash.com/photo-1504280390367-361c6d9f38f4?q=80&w=600", 199.99, 8, sports),

                createProduct("Fitness Tracker", "Waterproof activity and sleep tracker.",
                        "https://images.unsplash.com/photo-1576243345690-4e4b79b63288?q=80&w=600", 79.99, 15, sports),

                // Beauty & Personal Care
                createProduct("Facial Cleanser", "Gentle foaming facial cleanser.",
                        "https://images.unsplash.com/photo-1556228720-195a672e8a03?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmFjaWFsJTIwY2xlYW5zZXJ8ZW58MHx8MHx8fDA%3D", 19.99, 30, beauty),

                createProduct("Moisturizer", "Hydrating facial moisturizer with SPF.",
                        "https://images.unsplash.com/photo-1629732047847-50219e9c5aef?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8bW9pc3R1cml6ZXJ8ZW58MHx8MHx8fDA%3D", 24.99, 25, beauty),

                createProduct("Perfume", "Luxury fragrance in elegant bottle.",
                        "https://images.unsplash.com/photo-1610461888750-10bfc601b874?q=80&w=600", 89.99, 15, beauty),

                createProduct("Hair Dryer", "Professional salon-quality hair dryer.",
                        "https://images.unsplash.com/photo-1522338140262-f46f5913618a?q=80&w=600", 59.99, 12, beauty)
        );

        productRepository.saveAll(products);

        // Create a sample cart (if you have a User entity, you'd associate it here)
        Cart demoCart = new Cart();
        cartRepository.save(demoCart);

        // Add some items to the cart
        CartItem phoneItem = new CartItem(products.get(0), 1);  // Phone
        phoneItem.setCart(demoCart);

        CartItem jacketItem = new CartItem(products.get(6), 2);  // Jacket
        jacketItem.setCart(demoCart);

        CartItem bookItem = new CartItem(products.get(18), 1);  // Fiction Novel
        bookItem.setCart(demoCart);

        demoCart.setCartItems(Arrays.asList(phoneItem, jacketItem, bookItem));
        demoCart.calculateTotalPrice();

        cartRepository.save(demoCart);
    }

    private Product createProduct(String name, String description, String imageUrl, double price, int stock, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        product.setPrice(price);
        product.setStockQuantity(stock);
        product.setCategory(category);
        return product;
    }
}
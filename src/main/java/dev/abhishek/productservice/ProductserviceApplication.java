package dev.abhishek.productservice;

//import dev.abhishek.productservice.inheritancedemo.singletable.Mentor;
//import dev.abhishek.productservice.inheritancedemo.singletable.MentorRepository;
//import dev.abhishek.productservice.inheritancedemo.singletable.User;
//import dev.abhishek.productservice.inheritancedemo.singletable.UserRepository;
//import dev.abhishek.productservice.inheritancedemo.tableperclass.Mentor;
//import dev.abhishek.productservice.inheritancedemo.tableperclass.MentorRepository;
//import dev.abhishek.productservice.inheritancedemo.tableperclass.User;
//import dev.abhishek.productservice.inheritancedemo.tableperclass.UserRepository;
import dev.abhishek.productservice.inheritancedemo.joinedtable.Mentor;
import dev.abhishek.productservice.inheritancedemo.joinedtable.MentorRepository;
import dev.abhishek.productservice.inheritancedemo.joinedtable.User;
import dev.abhishek.productservice.inheritancedemo.joinedtable.UserRepository;
import dev.abhishek.productservice.models.Category;
import dev.abhishek.productservice.models.Price;
import dev.abhishek.productservice.models.Product;
import dev.abhishek.productservice.repository.CategoryRepository;
import dev.abhishek.productservice.repository.PriceRepository;
import dev.abhishek.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {
    private MentorRepository mentorRepository;
    private UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    public ProductserviceApplication(@Qualifier("jt_mr") MentorRepository mentorRepository,@Qualifier("jt_ur") UserRepository userRepository,
                                     ProductRepository productRepository,
                                     CategoryRepository categoryRepository,
                                     PriceRepository priceRepository){
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }
    @Override
    public void run(String... args) throws Exception {
//        Mentor mentor = new Mentor();
//        mentor.setName("Abhishek");
//        mentor.setEmail("abhishek@scaler.com");
//        mentor.setAvgRating(5.67);
//        mentorRepository.save(mentor);
//
//        User user = new User();
//        user.setName("User_Abhishek");
//        user.setEmail("userabhishek@scaler.com");
//        userRepository.save(user);
//        List<User> users = userRepository.findAll();
//        for(User user1:users){
//            System.out.println(user1);
//        }
        Category category = new Category();
        category.setName("Apple Devices");
//        Category savedCategory  = categoryRepository.save(category);//Assume you forgot to save this

        Price price = new Price("Rupees",10);
//        Price savedPrice = priceRepository.save(price);//Assume we forgot to save this
        Product product = new Product();
        product.setTitle("iPhone 15 Pro");
        product.setDescription("Best iPhone Ever");
        product.setCategory(category);
        product.setPrice(price);
        productRepository.save(product);
        productRepository.deleteById(UUID.fromString("a1617cfa-8a3a-4538-9d81-c475c2d31799"));
        List<Product> products = productRepository.findAllByPrice_Currency("Rupees");
        System.out.println(productRepository.countAllByPrice_Currency("Rupees"));
        Product sortedProduct = productRepository.findByTitleEqualsAndPrice_PriceOrderByPrice_Price("iPhone 15 Pro",10);

//        Category category1 = categoryRepository.findById(UUID.fromString("4ef22b9e-7fa8-4b71-b2fe-5aade9c39e16")).get();
//        System.out.println("Category Name Is:"+category1.getName());
//        System.out.println("Printing all products in the category");
//        for(Product product1:category1.getProducts()){
//            try {
//                System.out.println(product1.getTitle());
//            }
//            catch (Exception e){
//                System.out.println(e.getMessage());
//            }
//        }

        List<Product> newProducts = productRepository.findAllByTitle("iPhone 15 Pro");
        System.out.println("New Prods:"+newProducts);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

}

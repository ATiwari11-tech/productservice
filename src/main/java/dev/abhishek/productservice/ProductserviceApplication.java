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
import dev.abhishek.productservice.models.Product;
import dev.abhishek.productservice.repository.CategoryRepository;
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

    public ProductserviceApplication(@Qualifier("jt_mr") MentorRepository mentorRepository,@Qualifier("jt_ur") UserRepository userRepository,
                                     ProductRepository productRepository,
                                     CategoryRepository categoryRepository){
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
        Category savedCategory  = categoryRepository.save(category);

        Product product = new Product();
        product.setTitle("iPhone 15 Pro");
        product.setDescription("Best iPhone Ever");
        product.setCategory(savedCategory);
        productRepository.save(product);

        Category category1 = categoryRepository.findById(UUID.fromString("4ef22b9e-7fa8-4b71-b2fe-5aade9c39e16")).get();
        System.out.println("Category Name Is:"+category1.getName());
        System.out.println("Printing all products in the category");
        for(Product product1:category1.getProducts()){
            try {
                System.out.println(product1.getTitle());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

}

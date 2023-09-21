package dev.abhishek.productservice.repository;

import dev.abhishek.productservice.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;


@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByTitleEquals(String title);//select * from products where title=""

    @Override
    Optional<Product> findById(UUID uuid);

    @Override
    List<Product> findAllById(Iterable<UUID> uuids);

    @Override
    List<Product> findAll();

    @Override
    void deleteById(UUID uuid);

    Product findByTitleEqualsAndPrice_Price(String title, double price);//select * from products where title=""
    //and Price.price=""
    List<Product> findAllByPrice_Currency(String currency);//select * from products where Price.currency=""
    long countAllByPrice_Currency(String currency);//fetch number of products with this currency
    Product findByTitleEqualsAndPrice_PriceOrderByPrice_Price(String title,double price);
    @Query(value=CustomQueries.FIND_ALL_BY_TITLE,nativeQuery = true)//nativeQuery here is mySQL
    List<Product> findAllByTitle(String title);//Annotate with Query if you don't want to rely on JPA Query and write your own
//    @Query("select Product from Product where Product.title=:title and Product.price.currency=:currency")//non native query i.e. Hibernate
//    //Query Language
//    List<Product> readAllByTitle(String title);//Annotate with Query if you don't want to rely on JPA Query and write your own
}

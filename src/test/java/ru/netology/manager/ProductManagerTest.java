package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.Repository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private Repository repo = new Repository();
    ProductManager manager = new ProductManager(repo);
    private Product blouse = new Product(1, "Blouse", 200);
    private Book harryPotter = new Book(12, "HarryPotter", 350, "Дж. Роулинг");
    private Smartphone redmiNot = new Smartphone(32, "Xiaomi Note 9 Pro", 1000, "Xiaomi");

    @Test
    void add3Product() {
        manager.add(blouse);
        manager.add(harryPotter);
        manager.add(redmiNot);

        Product[] actual = repo.findAll();
        Product[] expected = {blouse, harryPotter, redmiNot};

        assertArrayEquals(expected, actual);
    }

    @Test
    void add2Product() {
        manager.add(blouse);
        manager.add(harryPotter);

        Product[] actual = repo.findAll();
        Product[] expected = {blouse, harryPotter};

        assertArrayEquals(expected, actual);
    }

    @Test
    void add1Product() {
        manager.add(harryPotter);

        Product[] actual = repo.findAll();
        Product[] expected = {harryPotter};

        assertArrayEquals(expected, actual);
    }

    @Test
   void searchBy () {
        manager.add(blouse);
        manager.add(harryPotter);
        manager.add(redmiNot);

        Product[] actual = manager.searchBy("Xiaomi Note 9 Pro");
        Product [] expected = {redmiNot};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByWhenInTheRepoOneProduct() {
        manager.add(blouse);


        Product[] actual = manager.searchBy("Blouse");
        Product[] expected = {blouse};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByProductWhichIsNotOnTheList() {
        manager.add(blouse);
        manager.add(harryPotter);
        manager.add(redmiNot);


        Product[] actual = manager.searchBy("50 shades of grey");
        Product[] expected = {};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByBookHarryPotter() {
        manager.add(blouse);
        manager.add(harryPotter);
        manager.add(redmiNot);


        Product[] actual = manager.searchBy("HarryPotter");
        Product[] expected = {harryPotter};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByProductWhenTwoItemsMatchTheRequest() {
        Smartphone iphone128Gb = new Smartphone(45, "Iphone12", 1000, "Apple");
        Smartphone iphone256Gb = new Smartphone(46, "Iphone12", 2000, "Apple");
        manager.add(blouse);
        manager.add(harryPotter);
        manager.add(iphone128Gb);
        manager.add(redmiNot);
        manager.add(iphone256Gb);


        Product[] actual = manager.searchBy("Iphone12");
        Product[] expected = {iphone128Gb, iphone256Gb};

        assertArrayEquals(expected, actual);
    }

}
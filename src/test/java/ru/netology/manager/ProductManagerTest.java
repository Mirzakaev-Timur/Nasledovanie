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
    private Product shirt = new Product(1, "Shirt", 101);
    private Book harryPotter = new Book(12, "HarryPotter", 211, "Дж. Роулинг");
    private Smartphone iphoneX = new Smartphone(32, "IphoneX", 500, "Apple");

    @Test
    void add3Product() {
        manager.add(shirt);
        manager.add(harryPotter);
        manager.add(iphoneX);

        Product[] actual = repo.findAll();
        Product[] expected = {shirt, harryPotter, iphoneX};

        assertArrayEquals(expected, actual);
    }

    @Test
    void add2Product() {
        manager.add(shirt);
        manager.add(harryPotter);

        Product[] actual = repo.findAll();
        Product[] expected = {shirt, harryPotter};

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
    void searchBy() {
        manager.add(shirt);
        manager.add(harryPotter);
        manager.add(iphoneX);


        Product[] actual = manager.searchBy("IphoneX");
        Product[] expected = {iphoneX};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByWhenInTheRepoOneProduct() {
        manager.add(shirt);


        Product[] actual = manager.searchBy("Shirt");
        Product[] expected = {shirt};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByProductWhichIsNotOnTheList() {
        manager.add(shirt);
        manager.add(harryPotter);
        manager.add(iphoneX);


        Product[] actual = manager.searchBy("Oblivion");
        Product[] expected = {};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByBookHarryPotter() {
        manager.add(shirt);
        manager.add(harryPotter);
        manager.add(iphoneX);


        Product[] actual = manager.searchBy("HarryPotter");
        Product[] expected = {harryPotter};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByProductWhenTwoItemsMatchTheRequest() {
        Smartphone iphone128Gb = new Smartphone(45, "Iphone13", 1000, "Apple");
        Smartphone iphone256Gb = new Smartphone(46, "Iphone13", 2000, "Apple");
        manager.add(shirt);
        manager.add(harryPotter);
        manager.add(iphone128Gb);
        manager.add(iphoneX);
        manager.add(iphone256Gb);


        Product[] actual = manager.searchBy("Iphone13");
        Product[] expected = {iphone128Gb, iphone256Gb};

        assertArrayEquals(expected, actual);
    }

}
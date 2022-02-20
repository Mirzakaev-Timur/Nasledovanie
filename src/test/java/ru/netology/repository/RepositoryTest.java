package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.Repository;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private Repository repo = new Repository();
    private Product shirt = new Product(1, "Shirt", 101);
    private Book harryPotter = new Book(12, "Harry Potter", 211, "Дж. Роулинг");
    private Smartphone iphoneX = new Smartphone(32, "IphoneX", 500, "Apple");

    @Test
    void add3Product() {
        repo.save(shirt);
        repo.save(harryPotter);
        repo.save(iphoneX);

        Product[] actual = repo.findAll();
        Product[] expected = {shirt, harryPotter, iphoneX};

        assertArrayEquals(expected, actual);
    }

    @Test
    void add2Product() {
        Repository repo = new Repository();
        repo.save(shirt);
        repo.save(harryPotter);

        Product[] actual = repo.findAll();
        Product[] expected = {shirt, harryPotter};

        assertArrayEquals(expected, actual);
    }

    @Test
    void add1Product() {
        repo.save(iphoneX);

        Product[] actual = repo.findAll();
        Product[] expected = {iphoneX};

        assertArrayEquals(expected, actual);
    }

    @Test
    void findAll() {
        Product[] actual = repo.findAll();
        Product[] expected = {};

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdAll() throws NotFoundException {
        repo.save(shirt);
        repo.save(harryPotter);
        repo.save(iphoneX);

        repo.removeById(12);
        repo.removeById(32);
        repo.removeById(1);

        Product[] actual = repo.findAll();
        Product[] expected = {};

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdNotFound() {
        repo.save(shirt);
        repo.save(harryPotter);
        repo.save(iphoneX);

        Assertions.assertThrows(NotFoundException.class,()-> repo.removeById(77));

        Product[] actual = repo.findAll();
        Product[] expected = {shirt, harryPotter, iphoneX};

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdOneProd() throws NotFoundException {
        repo.save(shirt);
        repo.save(harryPotter);
        repo.save(iphoneX);

        repo.removeById(12);

        Product[] actual = repo.findAll();
        Product[] expected = {shirt, iphoneX};

        assertArrayEquals(expected, actual);
    }


}

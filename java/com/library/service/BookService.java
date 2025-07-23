package com.library.service;

import com.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findBooksByTitle(String title);
    Optional<Book> findById(Long id);
}

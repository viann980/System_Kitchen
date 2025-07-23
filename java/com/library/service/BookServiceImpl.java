package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}

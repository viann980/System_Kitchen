package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    // Endpoint untuk pencarian buku
    @GetMapping("/searchBooks")
    public String searchBooks(@RequestParam("title") String title, Model model) {
        List<Book> books = bookService.findBooksByTitle(title);
        model.addAttribute("books", books); // Model untuk daftar buku
        return "dashboard";
    }

    // Endpoint untuk detail buku
    @GetMapping("/books/detail/{id}")
    public String bookDetail(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id).orElse(null);
        model.addAttribute("book", book); // Model untuk detail buku
        return "book-detail";
    }
}

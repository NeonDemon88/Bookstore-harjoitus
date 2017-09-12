package com.harjoitus.Bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.harjoitus.Bookstore.domain.Book;
import com.harjoitus.Bookstore.domain.BookRepository;


@Controller
public class BookController {
	
	@Autowired
	private BookRepository rep; 
	
	@RequestMapping(value="/booklist", method = RequestMethod.GET)
    public String bookList(Model model) {	
        model.addAttribute("books", rep.findAll());
        return "booklist";
    }
	
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		rep.save(book);
		
		return "redirect:booklist";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model){
		
		rep.delete(bookId);
		return "redirect:../booklist";
	}

}

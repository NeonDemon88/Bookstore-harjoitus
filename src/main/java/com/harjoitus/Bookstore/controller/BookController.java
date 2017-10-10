package com.harjoitus.Bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harjoitus.Bookstore.domain.Book;
import com.harjoitus.Bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository rep;

	// show all books
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", rep.findAll());
		return "booklist";
	}
	
//	@RequestMapping(value = "/index")
//	public String index() {
//		return "index.html";
//	}

	// REST for all books
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> booklistRest(){
		return (List<Book>) rep.findAll();
	}
	
	//REST for id
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId){
		return rep.findOne(bookId);
	}

	@RequestMapping(value = "/add")
	public String addBook(Model model) {

		model.addAttribute("book", new Book());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		rep.save(book);

		return "redirect:booklist";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {

		rep.delete(bookId);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	} 

}

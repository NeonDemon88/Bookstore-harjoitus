package com.harjoitus.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.harjoitus.Bookstore.domain.Book;
import com.harjoitus.Bookstore.domain.BookRepository;
import com.harjoitus.Bookstore.domain.User;
import com.harjoitus.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookRunner(BookRepository rep, UserRepository urep) {
		return (args) -> {
			log.info("tallenna muutama kirja");
			rep.save(new Book("Kirja", "Title", 1985, "12345-6", 19.85));
			rep.save(new Book("Toinen", "Title2", 1990, "12569-7", 10.85));

			log.info("hae kaikki kirjat");
			for (Book books : rep.findAll()) {
				log.info(books.toString());
			}
			
			User user1 = new User("user", "$2a$08$wQLf2jfDNAHw.LKNsXTJHOPla2Q1SawYVpG0MjK2EMGXDj9bBxka2", "user@gmail.com", "USER"); //kayttaja
			User user2 = new User("admin", "$2a$10$3mQSL/XJIzmc0P24/gatcODqMD9fgQ5m2h/CRTvu9OSYkm24kKxZK", "admin@gmail.com", "ADMIN"); // yllapitaja
			urep.save(user1);
			urep.save(user2);
			

		};
	}
}

package com.example.pillawebappbooks.controllers;

import com.example.pillawebappbooks.dao.BookDao;
import com.example.pillawebappbooks.models.Book;
import com.example.pillawebappbooks.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/books")
public class BookController {
        @Autowired
        private BookDao bookRepository;

        @RequestMapping()
        public String index(HttpSession session,Model model) {
            if(session.getAttribute("loggedUser") == null)
                return "redirect:/user/signin";
            model.addAttribute("books", bookRepository.findByUser((User)session.getAttribute("loggedUser")));
            return "books";
        }

        @RequestMapping(value = "/add")
        public String addBookPage(HttpSession session) {
            if(session.getAttribute("loggedUser") == null)
                return "redirect:/user/signin";
            return "addbook";
        }
        @RequestMapping(value = "/add",method = RequestMethod.POST)
        public String addBook(@RequestParam("title") String title, @RequestParam("description") String description,HttpSession session) {
            Book book = new Book(title, description,(User)session.getAttribute("loggedUser"));
            bookRepository.save(book);
            return "redirect:/books";
        }

        @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
        public String deleteBook(@PathVariable("id") Long id) {
            bookRepository.deleteById(id);
            return "redirect:/books";
        }

        @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
        public String editBookPage(@PathVariable("id") Long id, Model model) {
            model.addAttribute("book", bookRepository.findById(id).get());
            return "editbook";
        }
        @RequestMapping(value = "/edit",method = RequestMethod.POST)
        public String editBook(@RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("description") String description) {
            Book book = bookRepository.findById(id).get();
            book.setTitle(title);
            book.setDescription(description);
            bookRepository.save(book);
            return "redirect:/books";
        }

        @RequestMapping("/sincronizza")
        @ResponseBody
        public String sincronizza() {
            String url = "https://www.googleapis.com/books/v1/volumes?q=search+terms";

            return "giorgio";
        }



}

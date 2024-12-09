package com.onlineBook.controller;

import com.onlineBook.entity.Author;
import com.onlineBook.repository.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class AuthorController {

  private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  private final AuthorRepository authorRepository;

  public AuthorController(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @GetMapping("/authors")
  public String getAuthorsPage(ModelMap model) {
    List<Author> authors = authorRepository.findAll();
    model.put("authors", authors);
    return "authors";
  }

  @GetMapping("/authors/add")
  public String addAuthorPage() {
    return "addAuthor";
  }

  @PostMapping("/authors/add")
  public String addAuthor(@ModelAttribute Author author) {
    authorRepository.save(author);
    return "redirect:/authors";
  }

  @GetMapping("/authors/delete")
  public String deleteAuthor(@RequestParam("id") int id) {
    authorRepository.deleteById(id);
    return "redirect:/authors";
  }

  @GetMapping("/authors/description")
  public String getAuthorById(ModelMap modelMap, @RequestParam("id") int id) {
    Author author = authorRepository.findByIdOrThrow(id);
    modelMap.put("author", author);
    return "authorDescription";
  }

}
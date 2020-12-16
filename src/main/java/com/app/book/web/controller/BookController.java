package com.app.book.web.controller;

import com.app.book.web.common.exception.ApiException;
import com.app.book.web.common.response.ApiSuccess;
import com.app.book.web.domain.Book;
import com.app.book.web.domain.Member;
import com.app.book.web.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * Book 목록 조회
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping({"", "/"})
    public String index(Model model) throws Exception {

        ApiSuccess<List<Book>> books = bookService.findBooks();
        model.addAttribute("books", books.getData());

        return "views/book";
    }

    /**
     * Book 목록 조회
     * @return
     * @throws Exception
     */
    @GetMapping(path = "/list")
    public @ResponseBody ResponseEntity<?> getBooks() throws Exception {

        try {
            ApiSuccess<List<Book>> books = bookService.findBooks();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (ApiException e) {
            return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Book 등록
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping(path = "/storage")
    public @ResponseBody ResponseEntity<?> insertBook(@AuthenticationPrincipal Member member, @RequestBody Book dto) throws Exception {
        try {
            ApiSuccess<?> data = bookService.insertBook(member.getMemId(), dto);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (ApiException e) {
            return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
        }
    }
}

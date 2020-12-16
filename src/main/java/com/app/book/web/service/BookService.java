package com.app.book.web.service;

import com.app.book.web.common.response.ApiSuccess;
import com.app.book.web.domain.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {

    ApiSuccess<List<Book>> findBooks() throws IOException;

    ApiSuccess<?> insertBook(String memId, Book dto) throws IOException;
}

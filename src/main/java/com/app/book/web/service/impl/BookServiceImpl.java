package com.app.book.web.service.impl;

import com.app.book.web.common.exception.ApiException;
import com.app.book.web.common.response.ApiSuccess;
import com.app.book.web.domain.Book;
import com.app.book.web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Value("${url.book-api}")
    private String BOOK_API;

    private RestTemplate apiRestTemplate;

    @Autowired
    @Qualifier("apiRestTemplate")
    public void setApiRestTemplate(RestTemplate restTemplate) {
        this.apiRestTemplate = restTemplate;
    }

    @Override
    public ApiSuccess<List<Book>> findBooks() throws IOException {

        ApiSuccess<List<Book>> books = null;
        try {
            ResponseEntity<ApiSuccess> responseEntity = apiRestTemplate.getForEntity(BOOK_API + "/api/books", ApiSuccess.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                books = responseEntity.getBody();
            }
        } catch (HttpClientErrorException e) {
            throw new ApiException(e);
        } catch (HttpServerErrorException e) {
            throw new ApiException(e);
        }

        return books;
    }

    @Override
    public ApiSuccess<?> insertBook(String memId, Book dto) throws IOException {

        ApiSuccess<?> data = null;
        try {
            dto.setRegId(memId);
            ResponseEntity<ApiSuccess> responseEntity = apiRestTemplate.postForEntity(BOOK_API + "/api/books", dto, ApiSuccess.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                data = responseEntity.getBody();
            }
        } catch (HttpClientErrorException e) {
            throw new ApiException(e);
        } catch (HttpServerErrorException e) {
            throw new ApiException(e);
        }

        return data;
    }
}

package ru.maxima.babayan.project1.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.babayan.project1.models.Book;
import ru.maxima.babayan.project1.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Book book = new Book();
        book.setBookId(resultSet.getInt("book_id"));
        book.setPersonId(resultSet.getInt("person_id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setYearOfPublishing(resultSet.getInt("year_of_publishing"));

        return book;
    }
}

package ru.maxima.babayan.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.babayan.project1.models.Book;
import ru.maxima.babayan.project1.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {

        return jdbcTemplate.query("select * from book", new BookMapper());
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book (title, author, year_of_publishing) values (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearOfPublishing());
    }

    public Book show(int bookId) {
        return jdbcTemplate.query("select * from book where book_id=?",
                        new Object[]{bookId}, new BookMapper()).stream().findAny().orElse(null);
    }

    public void update(int bookId, Book updatedBook) {
        jdbcTemplate.update("update book set title=?, author=?, year_of_publishing=? where book_id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYearOfPublishing(), bookId);
    }

    public void delete(int bookId) {
        jdbcTemplate.update("delete from book where book_id=?", bookId);
    }

    public List<Book> indexOwner(int personId) {
        return jdbcTemplate.query("select * from book where person_id=?",
                new Object[]{personId}, new BookMapper());
    }

    public List<Book> hasBooks(int personId) {
        return jdbcTemplate.query("select * from book where person_id=?", new Object[]{personId}, new BookMapper());
    }

    public void unlink(int bookId) {
        jdbcTemplate.update("update book set person_id=? where book_id=?", null, bookId);

    }

    public void assign(int bookId, Person person) {
        jdbcTemplate.update("update book set person_id=? where book_id=?", person.getPersonId(), bookId);

    }
}

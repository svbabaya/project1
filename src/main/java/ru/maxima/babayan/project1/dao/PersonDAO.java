package ru.maxima.babayan.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.babayan.project1.models.Book;
import ru.maxima.babayan.project1.models.Person;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person (name, year_of_birth) values (?, ?)",
                person.getName(), person.getYearOfBirth());
    }

    public Person show(int personId) {
        return jdbcTemplate.query("select * from person where person_id = ?",
                new Object[]{personId}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public void update(int personId, Person updatedPerson) {
        jdbcTemplate.update("update person set name=?, year_of_birth=? where person_id=?",
                updatedPerson.getName(), updatedPerson.getYearOfBirth(), personId);
    }

    public void delete(int personId) {
        jdbcTemplate.update("delete from person where person_id=?", personId);
    }

    public Person indexBook(int bookId) {
        return jdbcTemplate.query("select * from person join book on book.person_id=person.person_id where book_id=?",
                        new Object[]{bookId}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public List<Book> hasOwner(int bookId) {
        return jdbcTemplate.query("select * from book where book_id=? and person_id is null",
                new Object[]{bookId}, new BookMapper());
    }

}

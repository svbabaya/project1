package ru.maxima.babayan.project1.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.babayan.project1.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Person person = new Person();
        person.setPersonId(resultSet.getInt("person_id"));
        person.setName(resultSet.getString("name"));
        person.setYearOfBirth(resultSet.getInt("year_of_birth"));

        return person;
    }

}

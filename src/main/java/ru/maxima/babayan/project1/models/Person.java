package ru.maxima.babayan.project1.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int personId;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should between 2 and 50 characters")
    private String name;

//    @NotEmpty(message = "Year of birth shouldn't be empty")
    @Min(value = 1900, message = "Year of birth should be greater than 1900")
    private int yearOfBirth;

//    public Person(int personId, String name, int yearOfBirth) {
//        this.personId = personId;
//        this.name = name;
//        this.yearOfBirth = yearOfBirth;
//    }

    public Person() {

    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}

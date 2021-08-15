package com.task.springphonebook.repositories;

import com.task.springphonebook.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findOneByTitle(String surName);
    List<Contact> findAllBySurnameFirstnamePatronymic(String surName, String firstName, String patronymic);
    List<Contact> findAllByBirthDate(LocalDate birthDate);
    List<Contact> findAllGroupByTitle();
    List<Contact> findAllGroupByBirthday();

}

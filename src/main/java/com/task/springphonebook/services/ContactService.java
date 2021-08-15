package com.task.springphonebook.services;

import com.task.springphonebook.entities.Contact;
import com.task.springphonebook.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContactService {
    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    //добавление контакта
    public void addContact(Contact contact){
        contactRepository.save(contact);
    }

    //получение списка всех контактов
    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    //получение контакта по ID
    public Contact getContactById(Long id){
        return contactRepository.getById(id);

    }

    //редактирование контакта по ID
    public boolean updateContactById(Long id){
        return true;
    }

    //поиск контакта по фамилии
    public Contact findContact(String surName) {
        return contactRepository.findOneByTitle(surName);
    }

    //поиск людей по дате рождения в диапазоне
    public List<Contact> findContactByBirthDate(LocalDate birthDate){
        return contactRepository.findAllByBirthDate(birthDate);
    }

    //поиск людей по ФИО
    public List<Contact> findContactByFIO(String surName, String firstName,String patronymic){
        return contactRepository.findAllBySurnameFirstnamePatronymic(surName,firstName,patronymic);
    }

    //удаление контакта по ID
    public void removeContactById(Long id){
        contactRepository.deleteById(id);
    }

    //сортировать контакты по имени
    public List<Contact> sortAllContactsBySurname(){
        return contactRepository.findAllGroupByTitle();
    }

    //сортировать контакты по дате рождения
    public List<Contact> sortAllContactsByBirthdate(){
        return contactRepository.findAllGroupByBirthday();
    }
}

package com.task.springphonebook.controllers;

import com.task.springphonebook.entities.Contact;
import com.task.springphonebook.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {

    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    //добавление контакта
    @PostMapping("/contacts")
    public String addContact(@RequestBody Contact contact){
        contactService.addContact(contact);
        return "Contact added";
    }


    //Получение списка всех контактов
    @GetMapping("/contacts")
    public String homepage(Model model){
        List<Contact> allContacts = contactService.getAllContacts();
        model.addAttribute("contacts",allContacts);
        return "contacts";
    }

    //получение детальной информации о контакте
    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable("id") Long id){
        Contact selectedContact = contactService.getContactById(id);
        model.addAttribute("selectedContact",selectedContact);
        return "details";
    }

    //редактирование контакта
    @PutMapping("details/{id}/edit")
    public boolean editContact(@PathVariable("id") Long id){
        boolean edit = contactService.updateContactById(id);
        return edit;
    }

    //поиск контактов по Фамили
    @GetMapping("/find_by_surname")
    public String findContact(Model model, @RequestParam("surname") String surName){
        Contact contact = contactService.findContact(surName);
        model.addAttribute("foundContact",contact);
        return "details";
    }

    //поиске контакта по дате рождения
    @GetMapping("/find_by_birthdate")
    public String findContactByFIO(Model model, @RequestParam("birthdate") LocalDate birthDate){
        List<Contact> contacts= contactService.findContactByBirthDate(birthDate);
        model.addAttribute("foundContact",contacts);
        return "contacts";
    }


    //Получение списка всех контактов
    @RequestMapping("/sort_by_name")
    public String sortAllContactsByName(Model model){
        List<Contact> allContacts = contactService.getAllContacts();
        model.addAttribute("contacts",allContacts);
        return "contacts";
    }

    //удаление контакта
    @DeleteMapping("details/{id}/delete")
    public String deleteContactById(@PathVariable Long id){
        contactService.removeContactById(id);
        return "redirect:/contacts";
    }
}

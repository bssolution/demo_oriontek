package com.oriontek.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oriontek.demo.dto.ContactDTO;
import com.oriontek.demo.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<ContactDTO> createContact(@PathVariable("customerId") Long customerId, @RequestBody ContactDTO contactDTO) {
        ContactDTO createdContact = contactService.createContactForCustomer(customerId, contactDTO);
        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<ContactDTO>> getContactsByCustomerId(@PathVariable("customerId") Long customerId) {
        List<ContactDTO> contacts = contactService.getContactsByCustomerId(customerId);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable("customerId") Long customerId, @PathVariable("contactId") Long contactId, @RequestBody ContactDTO contactDTO) {
        ContactDTO updatedContact = contactService.updateContactForCustomer(customerId, contactId, contactDTO);
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable("customerId") Long customerId, @PathVariable("contactId") Long contactId) {
        contactService.deleteContactForCustomer(customerId, contactId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

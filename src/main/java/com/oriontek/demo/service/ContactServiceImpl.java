package com.oriontek.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oriontek.demo.dto.ContactDTO;
import com.oriontek.demo.model.Contact;
import com.oriontek.demo.model.Customer;
import com.oriontek.demo.repository.ContactRepository;
import com.oriontek.demo.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContactServiceImpl implements ContactService {

    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(CustomerRepository customerRepository, ContactRepository contactRepository) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public ContactDTO createContactForCustomer(Long customerId, ContactDTO contactDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));

        Contact contact = new Contact();
        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        contact.setCustomer(customer);

        Contact savedContact = contactRepository.save(contact);
        return mapContactToDTO(savedContact);
    }

    @Override
    public List<ContactDTO> getContactsByCustomerId(Long customerId) {
        List<Contact> contacts = contactRepository.findByCustomerId(customerId);
        return contacts.stream()
                .map(this::mapContactToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO updateContactForCustomer(Long customerId, Long contactId, ContactDTO contactDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));

        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + contactId));

        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        contact.setCustomer(customer);

        Contact updatedContact = contactRepository.save(contact);
        return mapContactToDTO(updatedContact);
    }

    @Override
    public void deleteContactForCustomer(Long customerId, Long contactId) {
        contactRepository.delete(findContactByIdAndCustomerId(contactId, customerId));
    }

    private Contact findContactByIdAndCustomerId(Long contactId, Long customerId) {
        return contactRepository.findById(contactId)
                .filter(contact -> contact.getCustomer().getId().equals(customerId))
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + contactId));
    }

    private ContactDTO mapContactToDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setName(contact.getName());
        contactDTO.setEmail(contact.getEmail());
        contactDTO.setPhone(contact.getPhone());
        return contactDTO;
    }

    @Override
    public List<ContactDTO> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream()
                .map(this::mapContactToDTO)
                .collect(Collectors.toList());
    }
}

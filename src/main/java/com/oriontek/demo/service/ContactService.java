package com.oriontek.demo.service;

import java.util.List;

import com.oriontek.demo.dto.ContactDTO;

public interface ContactService {

        ContactDTO createContactForCustomer(Long customerId, ContactDTO contactDTO);
        List<ContactDTO> getContactsByCustomerId(Long customerId);
        ContactDTO updateContactForCustomer(Long customerId, Long contactId, ContactDTO contactDTO);
        List<ContactDTO> getAllContacts();
        void deleteContactForCustomer(Long customerId, Long contactId);
    
}
package com.oriontek.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.oriontek.demo.model.Company;
import com.oriontek.demo.model.Contact;

public class CustomerDTO  implements Serializable{
    private Long id;
    private String name;
    private String email;
    private Long companyId;
    private Company company;
    private List<Contact> contacts = new ArrayList<>();
    public List<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
 

   

}

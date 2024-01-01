package com.example.myapplication;

public class ContactsModule {
    private int id;
    private String name;
    private String contactNumber;

    public ContactsModule(int id, String name, String contactNumber) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public boolean equalsByNameAndNumber(ContactsModule other) {
        return this.name.equals(other.getName()) && this.contactNumber.equals(other.getContactNumber());
    }
}

package com.example.myapplication;

public class ContactsModule {
    private int id;
    private String name;
    private String contactNumber;
    private byte[] photoData;

    public ContactsModule(int id, String name, String contactNumber, byte[] photoData) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.photoData = photoData;
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

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public boolean equalsByNameAndNumber(ContactsModule other) {
        return this.name.equals(other.getName()) && this.contactNumber.equals(other.getContactNumber());
    }
}

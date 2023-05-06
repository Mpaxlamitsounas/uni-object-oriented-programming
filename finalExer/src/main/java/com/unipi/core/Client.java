package com.unipi.core;

public class Client {

    private final String ADT;  // final since they shouldn't be changed, and act as unique IDs
    private final String AFM; // preferred String to avoid long integers
    private String name;
    private String property;
    private String address;
    private String phone;
    private String email;
    private boolean hadContracts;

    // public Client (String id, String afm) {
    //     this.id = id;
    //     this.afm = afm;
    //     this.hadContracts = false;
    // }
    // Arxika katalaba oti prepei na pairnoun mono ID kai AFM "Xarakthrizontai monadika apo to ADT kai AFM", o apo katw xrhsimopoieitai

    public Client(String id, String afm, String name, String property, String address, String phone, String email) {
        this.ADT = id;
        this.AFM = afm;
        this.name = name;
        this.property = property;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.hadContracts = false;
    }

    public Client(String id, String afm, String name, String property, String address, String phone, String email, boolean hadContracts) {
        this.ADT = id;
        this.AFM = afm;
        this.name = name;
        this.property = property;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.hadContracts = false;
    } // constructor with every field, den xrhsimopoieitai


    // regular getters/setters, nothing to see below
    public String getId() {
        return ADT;
    }

    public String getAfm() {
        return AFM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean hasHadContracts() {
        return hadContracts;
    }

    public void setHadContracts(boolean hadContracts) {
        this.hadContracts = hadContracts;
    }
   
}

package models;

public class Supplier {

    private String id;
    private String name;
    private String contactPerson;
    private String address;
    private String phoneNumber;
    private String productsSupplied;

    public Supplier(String id, String name, String contactPerson, String address, String phoneNumber, String productsSupplied) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.productsSupplied = productsSupplied;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProductsSupplied() {
        return productsSupplied;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setProductsSupplied(String productsSupplied) {
        this.productsSupplied = productsSupplied;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + contactPerson + "|" + address + "|" + phoneNumber + "|" + productsSupplied;
    }
}

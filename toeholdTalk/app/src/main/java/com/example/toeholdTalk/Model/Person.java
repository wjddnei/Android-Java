package com.example.toeholdTalk.Model;

public class Person implements Comparable<Person> {
    private String name;
    private String id;
    private String phoneNumber;

    public Person(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public String getPhoneNumber() { return this.phoneNumber; }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public int compareTo(Person person) {
        return this.name.compareTo(person.name);
    }
}

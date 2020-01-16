package com.example.toeholdTalk.Model;

public class Person implements Comparable<Person> {
    private String name;
    private String id;
    private String phoneNumber;
    private String imageUrl;

    public Person(String id, String name, String imageUrl) {
        this.name = name;
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return this.name;
    }
    public String getPhoneNumber() { return this.phoneNumber; }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getImageUrl(){
        return imageUrl;
    }
    @Override
    public int compareTo(Person person) {
        return this.name.compareTo(person.name);
    }

}

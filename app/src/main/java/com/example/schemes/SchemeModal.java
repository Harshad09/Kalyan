package com.example.schemes;

public class SchemeModal
{
    private String name;
    private String surname;

    private SchemeModal(){}

    private SchemeModal(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

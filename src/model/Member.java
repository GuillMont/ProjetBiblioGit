package model;

import java.util.ArrayList;
import java.util.List;

public class Member {

    int id;
    String firstName;
    String lastName;
    String mail;
    List<Book> borrowedBooks;

    public Member(int id, String firstName, String lastName, String mail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Book> getBorrowed() {
        return borrowedBooks;
    }

    public void setBorrowed(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}

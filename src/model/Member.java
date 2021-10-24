package model;

import java.util.ArrayList;
import java.util.List;

public class Member {

    String firstName;
    String lastName;
    String mail;
    List<Book> borrowedBooks;
    boolean hasBorrowed;

    public Member(int id, String firstName, String lastName, String mail) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.borrowedBooks = new ArrayList<>();
        hasBorrowed = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

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

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public boolean isHasBorrowed() {
        return hasBorrowed;
    }

    public void setHasBorrowed(boolean hasBorrowed) {
        this.hasBorrowed = hasBorrowed;
    }

    public String toString(){
        return firstName+" "+lastName+" ("+mail+")";
    }
}

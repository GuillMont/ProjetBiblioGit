package model;

import java.util.ArrayList;
import java.util.List;

public class Work {

    String title;
    String author;
    String date;

    public Work(String title, String author, String date) {
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString(){
        return getTitle()+"; " + getAuthor() + " ("+getDate()+")";
    }

    public List<Book> getBooks(List<Book> allBooks){
        List<Book> books = new ArrayList<>();
        for(Book book : allBooks){
            if(book.getWork()==this){
                books.add(book);
            }
        }
        return books;

    }
}

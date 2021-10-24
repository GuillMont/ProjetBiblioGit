package controller;

import model.Book;
import model.Work;

import java.util.ArrayList;
import java.util.List;

public class WorkController {

    private List<Work> works;
    public Parser parser;

    public WorkController(Parser parser) {
        works = new ArrayList<>();
        this.parser=parser;
        populate();
    }

    public void populate(){
        works.addAll(parser.workList);
    }

    public void addWork(Work work){
        works.add(work);
        parser.updateWorkXML(works);
    }

    /*** Getter & Setter ***/
    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public List<Book> getAvailableBook(){
        List<Book> availableBooks = new ArrayList<>();

        for(Book book : parser.getBookList()){
            if (book.isIsAvailable())
                availableBooks.add(book);
        }
        return availableBooks;
    }

    public List<Book> getNotAvailableBook(){
        List<Book> notAvailableBooks = new ArrayList<>();

        for(Book book : parser.getBookList()){
            if (!book.isIsAvailable())
                notAvailableBooks.add(book);
        }
        return notAvailableBooks;
    }

}

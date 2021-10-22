package model;

public class Book {

    public int id;
    public String purchaseDate;
    public boolean isAvailable;
    public Work work;

    public Book(int id, String purchaseDate, boolean isAvailable, Work work) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.isAvailable = isAvailable;
        this.work = work;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work oeuvre) {
        this.work = oeuvre;
    }
}
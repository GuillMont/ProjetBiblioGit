package controller;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import model.Member;
import model.Work;
import view.AllTabs;
import view.WorkTab;

import java.util.ArrayList;
import java.util.List;

import static view.WorkTab.*;

public class PretController {

    List<Book> availableBooks;
    List<Member> members;
    Group root = new Group();
    HBox Hbox = new HBox();
    Stage stage = new Stage();

    List<RadioButton> radioBooks = new ArrayList<>();
    List<RadioButton> radioMember = new ArrayList<>();
    WorkTab workTab;


    public PretController(List<Book> availableBooks, List<Member> members, WorkTab workTab){
        this.availableBooks=availableBooks;
        this.members=members;
        this.workTab=workTab;
        createStage();
    }

    public void createStage(){

        ScrollPane pane = new ScrollPane();
        Scene scene = new Scene(pane,900,500);

        initAvailableBook();
        initMembre();
        initSaveButton();

        root.getChildren().add(Hbox);
        pane.setContent(root);
        stage.setTitle("Pret");
        stage.setScene(scene);
        stage.show();
    }

    public void initAvailableBook(){
        VBox box = new VBox();
        box.setPadding(new Insets(10));
        box.setSpacing(5);
        for(Book book : availableBooks){
            RadioButton button1 = new RadioButton(book.getWork().getTitle()+" - "+ book.work.getAuthor()+" ("+book.getPurchaseDate()+")");

            button1.setSelected(false);
            radioBooks.add(button1);
            box.getChildren().add(button1);
        }
        Hbox.getChildren().add(box);
    }

    public void initMembre(){
        VBox box = new VBox();
        for(Member member : this.members){
            RadioButton button1 = new RadioButton(member.getFirstName()+" "+member.getLastName());

            button1.setSelected(false);
            radioMember.add(button1);
            box.getChildren().add(button1);
        }
        Hbox.getChildren().add(box);
    }

    public void initSaveButton(){
        Button save = new Button("Enregistrer le pret");
        save.setOnMouseClicked(e->{
            Member member = null;
            for(int i=0; i<radioMember.size();i++){
                if(radioMember.get(i).isSelected()){
                    member = members.get(i);
                }
            }
            List<Book> memberBook = new ArrayList<>();
            for(int i=0; i<radioBooks.size();i++){
                if(radioBooks.get(i).isSelected()){
                    memberBook.add(availableBooks.get(i));
                }
            }

            for(Book book : memberBook){
                book.setAvailable(false);
                Parser.setBook(book);
            }


            member.getBorrowedBooks().addAll(memberBook);
            if(member.getBorrowedBooks().size()>0) member.setHasBorrowed(true);

            Parser.setMemberList(members);
            workController.parser.updateMembreXML(members);

        //    workTab.updateList();

            stage.close();
        });
        this.Hbox.getChildren().add(save);
    }


}

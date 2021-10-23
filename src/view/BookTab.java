package view;

import com.sun.org.apache.xpath.internal.operations.Bool;
import controller.BookController;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Book;
import model.Member;


public class BookTab {

    private static BookController bookController = new BookController();
    private VBox vBoxBook;
    public static TableView<Book> tableBook;

    public BookTab(){
        vBoxBook = new VBox();

        Button buttonAddBook = new Button("Ajouter un livre");
        tableBook = new TableView<>();

        /** Création des colonnes **/
        final TableColumn<Book,String> purchaseDateColumn = new TableColumn<>("Date d'achat");
        final TableColumn<Book, Boolean> hasBorrowedColumn = new TableColumn<>("Membre");


        /** Définit le remplissage des colonnes **/
        purchaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        hasBorrowedColumn.setCellValueFactory(new PropertyValueFactory<>("hasBorrowed"));

        updateList();



    }

    private void updateList() {
    }

}

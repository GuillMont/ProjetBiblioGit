package view;

import controller.WorkController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Book;
import model.Work;

public class WorkTab {

    private static WorkController workController;
    public VBox vBoxWork;
    public static TreeTableView<Object> tableWork;

    public WorkTab(WorkController workController) {
        vBoxWork = new VBox();
        this.workController=workController;

        /**Création du bouton d'ajout d'oeuvre*/
        Button buttonAddWork = new Button("Ajouter une oeuvre");

        /**Création du bouton de validation de recherche*/
        Button buttonResearch = new Button("Rechercher");

        /**Création du bouton de validation de recherche*/
        Button buttonDeleteResearch = new Button("Annuler la recherche");


        /**Création des colonnes du tableau*/
        tableWork = new TreeTableView<>();
        tableWork.setShowRoot(false
        );
        final TreeTableColumn<Object, String> titleColumn = new TreeTableColumn<>("Titre");
        final TreeTableColumn<Object, String> authorColumn = new TreeTableColumn<>("Auteur");
        final TreeTableColumn<Object, String> dateColumn = new TreeTableColumn<>("Date de parution");
        final TreeTableColumn<Object, String> purchaseDate = new TreeTableColumn<>("Date d'achat");
        final TreeTableColumn<Object, String> hasBorrowedColumn = new TreeTableColumn<>("Disponible");


        /**Définit le remplissage des colonnes*/
        titleColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("author"));
        dateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
        purchaseDate.setCellValueFactory(new TreeItemPropertyValueFactory<>("purchaseDate"));
        hasBorrowedColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("isAvailable"));

        /** Définit l'affichage du tableau */
        tableWork.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        tableWork.getColumns().setAll(titleColumn, authorColumn, dateColumn,purchaseDate,hasBorrowedColumn);
        tableWork.setStyle("-fx-selection-bar: #b0e9ff;");



        updateList();

        vBoxWork.getChildren().addAll(buttonAddWork, buttonResearch, buttonDeleteResearch, tableWork);

        /*** TO DO ***/
        // Ajouter les fonctions des boutons
    }

    private void updateList() {
        ObservableList<Work> workList = FXCollections.observableArrayList(workController.getWorks());
        TreeItem<Object> main = new TreeItem<>();
        for(Work work : workList){
            TreeItem<Object> itemWork = new TreeItem<>(work);
            for(Book book : work.getBooks(workController.parser.bookList)){
                TreeItem<Object> itemBook = new TreeItem<>(book);
                itemWork.getChildren().add(itemBook);
            }

            main.getChildren().add(itemWork);

        }
        tableWork.setRoot(main);





    }

    public VBox getvBoxWork() {
        return vBoxWork;
    }
}

package view;

import controller.WorkController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Member;
import model.Work;

public class WorkTab {

    private static WorkController workController = new WorkController();
    public VBox vBoxWork;
    public static TableView<Work> tableWork;

    public WorkTab() {

        vBoxWork = new VBox();

        /**Création du bouton d'ajout d'oeuvre*/
        Button buttonAddWork = new Button("Ajouter une oeuvre");

        /**Création du bouton de validation de recherche*/
        Button buttonResearch = new Button("Rechercher");

        /**Création du bouton de validation de recherche*/
        Button buttonDeleteResearch = new Button("Annuler la recherche");


        /**Création des colonnes du tableau*/
        tableWork = new TableView();
        final TableColumn<Work, String> titleColumn = new TableColumn<>("Titre");
        final TableColumn<Work, String> authorColumn = new TableColumn<>("Auteur");
        final TableColumn<Work, String> dateColumn = new TableColumn<>("Date de parution");


        /**Définit le remplissage des colonnes*/
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        updateList();

        vBoxWork.getChildren().addAll(buttonAddWork, buttonResearch, buttonDeleteResearch, tableWork);

        /*** TO DO ***/
        // Ajouter les fonctions des boutons
    }

    private void updateList() {
        ObservableList<Work> workList = FXCollections.observableArrayList(workController.getWorks());
        tableWork.setItems(workList);
    }

    public VBox getvBoxWork() {
        return vBoxWork;
    }
}

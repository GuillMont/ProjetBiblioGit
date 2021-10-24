package view;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import com.sun.org.apache.xpath.internal.operations.Bool;
import controller.WorkController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Book;
import model.Member;
import model.Work;

import java.util.Observable;

public class WorkTab {

    private static WorkController workController;
    public VBox vBoxWork;
    public static TreeTableView<Object> tableWork;

    public WorkTab(WorkController workController) {
        vBoxWork = new VBox();
        this.workController = workController;

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
        final TreeTableColumn<Object, Boolean> hasBorrowedColumn = new TreeTableColumn<>("Disponible");
        final TreeTableColumn<Object, Void> pretColumn = new TreeTableColumn<>("Preter");

        /**Définit le remplissage des colonnes*/
        titleColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("author"));
        dateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
        purchaseDate.setCellValueFactory(new TreeItemPropertyValueFactory<>("purchaseDate"));
        hasBorrowedColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Object, Boolean>("isAvailable"));

        /** Définit l'affichage du tableau */
        tableWork.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        tableWork.getColumns().setAll(titleColumn, authorColumn, dateColumn, purchaseDate, hasBorrowedColumn, pretColumn);
        tableWork.setStyle("-fx-selection-bar: #b0e9ff;");

        Callback<TreeTableColumn<Object, Void>, TreeTableCell<Object, Void>> cellFactory = new Callback<TreeTableColumn<Object, Void>, TreeTableCell<Object, Void>>() {
            @Override
            public TreeTableCell<Object, Void> call(final TreeTableColumn<Object, Void> param) {
                final TreeTableCell<Object, Void> cell = new TreeTableCell<Object, Void>() {

                    private final Button btn = new Button("Preter");

                    {
                        btn.setOnAction((ActionEvent event) -> {
//
//                            Object data = getTreeTableView().getRoot().getParent().getChildren().get(getIndex());//getTableView().getItems().get(getIndex());
                            System.out.println(getIndex());
                            System.out.println(param.getCellObservableValue(getIndex())
                            );
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        pretColumn.setCellFactory(cellFactory);


            hasBorrowedColumn.setCellFactory(column -> {
            return new TreeTableCell<Object, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                                if (item == null)
                                    setStyle("");
                                else if (item.booleanValue() == false)
                                    setStyle("-fx-background-color: #ff3d35");
                                else
                                    setStyle("-fx-background-color: #49ff4f");

                }
            };
        });

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

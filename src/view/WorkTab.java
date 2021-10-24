package view;


import controller.PretController;
import controller.WorkController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Book;
import model.Work;


import java.util.Optional;

public class WorkTab {

    public static WorkController workController;
    public VBox vBoxWork;
    public static TreeTableView<Object> tableWork;

    public WorkTab(WorkController workController) {
        vBoxWork = new VBox();
        this.workController = workController;

        /**Création du bouton d'ajout d'oeuvre*/
        Button buttonAddWork = new Button("Ajouter une oeuvre");

        Button buttonAddBooks = new Button("Ajouter des livres");

        Button buttonPret = new Button("Pret");

        Button buttonRenduPret = new Button("Rendre un livre");

        /**Création des colonnes du tableau*/
        tableWork = new TreeTableView<>();
        tableWork.setShowRoot(false
        );
        final TreeTableColumn<Object, String> titleColumn = new TreeTableColumn<>("Titre");
        final TreeTableColumn<Object, String> authorColumn = new TreeTableColumn<>("Auteur");
        final TreeTableColumn<Object, String> dateColumn = new TreeTableColumn<>("Date de parution");
        final TreeTableColumn<Object, String> purchaseDate = new TreeTableColumn<>("Date d'achat");
        final TreeTableColumn<Object, Boolean> hasBorrowedColumn = new TreeTableColumn<>("Disponible");


        /**Définit le remplissage des colonnes*/
        titleColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("author"));
        dateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
        purchaseDate.setCellValueFactory(new TreeItemPropertyValueFactory<>("purchaseDate"));
        hasBorrowedColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Object, Boolean>("isAvailable"));

        /** Définit l'affichage du tableau */
        tableWork.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        tableWork.getColumns().setAll(titleColumn, authorColumn, dateColumn, purchaseDate, hasBorrowedColumn);
        tableWork.setStyle("-fx-selection-bar: #b0e9ff;");
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

        vBoxWork.getChildren().addAll(buttonAddWork, buttonAddBooks,buttonPret, buttonRenduPret, tableWork);
        updateList();

        /*** Faire un pret ***/
        buttonPret.setOnMouseClicked(e->{

            new PretController(workController.getAvailableBook(),workController.parser.memberList,this);
        });

        /*** Rendre un livre ***/
        buttonRenduPret.setOnMouseClicked(e->{
            new PretController(workController.getNotAvailableBook(),this);
        });

        /**Ajout de livres**/

        buttonAddBooks.setOnMouseClicked(e->{
            if(tableWork.getSelectionModel().getSelectedItem()==null){
               new Alert(Alert.AlertType.ERROR,"Vous devez sélectionner une oeuvre").show();
            }
            else{
                if(tableWork.getSelectionModel().getSelectedItem().getValue() instanceof Work){
                    Work work = (Work) tableWork.getSelectionModel().getSelectedItem().getValue();
                    TextInputDialog bookNumber = new TextInputDialog();
                    bookNumber.setHeaderText("Nombre de livres à ajouter:");
                    Optional<String> numberRead = bookNumber.showAndWait();

                    TextInputDialog bookDate = new TextInputDialog();
                    bookDate.setHeaderText("Date d'achat :");
                    Optional<String> dateRead = bookDate.showAndWait();

                    if (!numberRead.get().isEmpty() && !dateRead.get().isEmpty()) {
                        for(int i=0; i<Integer.parseInt(numberRead.get());i++){
                            this.workController.parser.bookList.add(new Book(workController.parser.lastWorkBook+1,dateRead.get(),false,work));
                            workController.parser.lastWorkBook++;
                        }
                        workController.parser.updateWorkXML(workController.getWorks());
                        updateList();
                    }
                }
                else  new Alert(Alert.AlertType.ERROR,"Vous devez sélectionner une oeuvre").show();

            }
        });

        /** Ajout d'un membre */
        buttonAddWork.setOnMouseClicked(e ->
        {
            TextInputDialog title = new TextInputDialog();
            title.setHeaderText("Entrez le nom du livre");
            Optional<String> titleRead = title.showAndWait();

            TextInputDialog author = new TextInputDialog();
            author.setHeaderText("Entrez un auteur.");
            Optional<String> authorRead = author.showAndWait();

            TextInputDialog date = new TextInputDialog();
            date.setHeaderText("Entrez la date de parution du livre.");
            Optional<String> dateRead = date.showAndWait();

            if (!titleRead.get().isEmpty() && !authorRead.get().isEmpty() && !dateRead.get().isEmpty()) {
                workController.addWork(new Work(titleRead.get(), authorRead.get(), dateRead.get()));
                updateList();
            }
        });


    }

    public void updateList() {
        System.out.println("Update list works");
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

    public WorkController getWorkController(){
        return workController;
    }
}

package view;

import controller.MemberController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Member;

import java.util.Optional;

public class MembersTab {

    private static MemberController memberController;
    public VBox vBoxAdherent;
    public static TableView<Member> tableAdherent;

    public MembersTab(MemberController memberController) {
        vBoxAdherent = new VBox(); //contient l'ensemble des objets de l'onglet (bouton/tableau)
        this.memberController = memberController;
        Button buttonAddAdherent = new Button("Ajouter un adherent");

        Button buttonRemoveAdherent = new Button("Supprimer un adhérent");

        /** Création des colonnes du tableau */
        tableAdherent = new TableView();
        final TableColumn<Member, String> firstNameColumn = new TableColumn<>("Prénom");
        final TableColumn<Member, String> lastNameColumn = new TableColumn<>("Nom");
        final TableColumn<Member, String> mailColumn = new TableColumn<>("Adresse");
        final TableColumn<Member, Boolean> hasBorrowedColumn = new TableColumn<>("Prêt en cours");
        final TableColumn<Member, ?> informationsAdherentColumn = new TableColumn<>("Informations");


        /** Définit le remplissage des colonnes */
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        hasBorrowedColumn.setCellValueFactory(new PropertyValueFactory<>("hasBorrowed"));

        updateList();

        /** Vert si l'adhérent a emprunté un livre
         ** Rouge sinon */
        hasBorrowedColumn.setCellFactory(column -> {
            return new TableCell<Member, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null)
                        setStyle("");
                    else if (item.booleanValue() == false)
                        setStyle("-fx-background-color: #ff3d35");
                    else{
                        System.out.println("set color member");
                        setStyle("-fx-background-color: #49ff4f");
                    }

                }
            };
        });


        /** Définit l'affichage du tableau */
        tableAdherent.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        informationsAdherentColumn.getColumns().setAll(firstNameColumn, lastNameColumn, mailColumn);
        tableAdherent.getColumns().setAll(informationsAdherentColumn,hasBorrowedColumn);
        tableAdherent.setStyle("-fx-selection-bar: #b0e9ff;");

        buttonRemoveAdherent.setOnMouseClicked(e->{
            if(tableAdherent.getSelectionModel().getSelectedCells().size()>0){
                System.out.println(tableAdherent.getSelectionModel().getSelectedCells().toString());
                Member member = tableAdherent.getSelectionModel().getSelectedItem();
                if(member.isHasBorrowed()){
                    new Alert(Alert.AlertType.ERROR,"Impossible de supprimer le membre : des prets sont en cours").show();
                }
                else{
                    memberController.getMembers().remove(member);
                    updateList();
                    memberController.getParser().getMemberList().remove(member);
                    memberController.getParser().updateMembreXML(memberController.getMembers());
                }
            }
        });

        vBoxAdherent.getChildren().addAll(buttonAddAdherent,buttonRemoveAdherent, tableAdherent);

        /** Ajout d'un membre */
        buttonAddAdherent.setOnMouseClicked(e ->
        {
            TextInputDialog prenom = new TextInputDialog();
            prenom.setHeaderText("Entrez un prenom.");
            Optional<String> prenomLu = prenom.showAndWait();

            if (!prenomLu.get().isEmpty()) {
                TextInputDialog nom = new TextInputDialog();
                nom.setHeaderText("Entrez un nom.");
                Optional<String> nomLu = nom.showAndWait();
                if (!nomLu.get().isEmpty()) {
                    TextInputDialog adresse = new TextInputDialog();
                    adresse.setHeaderText("Entrez l'adresse.");
                    Optional<String> adresseLue = adresse.showAndWait();
                    memberController.addMember(new Member(memberController.getParser().lastMember,prenomLu.get(), nomLu.get(), adresseLue.get()));
                    System.out.println("last member : " + memberController.getParser().lastMember);
                    memberController.getParser().lastMember++;
                    updateList();
                    }
                }
        });
    }

    public static void updateList(){
        System.out.println("Update list members");
        ObservableList<Member> membersList = FXCollections.observableArrayList(memberController.getMembers());
        System.out.println(membersList);
        tableAdherent.setItems(membersList);
    }

    public VBox getvBoxAdherent() {
        return vBoxAdherent;
    }


}

import controller.MemberController;
import controller.WorkController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Work;
import view.MembersTab;
import view.WorkTab;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        /** Test controller */
        MemberController memberController = new MemberController();
        System.out.println(memberController.getMembers().size());

        WorkController workController = new WorkController();
        System.out.println("Nombre d'oeuvres : " + workController.getWorks().size());

        /*** Onglet des Oeuvres ***/
        WorkTab workTab = new WorkTab();
        VBox vBoxWork = workTab.getvBoxWork();

        /*** Onglet des Membres ***/
        MembersTab membersTab = new MembersTab();
        VBox vBoxMembre = membersTab.getvBoxAdherent();

        /*** Ensemble des onglets ***/
        final TitledPane workPane = new TitledPane("Oeuvres", vBoxWork);
        final TitledPane memberPane = new TitledPane("Membre", vBoxMembre);
        final Accordion accordion = new Accordion();
        accordion.getPanes().setAll(workPane, memberPane);
        accordion.setExpandedPane(workPane);


        final StackPane root = new StackPane();
        root.getChildren().add(accordion);
        primaryStage.setTitle("Bibliothèque");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}

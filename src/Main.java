import controller.MemberController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.MembersTab;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        /** Test controller */
        MemberController memberController = new MemberController();
        System.out.println(memberController.getMembers().size());


        final StackPane root = new StackPane();
        root.getChildren().add(new MembersTab().getvBoxAdherent());
        primaryStage.setTitle("Bibliothèque");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}

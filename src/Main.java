import controller.MemberController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        final StackPane root = new StackPane();
        primaryStage.setTitle("Hello World");
        MemberController memberController = new MemberController();
        System.out.println(memberController.getMembers());
        memberController.populate();
        System.out.println(memberController.getMembers());



        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}

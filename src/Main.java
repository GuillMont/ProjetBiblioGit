import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.AllTabs;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage){
        AllTabs allTabs = new AllTabs();
        final StackPane root = new StackPane();
        root.getChildren().add(allTabs.getAccordion());
        primaryStage.setTitle("Bibliothèque");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}

import controller.MemberController;
import controller.Parser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import view.MembersTab;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        /** Test controller */
        MemberController memberController = new MemberController();
        System.out.println(memberController.getMembers().size());
        try {
            new Parser().parseWorks();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

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

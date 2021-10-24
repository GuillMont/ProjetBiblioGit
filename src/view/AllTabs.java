package view;

import controller.MemberController;
import controller.WorkController;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class AllTabs {

    public final Accordion accordion = new Accordion();

    public AllTabs(){
        init();
    }

    public void init() {
        /** Parser XML **/
        MemberController memberController = new MemberController();
        WorkController workController = new WorkController();

        /*** Onglet des oeuvres ***/
        WorkTab workTab = new WorkTab(workController);
        VBox vBoxWork = workTab.getvBoxWork();

        /*** Onglet des membres ***/
        MembersTab membersTab = new MembersTab(memberController);
        VBox vBoxMembre = membersTab.getvBoxAdherent();

        /*** Ensemble des onglets ***/
        final TitledPane workPane = new TitledPane("Oeuvres", vBoxWork);
        final TitledPane memberPane = new TitledPane("Membre", vBoxMembre);
        accordion.getPanes().setAll(workPane, memberPane);
        accordion.setExpandedPane(workPane);
    }

    public Accordion getAccordion() {
        return accordion;
    }
}

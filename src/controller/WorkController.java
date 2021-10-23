package controller;

import model.Work;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkController {

    private List<Work> works;
    public Parser parser = new Parser();

    public WorkController() {
        works = new ArrayList<>();
        populate();

    }

    public void populate(){

     //   Parser.workList;
        Work HP1 = new Work("Harry Potter à l'école des sorciers", "J. K. Rowling", "1997");
        Work HP2 = new Work("Harry Potter et la chambre des secrets", "J. K. Rowling", "1999");
        Work HP3 = new Work("Harry Potter et le prisonnier d'Askaban", "J. K. Rowling", "1999");
        Work HP4 = new Work("Harry Potter et la Coupe de feu", "J. K. Rowling", "2000");
        Work HP5 = new Work("Harry Potter et l'Ordre du Phénix ", "J. K. Rowling", "2003");
        Work HP6 = new Work("Harry Potter et le Prince de sang-mêlé", "J. K. Rowling", "2005");
      //  works.addAll(Arrays.asList(HP1, HP2, HP3, HP4, HP5, HP6));
        works.addAll(parser.workList);
    }

    /*** Getter & Setter ***/
    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}

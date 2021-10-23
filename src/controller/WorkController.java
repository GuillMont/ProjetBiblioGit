package controller;

import model.Work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkController {

    private List<Work> works;

    public WorkController() {
        works = new ArrayList<>();
        populate();
    }

    public void populate(){
        Work HP1 = new Work("Harry Potter à l'école des sorciers", "J. K. Rowling", "1997");
        Work HP2 = new Work("Harry Potter et la chambre des secrets", "J. K. Rowling", "1999");
        Work HP3 = new Work("Harry Potter et le prisonnier d'Askaban", "J. K. Rowling", "1999");
        works.addAll(Arrays.asList(HP1, HP2, HP3));
    }

    /*** Getter & Setter ***/
    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}

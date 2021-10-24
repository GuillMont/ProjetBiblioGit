package controller;

import model.Work;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkController {

    private List<Work> works;
    public Parser parser;

    public WorkController() {
        works = new ArrayList<>();
        parser = new Parser();
        populate();
    }

    public void populate(){
        works.addAll(parser.workList);
    }

    /*** Getter & Setter ***/
    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

}

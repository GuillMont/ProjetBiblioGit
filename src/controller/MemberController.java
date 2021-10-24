package controller;

import model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
    List<Member> members;
    Parser parser;

    //constructeur utile pour choisir le parser, et par extension, les données à manipuler (dans le cadre de tests par exemple)
    public MemberController(Parser parser){
        members = new ArrayList<>();
        this.parser = parser;
        populate();
    }

    public List<Member> getMembers(){
        return members;
    }

    public void populate(){
        members.addAll(parser.memberList);
    }

    public void addMember(Member member){
        members.add(member);
        parser.updateMembreXML(members);
    }

    public void removeMember(Member member){
        members.remove(member);
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

}
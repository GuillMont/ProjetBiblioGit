package controller;

import model.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemberController {

    List<Member> members;
    Parser parser;

    public MemberController(){
        members = new ArrayList<>();
        populate();
    }

    public List<Member> getMembers(){
        return members;
    }

    public void populate(){

        Member member1 = new Member("Jean-Luc", "Massat", "jean-luc.massat@univ-amu.fr");
        Member member2 = new Member("Nicolas", "Hoarau", "nicolas-hoarau@univ-amu.fr");
        Member member3 = new Member("Noel", "Novelli", "noel-novelli@univ-amu.fr");
        members.addAll(Arrays.asList(member1, member2, member3));
    }

    public void addMember(Member member){
        members.add(member);
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

package controller;

import model.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemberController {

    List<Member> members;

    public MemberController(){
        members = new ArrayList<>();
    }

    public List<Member> getMembers(){
        return members;
    }

    public void populate(){
        Member member1 = new Member(1000, "Jean-Luc", "Massat", "jean-luc.massat@univ-amu.fr");
        Member member2 = new Member(2000, "Nicolas", "Hoarau", "nicolas-hoarau@univ-amu.fr");
        Member member3 = new Member(3000, "Noel", "Novelli", "nicolas-hoarau@univ-amu.fr");
        members.addAll(Arrays.asList(member1, member2, member3));
    }

    public void addMember(Member member){
        members.add(member);
    }

    public void removeMember(Member member){
        members.remove(member);
    }


}

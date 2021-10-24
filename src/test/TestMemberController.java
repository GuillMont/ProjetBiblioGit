package test;

import controller.MemberController;
import controller.Parser;
import model.Member;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import static org.junit.Assert.*;

public class TestMemberController {

    String dataWorkAndBook_pathName = "src/datas/testWorkAndBook.xml"; //contient deux oeuvres en, respectivement, trois et deux exemplaires (livres)
    String dataMember_pathName = "src/datas/testMember.xml"; //contient trois membres
    Parser parser = new Parser(dataWorkAndBook_pathName, dataMember_pathName);
    MemberController memberController = new MemberController(parser);


    @Test
    public void testGetMembers(){
        assertEquals(3, memberController.getMembers().size());
    }

    /*
    @Test
    public void testAddMember(){
        memberController.addMember(new Member("firstName", "lastName", "mail"));
        assertEquals(4, memberController.getMembers().size());
    }
    */
}

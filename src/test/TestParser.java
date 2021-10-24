package test;

import controller.MemberController;
import controller.Parser;
import controller.WorkController;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;


public class TestParser {

    String dataWorkAndBook_pathName = "src/datas/testWorkAndBook.xml"; //contient deux oeuvres en, respectivement, trois et deux exemplaires (livres)
    String dataMember_pathName = "src/datas/testMember.xml"; //contient trois membres
    Parser parser = new Parser(dataWorkAndBook_pathName, dataMember_pathName);

    @Test
    public void testMemberParser(){
        assertEquals(3, parser.getMemberList().size());
        System.out.println(parser.getMemberList());
    }

    @Test
    public void testWorkParser() {
        assertEquals(2, parser.getWorkList().size());
        System.out.println(parser.getWorkList());
    }

    @Test
    public void testBookParser() {
        assertEquals(5, parser.getBookList().size());
        System.out.println(parser.getBookList());
    }



}

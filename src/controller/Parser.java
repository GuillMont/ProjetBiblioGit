package controller;

import model.Book;
import model.Member;
import model.Work;
import org.junit.platform.engine.support.descriptor.FileSystemSource;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Work> workList = new ArrayList<>();
    public static List<Book> bookList = new ArrayList<>();
    public static List<Member> memberList = new ArrayList<>();
    Document document;
    String dataWorkAndBook_pathName;
    String dataMember_pathName;
   public int lastWorkBook = 0;
   public int lastMember =0;

   public static void setBookList(List<Book> books){
        bookList=books;
   }

    public static void setMemberList(List<Member> members){
        memberList=members;
    }

    public Parser(){
        dataWorkAndBook_pathName = "src/datas/workAndBook.xml";
        dataMember_pathName = "src/datas/member.xml";
        try {
            parseWorks(dataWorkAndBook_pathName);
            parserMember(dataMember_pathName);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public Parser(String dataWorkAndBook_pathName, String dataMember_pathName){
        this.dataWorkAndBook_pathName = dataWorkAndBook_pathName;
        this.dataMember_pathName = dataMember_pathName;
        try {
            parseWorks(dataWorkAndBook_pathName);
            parserMember(dataMember_pathName);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void parseWorks(String pathname) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        document = builder.parse(new File(pathname));

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String title = elem.getElementsByTagName("Title")
                        .item(0).getChildNodes().item(0).getNodeValue();

                // Get the value of all sub-elements.
                String author = elem.getElementsByTagName("Author")
                        .item(0).getChildNodes().item(0).getNodeValue();

                String date = elem.getElementsByTagName("Date").item(0)
                        .getChildNodes().item(0).getNodeValue();
                Work work = new Work(title, author, date);
                workList.add(work);
                this.parserBook(work,elem);
            }

        }
    }

    public void parserBook(Work work, Element elem){
        Node booksElement = elem.getElementsByTagName("Books").item(0);

        Element element = (Element) booksElement;

        for(int i=0;i<element.getElementsByTagName("Book").getLength();i++){
            Node bookNode = element.getElementsByTagName("Book").item(i);
            Element bookElement = (Element) bookNode;

            int id= Integer.parseInt(bookElement.getAttribute("id"));
            this.lastWorkBook=id;
            String purchaseDate = bookElement.getElementsByTagName("PurchaseDate").item(0).getChildNodes().item(0).getNodeValue();
            boolean isAvailable = Boolean.parseBoolean(bookElement.getElementsByTagName("IsAvailable").item(0).getChildNodes().item(0).getNodeValue());

            Book book = new Book(id,purchaseDate,isAvailable,work);
            this.bookList.add(book);

        }
    }

    public void parserMember(String pathname) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        document = builder.parse(new File(pathname));

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                int id = Integer.parseInt(elem.getAttribute("id"));

                String firstName = elem.getElementsByTagName("FirstName")
                        .item(0).getChildNodes().item(0).getNodeValue();

                // Get the value of all sub-elements.
                String lastName = elem.getElementsByTagName("LastName")
                        .item(0).getChildNodes().item(0).getNodeValue();

                String mail = elem.getElementsByTagName("Mail").item(0)
                        .getChildNodes().item(0).getNodeValue();
                Member member = new Member(id,firstName, lastName, mail);

                Node booksElement = elem.getElementsByTagName("Books").item(0);

                Element element = (Element) booksElement;

                for(int j=0;j<element.getElementsByTagName("Book").getLength();j++){
                    Node bookNode = element.getElementsByTagName("Book").item(j);
                    Element bookElement = (Element) bookNode;

                    int idBook= Integer.parseInt(bookElement.getAttribute("id"));

                    Book book=null
                            ;
                    for(Book books : bookList){
                        if(books.getId()==Integer.parseInt(String.valueOf(idBook))){
                            book = books;
                            books.setAvailable(false);
                            member.getBorrowedBooks().add(book);
                            member.setHasBorrowed(true);
                        }


                    }


                    System.out.println(member.getBorrowedBooks());
                }
                memberList.add(member);
            }
        }
    }

    public void updateMembreXML(List<Member> membres){
        try {
            int count=0;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root elements
            Document doc = docBuilder.newDocument();
            doc.setXmlVersion("1.0");
            Element rootElement = doc.createElement("Membres");
            doc.appendChild(rootElement);

            for(Member member: membres){
                Element utilisateur = doc.createElement("Membre");
                utilisateur.setAttribute("id", String.valueOf(count));
                Element firstName = doc.createElement("FirstName");
                firstName.appendChild(doc.createTextNode(member.getFirstName()));
                utilisateur.appendChild(firstName);

                Element lastName = doc.createElement("LastName");
                lastName.appendChild(doc.createTextNode(member.getLastName()));
                utilisateur.appendChild(lastName);

                Element mail = doc.createElement("Mail");
                mail.appendChild(doc.createTextNode(String.valueOf(member.getMail())));
                utilisateur.appendChild(mail);
                rootElement.appendChild(utilisateur);
                count++;
                this.lastMember=count;
                Element donnees = doc.createElement("Books");

                System.out.println(member.getBorrowedBooks());
                for( Book book : member.getBorrowedBooks()){
                    //if(book == counterUser){
                        Element donnee = doc.createElement("Book");
                        donnee.setAttribute("id",String.valueOf(book.id));
                        donnees.appendChild(donnee);

                        donnees.appendChild(donnee);
                   // }
                }
              //  counterUser++;
                utilisateur.appendChild(donnees);

            }

            TransformerFactory transformerFactory =  TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);

            StreamResult result =  new StreamResult(new File(dataMember_pathName));
            transformer.transform(source, result);


        }catch(ParserConfigurationException | TransformerException pce){
            pce.printStackTrace();
        }
    }

    public void updateWorkXML(List<Work> works){
        try {
            int countWork = 1;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root elements
            Document doc = docBuilder.newDocument();
            doc.setXmlVersion("1.0");
            Element rootElement = doc.createElement("Works");
            doc.appendChild(rootElement);

            for(Work work: works){
                Element workBalise = doc.createElement("Work");
                workBalise.setAttribute("id", countWork + "");

                Element author = doc.createElement("Author");
                author.appendChild(doc.createTextNode(work.getAuthor()));
                workBalise.appendChild(author);

                Element title = doc.createElement("Title");
                title.appendChild(doc.createTextNode(work.getTitle()));
                workBalise.appendChild(title);

                Element date = doc.createElement("Date");
                date.appendChild(doc.createTextNode(String.valueOf(work.getDate())));
                workBalise.appendChild(date);
                Element booksBalise = doc.createElement("Books");
                countWork ++;
                /*** Remplir les infos des livres quand on aura implémenté la fonction d'ajouter un livre a une oeuvre ***/
                for(Book book : work.getBooks(this.bookList)){
                    Element bookElement = doc.createElement("Book");
                    bookElement.setAttribute("id", countWork + "");
                    booksBalise.appendChild(bookElement);

                    Element purchaseDate = doc.createElement("PurchaseDate");
                    purchaseDate.appendChild(doc.createTextNode(book.purchaseDate));
                    bookElement.appendChild(purchaseDate);

                    Element isAvailable = doc.createElement("IsAvailable");
                    isAvailable.appendChild(doc.createTextNode(String.valueOf(book.isAvailable)));
                    bookElement.appendChild(isAvailable);
                    booksBalise.appendChild(bookElement);
                    countWork ++;
                }
                workBalise.appendChild(booksBalise);
                rootElement.appendChild(workBalise);
                this.lastWorkBook=countWork;



            }

            TransformerFactory transformerFactory =  TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);

            StreamResult result =  new StreamResult(new File(dataWorkAndBook_pathName));
            transformer.transform(source, result);


        }catch(ParserConfigurationException | TransformerException pce){
            pce.printStackTrace();
        }
    }

    public List<Work> getWorkList() {
        return workList;
    }

    public static List<Book> getBookList() {
        return bookList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public static void setBook(Book book){
      for(Book books : bookList){
        if(book.getId()==books.getId()){
            System.out.print("SET BOOK : " + book.isIsAvailable() + " TO " + book);
            books.setAvailable(false);
            System.out.print("SET BOOK : " + book.isIsAvailable() + " TO " + book);
        }

      }

    }

    public static void setBookAvailable(Book book){
        for(Book books : bookList){
            if(book.getId()==books.getId()){
                System.out.println("SET BOOK : " + book.isIsAvailable() + " TO " + book);
                books.setAvailable(true);
                System.out.println("SET BOOK : " + book.isIsAvailable() + " TO " + book);
            }

        }

    }
}

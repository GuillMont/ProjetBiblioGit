package controller;

import model.Book;
import model.Member;
import model.Work;
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

    public List<Work> workList = new ArrayList<Work>();
    public List<Book> bookList = new ArrayList<Book>();
    public List<Member> memberList = new ArrayList<>();
    Document document;

    public Parser(){
        try {
            parseWorks();
            parserMembre();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void parseWorks() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        document = builder.parse(new File("src/datas/workAndBook.xml"));

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

        }/*
        for(Book book : this.livres)
            System.out.println(book.toString());
        System.out.println(livres.size());*/
    }

    public void parserBook(Work work, Element elem){
        Node booksElement = elem.getElementsByTagName("Books").item(0);

        Element element = (Element) booksElement;

        for(int i=0;i<element.getElementsByTagName("Book").getLength();i++){
            Node bookNode = element.getElementsByTagName("Book").item(i);
            Element bookElement = (Element) bookNode;

            int id= Integer.parseInt(bookElement.getAttribute("id"));
            String purchaseDate = bookElement.getElementsByTagName("PurchaseDate").item(0).getChildNodes().item(0).getNodeValue();
            boolean isAvailable = Boolean.parseBoolean(bookElement.getElementsByTagName("IsAvailable").item(0).getChildNodes().item(0).getNodeValue());

            Book book = new Book(id,purchaseDate,isAvailable,work);
            this.bookList.add(book);

        }
    }

    public void parserMembre() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        document = builder.parse(new File("src/datas/membre.xml"));

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String firstName = elem.getElementsByTagName("FirstName")
                        .item(0).getChildNodes().item(0).getNodeValue();

                // Get the value of all sub-elements.
                String lastName = elem.getElementsByTagName("LastName")
                        .item(0).getChildNodes().item(0).getNodeValue();

                String mail = elem.getElementsByTagName("Mail").item(0)
                        .getChildNodes().item(0).getNodeValue();
                Member member = new Member(firstName, lastName, mail);
                memberList.add(member);
            }

        }
        for(Member member : memberList){
            System.out.println(member.toString());
        }
    }

    public void updateMembreXML(List<Member> membres){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root elements
            Document doc = docBuilder.newDocument();
            doc.setXmlVersion("1.0");
            Element rootElement = doc.createElement("Membres");
            doc.appendChild(rootElement);

            for(Member member: membres){
                Element utilisateur = doc.createElement("Membre");

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
            }

            TransformerFactory transformerFactory =  TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);

            StreamResult result =  new StreamResult(new File("src/datas/membre.xml"));
            transformer.transform(source, result);


        }catch(ParserConfigurationException | TransformerException pce){
            pce.printStackTrace();
        }
    }
}

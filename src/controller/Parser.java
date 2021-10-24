package controller;

import model.Book;
import model.Work;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Work> workList = new ArrayList<Work>();
    public  List<Book> livres = new ArrayList<Book>();
    Document document;

    public Parser(){
        try {
            parseWorks();
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
            this.livres.add(book);

        }
    }
}

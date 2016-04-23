/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author tomas
 */
public class XmlParser {
    
    private File parsingFile;
    
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;    
    
    private ArrayList<DBConnection> connectionsArrayList;

    public XmlParser(File parsingFile) throws SAXException, IOException, ParserConfigurationException {
        this.parsingFile = parsingFile;
        
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        
        createDocument();
    }
    
    public void reload(){
        createDocument();
    }
    
    private void createDocument() {
        Document doc = null;
        try {
            doc = dBuilder.parse(parsingFile);            
            doc.getDocumentElement().normalize();
            
            connectionsArrayList = new ArrayList();
            Element connectionsRoot = (Element) doc.getElementsByTagName("connections").item(0);  
            
            if(connectionsRoot != null){
                NodeList connections = connectionsRoot.getElementsByTagName("db_connection");
            
                for(int index = 0; index < connections.getLength(); index++){
                    Node nNode = connections.item(index);

                    if(nNode.getNodeType() == Node.ELEMENT_NODE){

                        Element eElement = (Element) nNode;
                        String id = eElement.getAttribute("id");
                        String url = eElement.getElementsByTagName("url").item(0).getTextContent();
                        String database = eElement.getElementsByTagName("database").item(0).getTextContent();
                        String user = eElement.getElementsByTagName("user").item(0).getTextContent();
                        String pass = eElement.getElementsByTagName("pass").item(0).getTextContent();
                        String active = eElement.getElementsByTagName("active").item(0).getTextContent();
                        
                        DBConnection tmpConn;
                        if(active.equals("true")){
                            tmpConn = new DBConnection(id, url, database, user,true);
                        }
                        else{
                            tmpConn = new DBConnection(id, url, database, user);
                        }            
                        
                        if(!pass.equals("-")){
                            tmpConn.setPassword(pass);
                        }
                        connectionsArrayList.add(tmpConn);
                    }
                }
            }
            
            
        } catch (SAXException | IOException ex) {            
            ex.printStackTrace();        
        }
        
    }
    
    public boolean dataElementExists(String name){
        try {
            Document doc = dBuilder.parse(parsingFile);
            doc.getDocumentElement().normalize();
            Element parentElement = (Element) doc.getElementsByTagName(name).item(0);
            
            if(parentElement != null){
                return true;
            }
            else{
                return false;
            }
            
        } catch (SAXException | IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public void insertDataElement(String parent, String elName){
        try {
            
            Document doc = dBuilder.parse(parsingFile);
            doc.getDocumentElement().normalize();
            
            Element parentElement = (Element) doc.getElementsByTagName(parent).item(0);
            
            if(parentElement != null){
                Element newElement = doc.createElement(elName);
                parentElement.appendChild(newElement);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(parsingFile);
            transformer.transform(source, result);
            
        } catch (SAXException | IOException | TransformerException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertDataElement(String parent, String elName, String id){
        try {
            
            Document doc = dBuilder.parse(parsingFile);
            doc.getDocumentElement().normalize();
            
            Element parentElement = (Element) doc.getElementsByTagName(parent).item(0);
            
            if(parentElement != null){
                Element newElement = doc.createElement(elName);
                newElement.setAttribute("id", id);
                parentElement.appendChild(newElement);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(parsingFile);
            transformer.transform(source, result);
            
        } catch (SAXException | IOException | TransformerException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertValueElement(String parent, String elName, String value){
        try {
            
            Document doc = dBuilder.parse(parsingFile);
            doc.getDocumentElement().normalize();
            
            Element parentElement = (Element) doc.getElementsByTagName(parent).item(0);
            
            if(parentElement != null){
                Element newElement = doc.createElement(elName);
                Text valueElement = doc.createTextNode(value);
                newElement.appendChild(valueElement);
                parentElement.appendChild(newElement);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(parsingFile);
            transformer.transform(source, result);
            
        } catch (SAXException | IOException | TransformerException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertValueElement(String parent, String id, String elName, String value){
        try {
            
            Document doc = dBuilder.parse(parsingFile);
            doc.getDocumentElement().normalize();
            
            NodeList parentsList = doc.getElementsByTagName(parent);
            if(parentsList != null){
                
                for(int index = 0; index < parentsList.getLength(); index++){
                    Node nNode = parentsList.item(index);
                    
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){
                        Element tmpElement = (Element) nNode;
                        
                        if(tmpElement.getAttribute("id").equals(id)){                            
                            Element newElement = doc.createElement(elName);
                            Text valueElement = doc.createTextNode(value);
                            newElement.appendChild(valueElement);
                            tmpElement.appendChild(newElement);
                            break;                            
                        }
                        
                    }
                    
                }
                
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(parsingFile);
            transformer.transform(source, result);
            
        } catch (SAXException | IOException | TransformerException ex) {
            ex.printStackTrace();
        }
    }

    public void changeElementValue(String parent, String elName, String elementOfValue, String newValue){
        try {
            
            Document doc = dBuilder.parse(parsingFile);
            doc.getDocumentElement().normalize();
            
            Element parentElement = (Element) doc.getElementsByTagName(parent).item(0);
            
            if(parentElement != null){
                
                NodeList elements = parentElement.getElementsByTagName(elName);
                for(int index = 0; index < elements.getLength(); index++){
                    Node nNode = elements.item(index);
                    
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){
                        
                        Element dataElement = (Element) nNode;
                        Element valueElement = (Element) dataElement.getElementsByTagName(elementOfValue).item(0);
                        if(valueElement != null){
                            valueElement.setTextContent(newValue);
                        }                        
                        
                    }
                }
                
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(parsingFile);
            transformer.transform(source, result);
            
        } catch (SAXException | IOException | TransformerException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<DBConnection> getConnectionsArrayList() {
        return connectionsArrayList;
    }
    
    public static void createFile(File file) throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  
        Document doc = dBuilder.newDocument();
        Element rootElement = doc.createElement("settings");
        doc.appendChild(rootElement);
                
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }
}

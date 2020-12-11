package parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xpath.classes.*;
import xpath.constants.XPathConstants;

import java.util.List;

public class ElementParse {

    public static void match(Element element, Person person) {

        String tagName = element.getTagName();
        switch (tagName) {

            case XPathConstants.NAME:
                Name.convert(element,person);
                break;

            case XPathConstants.PORTRAIT_IMAGE:
                person.setPortrait_image(Portrait.convert(element));
                break;

            case XPathConstants.QUALIFICATIONS:
                person.setQualifications(Qualifications.convert(element));
                break;
            case XPathConstants.NATIONALITY:
                person.setNationality(Qualifications.convert(element));
                break;
            case XPathConstants.PRESENTPOSITION:
                person.setPresentPosition(Qualifications.convert(element));
                break;
            case XPathConstants.NAMEATBIRTH:
                person.setNameAtBirth(Qualifications.convert(element));
                break;
            case XPathConstants.DATEOFBIRTH:
                person.setDateOfBirth(Qualifications.convert(element));
                break;
            case XPathConstants.PLACEOFBIRTH:
                person.setPlaceOfbirth(Qualifications.convert(element));
                break;
            case XPathConstants.DATEOFDEATH:
                person.setDateOfDeath(Qualifications.convert(element));
                break;
            case XPathConstants.PARENTAGE:
                person.setParentage(Qualifications.convert(element));
                break;
            case XPathConstants.IMMEDIATEFAMILY:
            case XPathConstants.EXTENDEDFAMILY:
                person.setFamily(Qualifications.convert(element));
                break;

            case XPathConstants.EDUCATION:
                person.setEducation(Qualifications.convert(element));
                break;
            case XPathConstants.CAREERPARA:
                person.setCareer(Qualifications.convert(element));
                break;
            case XPathConstants.HONOURSAWARDS:
                person.setHonourAndAwards(Qualifications.convert(element));
                break;


            case XPathConstants.PROFESSION:
                person.getProfession().add(element.getTextContent());
                break;

            case XPathConstants.FILMS:
                person.setFilms(Films.convert(element));
                break;
            case XPathConstants.PLAYS:
                person.setPlays(Films.convert(element));
                break;
            case XPathConstants.TV:
                person.setTv(Films.convert(element));
                break;
            case XPathConstants.MUSIC:
                person.setMusic(Films.convert(element));
                break;
            case XPathConstants.DANCE:
                person.setDance(Films.convert(element));
                break;
            case XPathConstants.ARTEXHIBITION:
                person.setArtExhibition(Films.convert(element));
                break;
            case XPathConstants.RADIO:
                person.setRadio(Films.convert(element));
                break;
            case XPathConstants.ACHIEVEMENTS:
                person.setAchievements(Films.convert(element));
                break;
            case XPathConstants.PUBLICATIONS:
                person.setPublications(Films.convert(element));
                break;
            case XPathConstants.LEISUREINTERESTS:
                person.setLeisure_interests(Films.convert(element));
                break;


            case XPathConstants.CONTACTDETAILS:
                List <String> stringList = ContactDetails.convert(element);
                if (stringList.get(0).equalsIgnoreCase("PUBLIC")) {
                    person.setContact_details(stringList.get(1));
                }
                if (stringList.get(0).equalsIgnoreCase("MANAGEMENT")) {
                    person.setManagement(stringList.get(1));
                }
                break;
            default:
                break;

        }
    }

    public static Person parseFiles(Document doc) {

        doc.getDocumentElement().normalize();
        Node entryNode =doc.getDocumentElement();
        NodeList nodeList = entryNode.getChildNodes();

        Element entryElement = (Element) entryNode;

        Person person = new Person();

        if (entryElement.hasAttribute("DEAD")) {
            String deadVar = entryElement.getAttribute("DEAD");
            person.setDead(Integer.parseInt(deadVar));
        }

        if (entryElement.hasAttribute("GENDER")) {
            person.setGender(entryElement.getAttribute("GENDER"));
        }

        for (int i=0; i<nodeList.getLength();i++) {
            Node nNode = nodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)nNode;
                match(element,person);
            }
        }
        return person;

    }

}

package xpath.classes;

import org.w3c.dom.Element;
import pojo.CrossRefEntry;
import pojo.Person;

public class CrossRefEntryParser {

    public static void convert(Element element, CrossRefEntry crossRefEntry, Person person) {

        if (person.getFirstname() != null) {
            crossRefEntry.setXml_firstname(person.getFirstname());
        }
        if (person.getSurname() != null) {
            crossRefEntry.setXml_surname(person.getSurname());
        }
        if (person.getNobility() != null) {
            crossRefEntry.setXml_nobility(person.getNobility());
        }
        if (person.getTitle() !=null) {
            crossRefEntry.setXml_title(person.getTitle());
        }
        if (person.getPseudonym() !=null) {
            crossRefEntry.setXml_pseudonym(person.getPseudonym());
        }

        crossRefEntry.setReferred_name(element.getTextContent());
        if (element.hasAttribute("SLTARGETID")) {
            crossRefEntry.setReferred_id(element.getAttribute("SLTARGETID"));
        }
    }
}

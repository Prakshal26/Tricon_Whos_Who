package xpath.classes;

import org.w3c.dom.Element;

import java.util.List;

public class Portrait {

    public static String convert(Element element){

        StringBuilder stringBuilder = new StringBuilder();
        if (element.hasAttribute("NAME")) {
            stringBuilder.append("<img src = \"https://www.worldwhoswho.com/portrait/");
            stringBuilder.append(element.getAttribute("NAME").toLowerCase());
            stringBuilder.append(".jpg\"/>");
        }
        return stringBuilder.toString();
    }
}

package string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpTest {

    public static void main(String[] args) {

        String str = "北京市(朝阳区)(西城区)(海淀区)";

        Pattern p = Pattern.compile(".*?(?=\\()");

        Matcher m = p.matcher(str);

        if(m.find()) {

            System.out.println(m.group());

        }

        str.matches("");
        str.replaceAll("", "");
        str.replaceFirst("", "");
        str.split("");
    }

}
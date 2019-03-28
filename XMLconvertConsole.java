package zz.lechi.xml;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class XMLconvertConsole {

    private static String str = "<";

    private static void parse(Element element){
        Iterator iterator = element.elementIterator() ;

        while (iterator.hasNext()){
            str = " " + str ;
            Element ele = (Element) iterator.next() ;
            System.out.print(str + ele.getQualifiedName());
            List listAttr = ele.attributes() ;
            for(int i = 0 ; i < listAttr.size() ; i ++){
                Attribute attr = (Attribute) listAttr.get(i) ;
                System.out.print(" " + attr.getName() + "=\"" + attr.getValue() + "\"");
            }
            if (ele.getText().equals("")){
                System.out.println(" />") ;
            } else {
                System.out.println(">" + ele.getTextTrim() + "</" + ele.getQualifiedName() +">") ;
            }
            parse(ele);
            str = "<" ;
        }
    }

    // 入口
    public static void convert(String fileName){
        try {
            Document doc = new SAXReader().read(new File(fileName));
            Element root = doc.getRootElement() ;
            parse(root);
        }catch (DocumentException e){
            e.printStackTrace();
        }
    }

}

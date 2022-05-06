package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "Object")
@XmlAccessorType(XmlAccessType.FIELD)
public class Object {
    @XmlAttribute
    private int number = 3;

    @XmlAttribute
    private boolean cond = true;

    @XmlAttribute
    private String str = "privet";

    private SimpleObject simpleObject = new SimpleObject(str);
    private int[] numbers = {1, 2, 3};

    public Object() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Object{");
        sb.append("number=").append(number);
        sb.append(", cond=").append(cond);
        sb.append(", str='").append(str).append('\'');
        sb.append(", simpleObject=").append(simpleObject);
        sb.append(", numbers=").append(Arrays.toString(numbers));
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Object object = new Object();
        JAXBContext context = JAXBContext.newInstance(Object.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(object, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Object result = (Object) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}

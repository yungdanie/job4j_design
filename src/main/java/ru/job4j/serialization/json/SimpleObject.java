package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SimpleObject")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleObject {
    @XmlAttribute
    private String str;

    public SimpleObject() {
    }

    public SimpleObject(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SimpleObject{");
        sb.append("str='").append(str).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

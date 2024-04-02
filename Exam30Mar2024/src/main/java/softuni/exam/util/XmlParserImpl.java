package softuni.exam.util;

import org.springframework.stereotype.Component;


import javax.xml.bind.JAXBException;

@Component
public class XmlParserImpl implements XmlParser {

    @Override
    public <T> T fromFile(String filePath, Class<T> tClass) throws JAXBException {
        return null;
    }
}



package org.example.lab1;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XmlSerializer<T> implements Serializer<T> {
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public void serialize(T object, String filePath) throws IOException {
        xmlMapper.writeValue(new File(filePath), object);
    }

    @Override
    public T deserialize(String filePath, Class<T> clazz) throws IOException {
        return xmlMapper.readValue(new File(filePath), clazz);
    }
}

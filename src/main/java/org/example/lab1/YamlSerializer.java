package org.example.lab1;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;

public class YamlSerializer<T> implements Serializer<T> {
    private final YAMLMapper yamlMapper = new YAMLMapper();

    @Override
    public void serialize(T object, String filePath) throws IOException {
        yamlMapper.writeValue(new File(filePath), object);
    }

    @Override
    public T deserialize(String filePath, Class<T> clazz) throws IOException {
        return yamlMapper.readValue(new File(filePath), clazz);
    }
}

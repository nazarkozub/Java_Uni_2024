package org.example.lab1;

import java.io.IOException;

public interface Serializer<T> {
    void serialize(T object, String filePath) throws IOException;

    T deserialize(String filePath, Class<T> clazz) throws IOException;
}

package com.zautomate.helpers;

import alexh.weak.Dynamic;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class YAMLHelper {

    private HashMap properties;


    public YAMLHelper(String fileName) throws IOException {
        Yaml yaml = new Yaml();
        properties = new HashMap();
        try(InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            Iterable<Object> iterable = yaml.loadAll(in);
            iterable.forEach((o) -> properties.putAll((Map) o));
        }
    }

    public String getValue(String key) {
        return Dynamic.from(properties).dget(key).convert().intoString();
    }
}

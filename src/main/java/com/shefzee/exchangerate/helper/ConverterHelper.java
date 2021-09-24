package com.shefzee.exchangerate.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConverterHelper {

    private final ObjectMapper objectMapper;

    public <T> T convert(Object obj, Class<T> clazz){
        return this.fromJson(this.toJsonNode(obj),clazz);
    }

    public <Z,U> U convertAll(Object src, Class<Z> clazz,Class<U> collectionClass){
        return this.fromJsonArray(this.toJsonNode(src),clazz,collectionClass);
    }

    private  <T> T fromJson(JsonNode jsonNode, Class<T> clazz){
        try{
            return objectMapper.convertValue(jsonNode,clazz);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    private <T,U> U fromJsonArray(JsonNode jsonNode, Class<T> clazz, Class<U> collectionClazz) {
        try {
            return objectMapper.convertValue(jsonNode, objectMapper.getTypeFactory().constructCollectionLikeType(collectionClazz, clazz));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);

        }
    }

    private JsonNode toJsonNode(Object obj){
        if(obj instanceof String){
            return readTree((String)obj);
        }else{
            return convertValue(obj,JsonNode.class);
        }
    }

    private JsonNode readTree(String value){
        try {
            return objectMapper.readTree(value);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    private <T> T convertValue(Object obj, Class<T> clazz){
        try {
            return objectMapper.convertValue(obj,clazz);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}

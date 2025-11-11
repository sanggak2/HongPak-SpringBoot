package com.example.hongpak_springboot.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ObjectMapper 확인
 */
class BurgerTest {

    @Test
    public void 자바객체_JSON으로() throws JsonProcessingException {
        // 입력
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우", "빵", "양상추", "소스");
        Burger burger = new Burger("통 쉬림프 와퍼", 6000, ingredients);

        // 실제
        String json = objectMapper.writeValueAsString(burger);

        // 예상
        String expected = "{\"name\":\"통 쉬림프 와퍼\",\"price\":6000,\"ingredient\":[\"통새우\",\"빵\",\"양상추\",\"소스\"]}";

        // 검증
        assertEquals(expected, json);
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void Json_자바객체로() throws JsonProcessingException {
        // 입력
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우", "빵", "양상추", "소스");
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "통 쉬림프 와퍼");
        objectNode.put("price", 6000);

        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("통새우");
        arrayNode.add("빵");
        arrayNode.add("양상추");
        arrayNode.add("소스");
        objectNode.set("ingredient", arrayNode);
        String json = objectNode.toString();

        // 실제
        Burger burger = objectMapper.readValue(json, Burger.class);

        // 예상
        Burger expected = new Burger("통 쉬림프 와퍼", 6000, ingredients);

        //검증
        assertEquals(expected.toString(), burger.toString());
        System.out.println(objectMapper.readTree(json).toString());
        System.out.println(burger.toString());
    }

}
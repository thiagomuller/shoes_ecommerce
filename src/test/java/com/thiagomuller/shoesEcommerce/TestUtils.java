package com.thiagomuller.shoesEcommerce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiagomuller.shoesEcommerce.models.Shoe;

public class TestUtils {
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	public static Object asObjectFromJson(String json){
		try{
			final ObjectMapper mapper = new ObjectMapper();
			final Object resultObject = mapper.readValue(json, Shoe.class);
			return resultObject;
		} catch (JsonMappingException jsonMapping){
			jsonMapping.printStackTrace();
		} catch (JsonProcessingException jsonProcessing){
			jsonProcessing.printStackTrace();
		}
		return null;
	}

}

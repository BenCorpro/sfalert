package com.safetynet.sfalert.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.sfalert.model.Json;

public class JsonProxy {

  public Json retrieveData() {
    Json jsonFile = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      File file = new File("/home/tez1kat/Documents/data.json");
      jsonFile = objectMapper.readValue(file, Json.class);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
    return jsonFile;
  }
}

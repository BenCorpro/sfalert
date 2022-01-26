package com.safetynet.sfalert.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.model.Person;

public class CustomJsonSerializer extends StdSerializer<Json> {

  /**
  * 
  */
  private static final long serialVersionUID = 1L;

  public CustomJsonSerializer() {
    this(null);
  }

  public CustomJsonSerializer(Class<Json> t) {
    super(t);
  }

  @Override
    public void serialize(Json json, JsonGenerator jsonGenerator, SerializerProvider serializer){
      try{ jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("persons");
        jsonGenerator.writeStartArray();
        for(Person p : json.getPersons()) {
          jsonGenerator.writeStartObject();
          jsonGenerator.writeStringField("firstName", p.getFirstName());
          jsonGenerator.writeStringField("lastName", p.getLastName());
          jsonGenerator.writeStringField("address", p.getAddress());
          jsonGenerator.writeStringField("city", p.getCity());
          jsonGenerator.writeStringField("zip", p.getZip());
          jsonGenerator.writeStringField("phone", p.getPhone());
          jsonGenerator.writeStringField("email", p.getEmail());
          jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName("firestations");
        jsonGenerator.writeStartArray();
        for(FireStation f : json.getFirestations()) {
          jsonGenerator.writeStartObject();
          jsonGenerator.writeStringField("address", f.getAddress());
          jsonGenerator.writeStringField("station", f.getStation());
          jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName("medicalrecords");
        jsonGenerator.writeStartArray();
        for(MedicalRecord m : json.getMedicalrecords()) {
          jsonGenerator.writeStartObject();
          jsonGenerator.writeStringField("firstName", m.getFirstName());
          jsonGenerator.writeStringField("lastName", m.getLastName());
          jsonGenerator.writeStringField("birthdate", m.getBirthdate());
          jsonGenerator.writeFieldName("medications");
          jsonGenerator.writeArray(m.getMedications().toArray(new String[m.getMedications().size()]), 0, m.getMedications().size());
          jsonGenerator.writeFieldName("allergies");
          jsonGenerator.writeArray(m.getAllergies().toArray(new String[m.getAllergies().size()]), 0, m.getAllergies().size());
          jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }


}

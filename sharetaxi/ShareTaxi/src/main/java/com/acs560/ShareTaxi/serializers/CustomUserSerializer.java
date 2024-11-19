package com.acs560.ShareTaxi.serializers;

import com.acs560.ShareTaxi.entities.CustomUserEntity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomUserSerializer extends StdSerializer<CustomUserEntity> {

    public CustomUserSerializer() {
        this(null);
    }

    public CustomUserSerializer(Class<CustomUserEntity> t) {
        super(t);
    }

    @Override
    public void serialize(CustomUserEntity user, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", user.getId());
        gen.writeStringField("username", user.getUsername());
        gen.writeStringField("role", user.getRole());
        gen.writeEndObject();
    }
}

package de.msg.dm.kommunikation.domain.crypto;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@DataObject(generateConverter = false)
public class Authentification {
    private long ehkId;
    private String keyId;
    private JsonObject jsonObject;
    
    public Authentification() {
    }
    
    public Authentification(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }
    
    public JsonObject toJson() {
        JsonObject  jsonObject = JsonObject.mapFrom(this);
        return jsonObject;
    }

    public long getEhkId() {
        return ehkId;
    }

    public void setEhkId(long ehkId) {
        this.ehkId = ehkId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
}

package de.msg.dm.kommunikation.domain.crypto;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@DataObject(generateConverter = false)
public class Authentification {
    private long ehkId;
    private String keyId;

    public Authentification() {
    }

    public Authentification(JsonObject jsonObject) {
        ehkId = jsonObject.getLong("ehkId");
        keyId = jsonObject.getString("keyId");
    }


    public JsonObject toJson() {

        JsonObject result = new JsonObject();

        result.put("ehkId", this.getEhkId());

        if (this.getKeyId() != null) {
            result.put("keyId", this.getKeyId());
        }
        return result;
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

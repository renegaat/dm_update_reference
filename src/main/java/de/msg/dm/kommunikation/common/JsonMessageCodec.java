package de.msg.dm.kommunikation.common;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.Json;

/**
 * @param <MessageType> type of the message.
 * @author Christian Ehrhardt (msg systems ag) 2018.
 */
public class JsonMessageCodec<MessageType> implements MessageCodec<MessageType, MessageType> {

    private Class<MessageType> messageClass;

    public JsonMessageCodec(Class<MessageType> messageClass) {
        this.messageClass = messageClass;
    }

    @Override
    public void encodeToWire(Buffer buffer, MessageType t) {
        buffer.appendString(Json.encode(t));
    }

    @Override
    public MessageType decodeFromWire(int pos, Buffer buffer) {
        return Json.decodeValue(buffer.getString(pos, buffer.length()), messageClass);
    }

    @Override
    public MessageType transform(MessageType t) {
        String encoded = Json.encode(t);
        return Json.decodeValue(encoded, messageClass);
    }

    @Override
    public String name() {
        return messageClass.getName() + "Codec";
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}





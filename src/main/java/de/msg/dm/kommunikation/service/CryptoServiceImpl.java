package de.msg.dm.kommunikation.service;

import de.msg.dm.kommunikation.domain.crypto.Authentification;
import de.msg.dm.kommunikation.domain.crypto.CryptoService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class CryptoServiceImpl implements CryptoService {

    Logger logger = LoggerFactory.getLogger(CryptoService.class);

    public CryptoServiceImpl(Handler<AsyncResult<CryptoService>> handler) {
        handler.handle(Future.succeededFuture(this));
    }

    @Override
    public CryptoService getRandomNumber(Authentification authentification, Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonObject result = new JsonObject();
        result.put("ehkId", authentification.getEhkId());
        result.put("keyId", authentification.getKeyId());
        resultHandler.handle(Future.succeededFuture(result));
        return this;
    }

    @Override
    public CryptoService getSecondRandomNumber(Authentification authentification, Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonObject result = new JsonObject();
        result.put("ehkId", authentification.getEhkId());
        result.put("keyId", authentification.getKeyId());
        resultHandler.handle(Future.succeededFuture(result));
        return this;
    }
}
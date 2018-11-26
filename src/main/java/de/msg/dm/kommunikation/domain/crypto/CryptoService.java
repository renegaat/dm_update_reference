
package de.msg.dm.kommunikation.domain.crypto;

import de.msg.dm.kommunikation.service.CryptoServiceImpl;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

@ProxyGen
public interface CryptoService {

    @Fluent
    CryptoService getRandomNumber(Authentification authentification, Handler<AsyncResult<JsonObject>> resultHandler);
    
    
    static CryptoService create(Handler<AsyncResult<CryptoService>> handler) {
        return new CryptoServiceImpl(handler);
    }
    
    static CryptoService createProxy(Vertx vertx, String adress) {
        return new CryptoServiceVertxEBProxy(vertx, adress);
    }
}


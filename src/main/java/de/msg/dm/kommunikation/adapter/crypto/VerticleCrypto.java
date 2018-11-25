package de.msg.dm.kommunikation.adapter.crypto;

import de.msg.dm.kommunikation.common.JsonMessageCodec;
import de.msg.dm.kommunikation.domain.crypto.Authentification;
import de.msg.dm.kommunikation.domain.crypto.CryptoService;
import de.msg.dm.kommunikation.service.Services;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceBinder;

public class VerticleCrypto extends AbstractVerticle {
    
    @Override
    public void start(Future<Void> startFuture) {

        
        CryptoService.create(cryptoServiceAsyncResult -> {
            
            if (cryptoServiceAsyncResult.succeeded()) {
              
              MessageConsumer<JsonObject> binder =   new ServiceBinder(vertx)
                        .setAddress(Services.CRYPTOSERVICE.toString())
                        .registerLocal(CryptoService.class,cryptoServiceAsyncResult.result());

                binder.completionHandler(startFuture);
                
            } else {
                startFuture.fail(cryptoServiceAsyncResult.cause());
            }
        });
        
    }
    
    @Override
    public void stop(Future<Void> stopFuture) {
        stopFuture.complete();
    }
}

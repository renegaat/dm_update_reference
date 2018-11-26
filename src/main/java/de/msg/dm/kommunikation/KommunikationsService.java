package de.msg.dm.kommunikation;

import de.msg.dm.kommunikation.adapter.crypto.VerticleCrypto;
import de.msg.dm.kommunikation.adapter.fzg.VerticleFzg;
import de.msg.dm.kommunikation.domain.crypto.CryptoService;
import de.msg.dm.kommunikation.service.Services;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.serviceproxy.ServiceBinder;

public class KommunikationsService {
    
    public static void main(String[] args) {
        
        Logger log = LoggerFactory.getLogger(KommunikationsService.class);
        
        Vertx vertx = Vertx.vertx();
        
        log.info("Kommunikationsservice gestartet");

        vertx.deployVerticle(new VerticleCrypto(), stringAsyncResult -> {
            
            if (stringAsyncResult.succeeded()) {
                CryptoService.create(cryptoServiceAsyncResult -> {

                    if (cryptoServiceAsyncResult.succeeded()) {
                        new ServiceBinder(vertx)
                                .setAddress(Services.CRYPTOSERVICE.toString())
                                .register(CryptoService.class,cryptoServiceAsyncResult.result());
                       
                        log.info("Crypto Service deployed und gelinked");
                        
                        vertx.deployVerticle(new VerticleFzg(), asyncResult -> {
                            if (asyncResult.succeeded()) {
                                log.info("VerticleFzg deployed");
                            } else {
                                log.error("VerticleFzg not deployed");
                            }
                        });
                    } else {
                        log.error("Crypto Service failure :" + cryptoServiceAsyncResult.cause());
                    }
                });
                log.info("VerticleCrypto deployed");
                
            } else {
                log.error("VerticleCrypto not deployed");
            }
        });
    }
}

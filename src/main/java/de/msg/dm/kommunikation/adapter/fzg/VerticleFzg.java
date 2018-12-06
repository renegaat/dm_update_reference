package de.msg.dm.kommunikation.adapter.fzg;

import de.msg.dm.kommunikation.domain.crypto.Authentification;
import de.msg.dm.kommunikation.domain.crypto.CryptoService;
import de.msg.dm.kommunikation.service.Services;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class VerticleFzg extends AbstractVerticle {

    public static final int PULSELONG = 2000;

    public static final int PULSESHORT = 500;

    private CryptoService cryptoService;
    
    private final Logger logger = LoggerFactory.getLogger(VerticleFzg.class);

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        cryptoService = CryptoService.createProxy(vertx, Services.CRYPTOSERVICE.toString());

        vertx.setPeriodic(PULSELONG, aLong -> {
            useCryptoServiceLong();
        });


        vertx.setPeriodic(PULSESHORT, aLong -> {
            useCryptoServiceShort();
        });

        startFuture.complete();
    }

    private void useCryptoServiceLong() {

        Authentification authentification = new Authentification();
        authentification.setEhkId(123L);
        authentification.setKeyId("keyId 1");

        cryptoService.getRandomNumber(authentification, jsonObjectAsyncResult -> {

                    if (jsonObjectAsyncResult.succeeded()) {
                        logger.info(jsonObjectAsyncResult.result().toString());
                    } else {
                        logger.info(jsonObjectAsyncResult.cause());
                    }
                }
        );
    }
    

    private void useCryptoServiceShort() {

        Authentification authentification = new Authentification();
        authentification.setEhkId(321L);
        authentification.setKeyId("keyId 2");
        
        cryptoService.getSecondRandomNumber(authentification, jsonObjectAsyncResult -> {

                    if (jsonObjectAsyncResult.succeeded()) {
                        logger.info(jsonObjectAsyncResult.result().toString());
                    } else {
                        logger.info(jsonObjectAsyncResult.cause());
                    }
                }
        );
    }


    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        stopFuture.complete();
    }
}

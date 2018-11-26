package de.msg.dm.kommunikation;

import de.msg.dm.kommunikation.adapter.crypto.VerticleCrypto;
import de.msg.dm.kommunikation.adapter.fzg.VerticleFzg;
import de.msg.dm.kommunikation.domain.crypto.CryptoService;
import de.msg.dm.kommunikation.service.Services;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.serviceproxy.ServiceBinder;

public class KommunikationsService {

    static Logger log = LoggerFactory.getLogger(KommunikationsService.class);
    static Vertx vertx = Vertx.vertx();

    public static void main(String[] args) {
        Future<Void> steps = createCryptoService()
                .compose(cryptoService -> deployVerticleFzG());
        
        steps.setHandler(voidAsyncResult -> {
            if (voidAsyncResult.succeeded()) {
                log.info("composition deployed");
            } else {
                log.error("composition deployment failure : ", voidAsyncResult.cause());
            }
        });
    }

    private static Future<Void> deployVerticleCrypto() {

        Future<Void> future = Future.future();

        vertx.deployVerticle(new VerticleCrypto(), stringAsyncResult -> {
            if (stringAsyncResult.succeeded()) {
                log.info("Crypto Verticle deployed");
                future.complete();
            } else {
                log.error("Crypto Verticle deployment error : ", stringAsyncResult.cause());
                future.fail(stringAsyncResult.cause());
            }
        });
        return future;
    }

    private static Future<Void> createCryptoService() {

        Future<Void> future = Future.future();

        CryptoService.create(cryptoServiceAsyncResult -> {

                    if (cryptoServiceAsyncResult.succeeded()) {

                        new ServiceBinder(vertx)
                                .setAddress(Services.CRYPTOSERVICE.toString())
                                .register(CryptoService.class, cryptoServiceAsyncResult.result());

                        log.info("Crypto Service created and binded");
                        future.complete();
                    } else {
                        log.error("Crypto Service error : ", cryptoServiceAsyncResult.cause());
                        future.fail(cryptoServiceAsyncResult.cause());
                    }
                }
        );
        return future;
    }

    private static Future<Void> deployVerticleFzG() {

        Future<Void> future = Future.future();

        vertx.deployVerticle(new VerticleFzg(), stringAsyncResult -> {
            if (stringAsyncResult.succeeded()) {
                log.info("Fzg verticle deployed");
                future.complete();
            } else {
                log.error("Fzg verticle deployment error : ", stringAsyncResult.cause());
                future.fail(stringAsyncResult.cause());
            }
        });
        return future;
    }
}

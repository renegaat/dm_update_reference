package de.msg.dm.kommunikation;

import de.msg.dm.kommunikation.adapter.crypto.VerticleCrypto;
import de.msg.dm.kommunikation.adapter.fzg.VerticleFzg;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KommunikationsService {
    public static void main(String[] args) {

        log.info("Kommunikationsservice gestartet");

        Vertx.vertx().deployVerticle(new VerticleFzg(), stringAsyncResult -> {

            if (stringAsyncResult.succeeded()) {
                log.info("VerticleFzg deployed");
            } else {
                log.error("VerticleFzg not deployed");
            }
        });

        Vertx.vertx().deployVerticle(new VerticleCrypto(), stringAsyncResult -> {
            if (stringAsyncResult.succeeded()) {
                log.info("VerticleCrypto deployed");
            } else {
                log.error("VerticleCrypto not deployed");
            }
        });
    }
}

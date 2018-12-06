package server.adapter;


import de.msg.dm.kommunikation.domain.crypto.Authentification;
import de.msg.dm.kommunikation.domain.crypto.CryptoService;
import de.msg.dm.kommunikation.service.Services;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import io.vertx.serviceproxy.ServiceBinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

@ExtendWith(VertxExtension.class)
public class CryptoServicerProxyTest {

    private static Vertx vertx;
    private static CryptoService cryptoService;

    @BeforeAll
    static void setup() {
        vertx = Vertx.vertx();

        CryptoService.create(cryptoServiceAsyncResult -> {
            if (cryptoServiceAsyncResult.succeeded()) {
                new ServiceBinder(vertx)
                        .setAddress(Services.CRYPTOSERVICE.toString())
                        .register(CryptoService.class, cryptoServiceAsyncResult.result());

                cryptoService = CryptoService.createProxy(vertx, Services.CRYPTOSERVICE.toString());
            }
        });
    }

    @Test
    void testCryptoServiceProxy(VertxTestContext vertxTestContext) throws InterruptedException {
        cryptoService.getRandomNumber(new Authentification(), jsonObjectAsyncResult -> {
            Assertions.assertTrue(jsonObjectAsyncResult.succeeded());
            vertxTestContext.completeNow();
        });
        Assertions.assertTrue(vertxTestContext.awaitCompletion(5, TimeUnit.SECONDS));
    }
}

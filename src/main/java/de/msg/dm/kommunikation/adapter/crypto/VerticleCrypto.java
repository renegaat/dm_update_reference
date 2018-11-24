package de.msg.dm.kommunikation.adapter.crypto;

        import de.msg.dm.kommunikation.common.JsonMessageCodec;
        import de.msg.dm.kommunikation.domain.crypto.Authentification;
        import io.vertx.core.AbstractVerticle;
        import io.vertx.core.Future;

public class VerticleCrypto extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {

        vertx.eventBus().registerDefaultCodec(Authentification.class,
                new JsonMessageCodec<>(Authentification.class));
        startFuture.complete();
    }

    @Override
    public void stop(Future<Void> stopFuture) {
        stopFuture.complete();
    }
}

package de.msg.dm.kommunikation.adapter.fzg;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class VerticleFzg extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        startFuture.complete();
    }


    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        stopFuture.complete();
    }
}

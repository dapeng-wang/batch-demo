package org.innolab.batchdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

@Slf4j
public class ChunkNumberListener implements ChunkListener {
    private int chunkNumber = 0;

    @Override
    public void beforeChunk(ChunkContext context) {
        chunkNumber++;
        log.info("Starting chunk number: " + chunkNumber);
    }

    @Override
    public void afterChunk(ChunkContext context) {
        log.info("Completed chunk number: " + chunkNumber);
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        log.info("Error in chunk number: " + chunkNumber);
    }

    public int getChunkNumber() {
        return chunkNumber;
    }
}

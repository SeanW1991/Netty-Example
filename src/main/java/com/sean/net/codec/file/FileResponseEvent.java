package com.sean.net.codec.file;

import com.sean.event.Event;

/**
 * Created by sean on 05/05/16.
 */
public final class FileResponseEvent implements Event {

    private final FileResponseType responseType;

    public FileResponseEvent(FileResponseType responseType) {
        this.responseType = responseType;
    }

    public FileResponseType getResponseType() {
        return responseType;
    }
}

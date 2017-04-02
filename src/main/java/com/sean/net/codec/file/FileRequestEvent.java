package com.sean.net.codec.file;

import com.sean.event.Event;

import java.io.File;

/**
 * Created by sean on 05/05/16.
 */
public final class FileRequestEvent implements Event {

    private final File file;

    public FileRequestEvent(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}

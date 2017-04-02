package com.sean.net.codec.file;

import com.sean.net.codec.handshake.HandshakeType;
import com.sean.net.codec.handshake.impl.HandshakeRequestEvent;
import com.sean.utils.ByteBufUtils;
import io.netty.buffer.ByteBufAllocator;

import java.io.File;

/**
 * Created by sean on 26/04/16.
 */
public class HandshakeFileRequestEvent extends HandshakeRequestEvent<File> {

    public HandshakeFileRequestEvent(ByteBufAllocator allo) {
        super(HandshakeType.FILE_SERVING_SERVICE, allo.buffer());
    }

    @Override
    public void addToBuffer(File file) {
        ByteBufUtils.addString(file.getAbsolutePath(), payload);
    }
}

package com.sean.utils;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by sean on 26/04/16.
 */
public final class ByteBufUtils {


    private ByteBufUtils() {

    }

    public static String readString(ByteBuf buf) {
        if(buf.readableBytes() < 0) {
            throw new IllegalArgumentException("buffer is has no readable bytes.");
        }
        int length = buf.readUnsignedByte();
        byte[] data = new byte[length];
        buf.readBytes(data);
        return new String(data);
    }

    public static void addString(String str, ByteBuf buf) {
        byte[] data = str.getBytes();
        buf.writeByte(data.length);
        buf.writeBytes(data);
    }

}

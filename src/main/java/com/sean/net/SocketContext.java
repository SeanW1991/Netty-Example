package com.sean.net;

import java.net.InetSocketAddress;

/**
 * Created by sean on 25/04/16.
 */
public final class SocketContext {

    /**
     * The {@link InetSocketAddress} for the client or server to either connect or bind to.
     */
    private final InetSocketAddress address;

    /**
     * Creates a new {@link SocketContext}.
     * @param address The {@code address}.
     */
    public SocketContext(InetSocketAddress address) {
        this.address = address;
    }

    /**
     * Gets the {@link InetSocketAddress}.
     * @return The {@code address}.
     */
    public InetSocketAddress getAddress() {
        return address;
    }
}

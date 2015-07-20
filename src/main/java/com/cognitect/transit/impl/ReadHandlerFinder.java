package com.cognitect.transit.impl;

import com.cognitect.transit.ReadHandler;

/**
 * @author Alexander Kiel
 */
public interface ReadHandlerFinder {

    ReadHandler<?,?> getHandler(String tag);
}

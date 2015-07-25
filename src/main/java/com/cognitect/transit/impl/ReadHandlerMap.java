// Copyright 2014 Cognitect. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS-IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.cognitect.transit.impl;

import com.cognitect.transit.ReadHandler;

import java.util.*;

public class ReadHandlerMap extends AbstractMap<String, ReadHandler<?, ?>> {

    private final Map<String, ReadHandler<?, ?>> handlers;

    public ReadHandlerMap() {
        this.handlers = ReaderFactory.defaultHandlers();
    }

    public ReadHandlerMap(Map<String, ReadHandler<?, ?>> customHandlers) {
        if (customHandlers == null) {
            this.handlers = ReaderFactory.defaultHandlers();
        } else {
            disallowOverridingGroundTypes(customHandlers);
            Map<String, ReadHandler<?,?>> handlers = ReaderFactory.defaultHandlers();
            handlers.putAll(customHandlers);
            this.handlers = handlers;
        }
    }

    private static void disallowOverridingGroundTypes(Map<String, ReadHandler<?,?>> handlers) {
        if (handlers != null) {
            String groundTypeTags[] = {"_", "s", "?", "i", "d", "b", "'", "map", "array"};
            for (String tag : groundTypeTags) {
                if (handlers.containsKey(tag)) {
                    throw new IllegalArgumentException("Cannot override decoding for transit ground type, tag " + tag);
                }
            }
        }
    }

    @Override
    public boolean containsKey(Object key) {
        return handlers.containsKey(key);
    }

    @Override
    public ReadHandler<?, ?> get(Object key) {
        return handlers.get(key);
    }

    @Override
    public ReadHandler<?, ?> remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<String, ReadHandler<?, ?>>> entrySet() {
        return handlers.entrySet();
    }
}

/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.caching.internal;

import org.gradle.caching.BuildCache;
import org.gradle.caching.BuildCacheEntryReader;
import org.gradle.caching.BuildCacheEntryWriter;
import org.gradle.caching.BuildCacheException;
import org.gradle.caching.BuildCacheKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ErrorLoggingBuildCacheDecorator implements BuildCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorLoggingBuildCacheDecorator.class);
    private final BuildCache delegate;

    public ErrorLoggingBuildCacheDecorator(BuildCache delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean load(BuildCacheKey key, BuildCacheEntryReader reader) throws BuildCacheException {
        try {
            return delegate.load(key, reader);
        } catch (BuildCacheException e) {
            LOGGER.warn("Could not load cached output for cache key {}", key, e);
            return false;
        }
    }

    @Override
    public void store(BuildCacheKey key, BuildCacheEntryWriter writer) throws BuildCacheException {
        try {
            delegate.store(key, writer);
        } catch (BuildCacheException e) {
            LOGGER.warn("Could not cache results for cache key {}", key, e);
        }
    }

    @Override
    public String getDescription() {
        return delegate.getDescription();
    }

    @Override
    public void close() throws IOException {
        delegate.close();
    }
}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.logging.log4j.core;

import org.checkerframework.checker.nullness.qual.Nullable;
import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.ThreadContext.ContextStack;
import org.apache.logging.log4j.core.impl.ThrowableProxy;
import org.apache.logging.log4j.core.time.Instant;
import org.apache.logging.log4j.core.time.MutableInstant;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.ReadOnlyStringMap;


/**
 * An abstract log event implementation with default values for all methods. The setters are no-ops.
 */
public abstract class AbstractLogEvent implements LogEvent {

    private static final long serialVersionUID = 1L;

    private volatile @Nullable MutableInstant instant;

    /**
     * Subclasses should implement this method to provide an immutable version.
     */
    @Override
    public LogEvent toImmutable() {
        return this;
    }

    @Override
    public @Nullable ReadOnlyStringMap getContextData() {
        return null;
    }

    /**
     * Returns {@link Collections#emptyMap()}.
     */
    @Override
    public Map<String, String> getContextMap() {
        return Collections.emptyMap();
    }

    @Override
    public ContextStack getContextStack() {
        return ThreadContext.EMPTY_STACK;
    }

    @Override
    public @Nullable Level getLevel() {
        return null;
    }

    @Override
    public @Nullable String getLoggerFqcn() {
        return null;
    }

    @Override
    public @Nullable String getLoggerName() {
        return null;
    }

    @Override
    public @Nullable Marker getMarker() {
        return null;
    }

    @Override
    public @Nullable Message getMessage() {
        return null;
    }

    @Override
    public @Nullable StackTraceElement getSource() {
        return null;
    }

    @Override
    public long getThreadId() {
        return 0;
    }

    @Override
    public @Nullable String getThreadName() {
        return null;
    }

    @Override
    public int getThreadPriority() {
        return 0;
    }

    @Override
    public @Nullable Throwable getThrown() {
        return null;
    }

    @Override
    public @Nullable ThrowableProxy getThrownProxy() {
        return null;
    }

    @Override
    public long getTimeMillis() {
        return 0;
    }

    @Override
    public Instant getInstant() {
        return getMutableInstant();
    }

    protected final MutableInstant getMutableInstant() {
        if (instant == null) {
            instant = new MutableInstant();
        }
        return instant;
    }

    @Override
    public boolean isEndOfBatch() {
        return false;
    }

    @Override
    public boolean isIncludeLocation() {
        return false;
    }

    @Override
    public void setEndOfBatch(final boolean endOfBatch) {
        // do nothing
    }

    @Override
    public void setIncludeLocation(final boolean locationRequired) {
        // do nothing
    }

    @Override
    public long getNanoTime() {
        return 0;
    }
}

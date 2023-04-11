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
package org.apache.logging.log4j.core.util;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationListener;
import org.apache.logging.log4j.core.config.Reconfigurable;

/**
 * Watcher for configuration files. Causes a reconfiguration when a file changes.
 */
public abstract class AbstractWatcher implements Watcher {

    private final @Nullable Reconfigurable reconfigurable;
    private final @Nullable List<ConfigurationListener> configurationListeners;
    private final @Nullable Log4jThreadFactory threadFactory;
    private final @Nullable Configuration configuration;
    private @Nullable Source source;

    // WrappedFileWatcher passes null for all 3 formal parameters
    public AbstractWatcher(final @Nullable Configuration configuration, final @Nullable Reconfigurable reconfigurable,
            final @Nullable List<ConfigurationListener> configurationListeners) {
        this.configuration = configuration;
        this.reconfigurable = reconfigurable;
        this.configurationListeners = configurationListeners;
        this.threadFactory = configurationListeners != null ?
            Log4jThreadFactory.createDaemonThreadFactory("ConfigurationFileWatcher") : null;
    }

    @Override
    public List<ConfigurationListener> getListeners() {
        return configurationListeners;
    }

    @Override
    public void modified() {
        for (final ConfigurationListener configurationListener : configurationListeners) {
            final Thread thread = threadFactory.newThread(new ReconfigurationRunnable(configurationListener, reconfigurable));
            thread.start();
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public abstract long getLastModified();

    @Override
    public abstract boolean isModified();

    @Override
    public void watching(@Nullable Source source) {
        this.source = source;
    }

    @Override
    public @Nullable Source getSource() {
        return source;
    }

    /**
     * Helper class for triggering a reconfiguration in a background thread.
     */
    public static class ReconfigurationRunnable implements Runnable {

        private final ConfigurationListener configurationListener;
        private final Reconfigurable reconfigurable;

        public ReconfigurationRunnable(final ConfigurationListener configurationListener, final Reconfigurable reconfigurable) {
            this.configurationListener = configurationListener;
            this.reconfigurable = reconfigurable;
        }

        @Override
        public void run() {
            configurationListener.onChange(reconfigurable);
        }
    }
}

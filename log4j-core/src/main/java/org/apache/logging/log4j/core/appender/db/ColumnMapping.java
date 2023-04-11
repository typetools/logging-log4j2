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
package org.apache.logging.log4j.core.appender.db;

import org.checkerframework.checker.nullness.qual.Nullable;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.spi.ThreadContextMap;
import org.apache.logging.log4j.spi.ThreadContextStack;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

/**
 * A configuration element for specifying a database column name mapping.
 *
 * @since 2.8
 */
@Plugin(name = "ColumnMapping", category = Core.CATEGORY_NAME, printObject = true)
public class ColumnMapping {

    /**
     * The empty array.
     */
    public static final ColumnMapping[] EMPTY_ARRAY = {};

    /**
     * Builder for {@link ColumnMapping}.
     */
    public static class Builder implements org.apache.logging.log4j.core.util.Builder<ColumnMapping> {

        @PluginConfiguration
        private Configuration configuration;

        @PluginElement("Layout")
        private @Nullable StringLayout layout;

        @PluginBuilderAttribute
        private @Nullable String literal;

        @PluginBuilderAttribute
        @Required(message = "No column name provided")
        private String name;

        @PluginBuilderAttribute
        private @Nullable String parameter;

        @PluginBuilderAttribute
        private @Nullable String pattern;

        @PluginBuilderAttribute
        private @Nullable String source;

        @PluginBuilderAttribute
        @Required(message = "No conversion type provided")
        private Class<?> type = String.class;

        @Override
        public ColumnMapping build() {
            if (pattern != null) {
                layout = PatternLayout.newBuilder()
                    .withPattern(pattern)
                    .withConfiguration(configuration)
                    .withAlwaysWriteExceptions(false)
                    .build();
            }
            if (!(layout == null
                || literal == null
                || Date.class.isAssignableFrom(type)
                || ReadOnlyStringMap.class.isAssignableFrom(type)
                || ThreadContextMap.class.isAssignableFrom(type)
                || ThreadContextStack.class.isAssignableFrom(type))) {
                LOGGER.error("No 'layout' or 'literal' value specified and type ({}) is not compatible with ThreadContextMap, ThreadContextStack, or java.util.Date for the mapping", type, this);
                return null;
            }
            if (literal != null && parameter != null) {
                LOGGER.error("Only one of 'literal' or 'parameter' can be set on the column mapping {}", this);
                return null;
            }
            return new ColumnMapping(name, source, layout, literal, parameter, type);
        }

        public Builder setConfiguration(final Configuration configuration) {
            this.configuration = configuration;
            return this;
        }

        /**
         * Layout of value to write to database (before type conversion). Not applicable if {@link #setType(Class)} is
         * a {@link ReadOnlyStringMap}, {@link ThreadContextMap}, or {@link ThreadContextStack}.
         *
         * @return this.
         */
        public Builder setLayout(final StringLayout layout) {
            this.layout = layout;
            return this;
        }

        /**
         * Literal value to use for populating a column. This is generally useful for functions, stored procedures,
         * etc. No escaping will be done on this value.
         *
         * @return this.
         */
        public Builder setLiteral(final String literal) {
            this.literal = literal;
            return this;
        }

        /**
         * Column name.
         *
         * @return this.
         */
        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        /**
         * Parameter value to use for populating a column, MUST contain a single parameter marker '?'. This is generally useful for functions, stored procedures,
         * etc. No escaping will be done on this value.
         *
         * @return this.
         */
        public Builder setParameter(final String parameter) {
            this.parameter= parameter;
            return this;
        }

        /**
         * Pattern to use as a {@link PatternLayout}. Convenient shorthand for {@link #setLayout(StringLayout)} with a
         * PatternLayout.
         *
         * @return this.
         */
        public Builder setPattern(final String pattern) {
            this.pattern = pattern;
            return this;
        }

        /**
         * Source name. Useful when combined with a {@link org.apache.logging.log4j.message.MapMessage} depending on the
         * appender.
         *
         * @return this.
         */
        public Builder setSource(final String source) {
            this.source = source;
            return this;
        }

        /**
         * Class to convert value to before storing in database. If the type is compatible with {@link ThreadContextMap} or
         * {@link ReadOnlyStringMap}, then the MDC will be used. If the type is compatible with {@link ThreadContextStack},
         * then the NDC will be used. If the type is compatible with {@link Date}, then the event timestamp will be used.
         *
         * @return this.
         */
        public Builder setType(final Class<?> type) {
            this.type = type;
            return this;
        }

        @Override
        public String toString() {
            return "Builder [name=" + name + ", source=" + source + ", literal=" + literal + ", parameter=" + parameter
                    + ", pattern=" + pattern + ", type=" + type + ", layout=" + layout + "]";
        }
    }

    private static final Logger LOGGER = StatusLogger.getLogger();

    @PluginBuilderFactory
    public static Builder newBuilder() {
        return new Builder();
    }

    public static String toKey(final String name) {
        return name.toUpperCase(Locale.ROOT);
    }

    private final StringLayout layout;
    private final String literalValue;
    private final String name;
    private final String nameKey;
    private final String parameter;
    private final String source;
    private final Class<?> type;

    private ColumnMapping(final String name, final String source, final StringLayout layout, final String literalValue, final String parameter, final Class<?> type) {
        this.name = name;
        this.nameKey = toKey(name);
        this.source = source;
        this.layout = layout;
        this.literalValue = literalValue;
        this.parameter = parameter;
        this.type = type;
    }

    public StringLayout getLayout() {
        return layout;
    }

    public String getLiteralValue() {
        return literalValue;
    }

    public String getName() {
        return name;
    }

    public String getNameKey() {
        return nameKey;
    }

    public String getParameter() {
        return parameter;
    }

    public String getSource() {
        return source;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ColumnMapping [name=" + name + ", source=" + source + ", literalValue=" + literalValue + ", parameter="
                + parameter + ", type=" + type + ", layout=" + layout + "]";
    }

}

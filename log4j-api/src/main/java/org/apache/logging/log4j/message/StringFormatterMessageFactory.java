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
package org.apache.logging.log4j.message;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Creates {@link FormattedMessage} instances for {@link MessageFactory2} methods (and {@link MessageFactory} by
 * extension.)
 * <p>
 * Enables the use of {@link java.util.Formatter} strings in message strings.
 * </p>
 * <p>
 * Creates {@link StringFormattedMessage} instances for {@link #newMessage(String, Object...)}.
 * </p>
 * <p>
 * This class is immutable.
 * </p>
 * <h4>Note to implementors</h4>
 * <p>
 * This class implements all {@link MessageFactory2} methods.
 * </p>
 */
public final class StringFormatterMessageFactory extends AbstractMessageFactory {

    /**
     * Instance of StringFormatterMessageFactory.
     */
    public static final StringFormatterMessageFactory INSTANCE = new StringFormatterMessageFactory();
    private static final long serialVersionUID = -1626332412176965642L;

    /**
     * Constructs a message factory with default flow strings.
     */
    public StringFormatterMessageFactory() {
    }

    /**
     * Creates {@link StringFormattedMessage} instances.
     *
     * @param message The message pattern.
     * @param params The parameters to the message.
     * @return The Message.
     *
     * @see MessageFactory#newMessage(String, Object...)
     */
    @Override
    public Message newMessage(final String message, final Object... params) {
        return new StringFormattedMessage(message, params);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0) {
        return new StringFormattedMessage(message, p0);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1) {
        return new StringFormattedMessage(message, p0, p1);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2) {
        return new StringFormattedMessage(message, p0, p1, p2);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3) {
        return new StringFormattedMessage(message, p0, p1, p2, p3);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4) {
        return new StringFormattedMessage(message, p0, p1, p2, p3, p4);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4, final @Nullable Object p5) {
        return new StringFormattedMessage(message, p0, p1, p2, p3, p4, p5);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4, final @Nullable Object p5,
            final @Nullable Object p6) {
        return new StringFormattedMessage(message, p0, p1, p2, p3, p4, p5, p6);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4, final @Nullable Object p5,
            final @Nullable Object p6, final @Nullable Object p7) {
        return new StringFormattedMessage(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4, final @Nullable Object p5,
            final @Nullable Object p6, final @Nullable Object p7, final @Nullable Object p8) {
        return new StringFormattedMessage(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4, final @Nullable Object p5,
            final @Nullable Object p6, final @Nullable Object p7, final @Nullable Object p8, final @Nullable Object p9) {
        return new StringFormattedMessage(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }
}

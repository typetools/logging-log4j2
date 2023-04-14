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
 * Enables the use of <code>{}</code> parameter markers in message strings.
 * </p>
 * <p>
 * Creates {@link ParameterizedMessage} instances for {@link #newMessage(String, Object...)}.
 * </p>
 * <p>
 * This class is immutable.
 * </p>
 * 
 * <h4>Note to implementors</h4>
 * <p>
 * This class implements all {@link MessageFactory2} methods.
 * </p>
 */
public final class ParameterizedMessageFactory extends AbstractMessageFactory {
    /**
     * Instance of ParameterizedMessageFactory.
     */
    public static final ParameterizedMessageFactory INSTANCE = new ParameterizedMessageFactory();

    private static final long serialVersionUID = -8970940216592525651L;

    /**
     * Constructs a message factory.
     */
    public ParameterizedMessageFactory() {
    }

    /**
     * Creates {@link ParameterizedMessage} instances.
     *
     * @param message The message pattern.
     * @param params The message parameters.
     * @return The Message.
     *
     * @see MessageFactory#newMessage(String, Object...)
     */
    @Override
    public Message newMessage(final String message, final Object... params) {
        return new ParameterizedMessage(message, params);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0) {
        return new ParameterizedMessage(message, p0);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1) {
        return new ParameterizedMessage(message, p0, p1);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2) {
        return new ParameterizedMessage(message, p0, p1, p2);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3) {
        return new ParameterizedMessage(message, p0, p1, p2, p3);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4) {
        return new ParameterizedMessage(message, p0, p1, p2, p3, p4);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4, final @Nullable Object p5) {
        return new ParameterizedMessage(message, p0, p1, p2, p3, p4, p5);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4, final @Nullable Object p5,
            final @Nullable Object p6) {
        return new ParameterizedMessage(message, p0, p1, p2, p3, p4, p5, p6);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4, final @Nullable Object p5,
            final @Nullable Object p6, final @Nullable Object p7) {
        return new ParameterizedMessage(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4, final @Nullable Object p5,
            final @Nullable Object p6, final @Nullable Object p7, final @Nullable Object p8) {
        return new ParameterizedMessage(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    /**
     * @since 2.6.1
     */
    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3, final @Nullable Object p4, final @Nullable Object p5,
            final @Nullable Object p6, final @Nullable Object p7, final @Nullable Object p8, final @Nullable Object p9) {
        return new ParameterizedMessage(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }
}

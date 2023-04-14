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
import java.io.Serializable;

import org.apache.logging.log4j.util.PerformanceSensitive;

/**
 * Implementation of the {@link MessageFactory} interface that avoids allocating temporary objects where possible.
 * Message instances are cached in a ThreadLocal and reused when a new message is requested within the same thread.
 * @see ParameterizedMessageFactory
 * @see ReusableSimpleMessage
 * @see ReusableObjectMessage
 * @see ReusableParameterizedMessage
 * @since 2.6
 */
@PerformanceSensitive("allocation")
public final class ReusableMessageFactory implements MessageFactory2, Serializable {

    /**
     * Instance of ReusableMessageFactory..
     */
    public static final ReusableMessageFactory INSTANCE = new ReusableMessageFactory();

    private static final long serialVersionUID = -8970940216592525651L;
    private static ThreadLocal<ReusableParameterizedMessage> threadLocalParameterized = new ThreadLocal<>();
    private static ThreadLocal<ReusableSimpleMessage> threadLocalSimpleMessage = new ThreadLocal<>();
    private static ThreadLocal<ReusableObjectMessage> threadLocalObjectMessage = new ThreadLocal<>();

    /**
     * Constructs a message factory.
     */
    public ReusableMessageFactory() {
    }

    private static ReusableParameterizedMessage getParameterized() {
        ReusableParameterizedMessage result = threadLocalParameterized.get();
        if (result == null) {
            result = new ReusableParameterizedMessage();
            threadLocalParameterized.set(result);
        }
        return result.reserved ? new ReusableParameterizedMessage().reserve() : result.reserve();
    }

    private static ReusableSimpleMessage getSimple() {
        ReusableSimpleMessage result = threadLocalSimpleMessage.get();
        if (result == null) {
            result = new ReusableSimpleMessage();
            threadLocalSimpleMessage.set(result);
        }
        return result;
    }

    private static ReusableObjectMessage getObject() {
        ReusableObjectMessage result = threadLocalObjectMessage.get();
        if (result == null) {
            result = new ReusableObjectMessage();
            threadLocalObjectMessage.set(result);
        }
        return result;
    }

    /**
     * Invokes {@link Clearable#clear()} when possible.
     * This flag is used internally to verify that a reusable message is no longer in use and
     * can be reused.
     * @param message the message to make available again
     * @since 2.7
     */
    public static void release(final Message message) { // LOG4J2-1583
        if (message instanceof Clearable) {
            ((Clearable) message).clear();
        }
    }

    @Override
    public Message newMessage(final CharSequence charSequence) {
        final ReusableSimpleMessage result = getSimple();
        result.set(charSequence);
        return result;
    }

    /**
     * Creates {@link ReusableParameterizedMessage} instances.
     *
     * @param message The message pattern.
     * @param params The message parameters.
     * @return The Message.
     *
     * @see MessageFactory#newMessage(String, Object...)
     */
    @Override
    public Message newMessage(final String message, final Object... params) {
        return getParameterized().set(message, params);
    }

    @Override
    public Message newMessage(final String message, final @Nullable Object p0) {
        return getParameterized().set(message, p0);
    }

    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1) {
        return getParameterized().set(message, p0, p1);
    }

    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2) {
        return getParameterized().set(message, p0, p1, p2);
    }

    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2,
            final @Nullable Object p3) {
        return getParameterized().set(message, p0, p1, p2, p3);
    }

    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3,
            final @Nullable Object p4) {
        return getParameterized().set(message, p0, p1, p2, p3, p4);
    }

    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3,
            final @Nullable Object p4, final @Nullable Object p5) {
        return getParameterized().set(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3,
            final @Nullable Object p4, final @Nullable Object p5, final @Nullable Object p6) {
        return getParameterized().set(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3,
            final @Nullable Object p4, final @Nullable Object p5, final @Nullable Object p6, final @Nullable Object p7) {
        return getParameterized().set(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3,
            final @Nullable Object p4, final @Nullable Object p5, final @Nullable Object p6, final @Nullable Object p7, final @Nullable Object p8) {
        return getParameterized().set(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public Message newMessage(final String message, final @Nullable Object p0, final @Nullable Object p1, final @Nullable Object p2, final @Nullable Object p3,
            final @Nullable Object p4, final @Nullable Object p5, final @Nullable Object p6, final @Nullable Object p7, final @Nullable Object p8, final @Nullable Object p9) {
        return getParameterized().set(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    /**
     * Creates {@link ReusableSimpleMessage} instances.
     *
     * @param message The message String.
     * @return The Message.
     *
     * @see MessageFactory#newMessage(String)
     */
    @Override
    public Message newMessage(final String message) {
        final ReusableSimpleMessage result = getSimple();
        result.set(message);
        return result;
    }


    /**
     * Creates {@link ReusableObjectMessage} instances.
     *
     * @param message The message Object.
     * @return The Message.
     *
     * @see MessageFactory#newMessage(Object)
     */
    @Override
    public Message newMessage(final Object message) {
        final ReusableObjectMessage result = getObject();
        result.set(message);
        return result;
    }
}

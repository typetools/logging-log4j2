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
package org.apache.logging.log4j.core.config.arbiters;

import org.checkerframework.checker.nullness.qual.Nullable;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;

/**
 * Class Description goes here.
 */
@Plugin(name = "Select", category = Node.CATEGORY, elementType = Arbiter.ELEMENT_TYPE, deferChildren = true,
        printObject = true)
public class SelectArbiter {

    public @Nullable Arbiter evaluateConditions(List<Arbiter> conditions) {
        Optional<Arbiter> opt = conditions.stream().filter((c) -> c instanceof DefaultArbiter)
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                });
        for (Arbiter condition : conditions) {
            if (condition instanceof DefaultArbiter) {
                continue;
            }
            if (condition.isCondition()) {
                return condition;
            }
        }
        return opt.orElse(null);
    }

    @PluginBuilderFactory
    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder implements org.apache.logging.log4j.core.util.Builder<SelectArbiter> {

        public Builder asBuilder() {
            return this;
        }

        public SelectArbiter build() {
            return new SelectArbiter();
        }
    }
}

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
package org.apache.logging.log4j.core.config.builder.impl;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.ScriptComponentBuilder;

/**
 * @since 2.5
 */
class DefaultScriptComponentBuilder extends DefaultComponentAndConfigurationBuilder<ScriptComponentBuilder>
        implements ScriptComponentBuilder {

    public DefaultScriptComponentBuilder(final DefaultConfigurationBuilder<? extends Configuration> builder,
                                         final String name, final @Nullable String language, final @Nullable String text) {
        super(builder, name, "Script");
        if (language != null) {
            addAttribute("language", language);
        }
        if (text != null) {
            addAttribute("text", text);
        }
    }
}

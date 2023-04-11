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
package org.apache.logging.log4j.core.config.builder.api;

import org.checkerframework.checker.nullness.qual.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Container for building Configurations. This class is not normally directly manipulated by users
 * of the Assembler API.
 * @since 2.4
 */
public class Component {

    private final Map<String, String> attributes = new LinkedHashMap<>();
    private final List<Component> components = new ArrayList<>();
    private final @Nullable String pluginType;
    private final @Nullable String value;

    public Component(final String pluginType) {
        this(pluginType, null, null);
    }

    public Component(final String pluginType, final @Nullable String name) {
        this(pluginType, name, null);
    }

    public Component(final String pluginType, final @Nullable String name, final @Nullable String value) {
        this.pluginType = pluginType;
        this.value = value;
        if (name != null && name.length() > 0) {
            attributes.put("name", name);
        }
    }

    public Component() {
        this.pluginType = null;
        this.value = null;
    }


    public String addAttribute(final String key, final String newValue) {
        return attributes.put(key, newValue);
    }

    public void addComponent(final Component component) {
        components.add(component);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public List<Component> getComponents() {
        return components;
    }

    public @Nullable String getPluginType() {
        return pluginType;
    }

    public @Nullable String getValue() {
        return value;
    }
 }

/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.org).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.healthcare.codegen.tool.framework.hl7.core;

import org.wso2.healthcare.codegen.tool.framework.commons.config.ToolConfig;
import org.wso2.healthcare.codegen.tool.framework.commons.core.AbstractTool;
import org.wso2.healthcare.codegen.tool.framework.commons.core.Tool;
import org.wso2.healthcare.codegen.tool.framework.commons.core.ToolContext;
import org.wso2.healthcare.codegen.tool.framework.commons.exception.CodeGenException;
import org.wso2.healthcare.codegen.tool.framework.hl7.core.common.HL7SpecificationData;
import org.wso2.healthcare.codegen.tool.framework.hl7.core.config.HL7ToolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * HL7 protocol level tool lib implementation.s
 */
public class HL7Tool extends AbstractTool {

    private HL7ToolContext toolContext;
    private final Map<String, Tool> toolImplementations;

    public HL7Tool() {
        toolImplementations = new HashMap<>();
    }

    @Override
    public void initialize(ToolConfig toolConfig) throws CodeGenException {
        toolContext = new HL7ToolContext();
        toolContext.setConfig((HL7ToolConfig) toolConfig);
        HL7SpecParser specParser = new HL7SpecParser();
        specParser.parse(toolConfig);
        toolContext.setSpecificationData(HL7SpecificationData.getDataHolderInstance());
    }

    @Override
    public ToolContext getToolContext() throws CodeGenException {
        return toolContext;
    }

    @Override
    public void setToolContext(ToolContext toolContext) throws CodeGenException {
        this.toolContext = (HL7ToolContext) toolContext;
    }

    public Map<String, Tool> getToolImplementations() {
        return toolImplementations;
    }

    public void addToolImplementation(String toolName, Tool tool) {
        toolImplementations.putIfAbsent(toolName, tool);
    }
}

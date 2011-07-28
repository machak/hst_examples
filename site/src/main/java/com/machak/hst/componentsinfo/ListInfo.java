
/*
 *
 *  * Copyright 2011 m.milicevic.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *       http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.machak.hst.componentsinfo;

import org.hippoecm.hst.configuration.components.Parameter;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.pagecomposer.jaxrs.model.ComponentWrapper;


public interface ListInfo extends GeneralListInfo {

    /**
     * Returns the scope to search below. Leading and trailing slashes do not have meaning and will be skipped when using the scope. The scope
     * is always relative to the current {@link Mount#getContentPath()}, even if it starts with a <code>/</code>
     * @return the scope to search below
     */
    @Parameter(name = "scope",defaultValue="/", displayName = "Scope", typeHint = ComponentWrapper.ParameterType.STRING)
    String getScope();

    @Parameter(name = "cssclass",defaultValue="lightgrey", displayName = "Css Class", typeHint = ComponentWrapper.ParameterType.STRING)
    String getCssClass();
    
    @Parameter(name = "bgcolor",defaultValue="", displayName = "Background Color", typeHint = ComponentWrapper.ParameterType.COLOR)
    String getBgColor();
}

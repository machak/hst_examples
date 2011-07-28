
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
import org.hippoecm.hst.pagecomposer.jaxrs.model.ComponentWrapper;


public interface GeneralListInfo  {

    @Parameter(name = "title",defaultValue="Overview", displayName = "The title of the page", typeHint = ComponentWrapper.ParameterType.STRING)
    String getTitle();

    @Parameter(name = "pageSize", defaultValue="10", typeHint = ComponentWrapper.ParameterType.NUMBER, displayName = "Page Size")
    int getPageSize();

    @Parameter(name = "docType", defaultValue="hstexamples:basedocument", displayName = "Document Type", typeHint = ComponentWrapper.ParameterType.STRING)
    String getDocType();

    @Parameter(name = "sortBy", displayName = "Sort By Property", typeHint = ComponentWrapper.ParameterType.STRING)
    String getSortBy();

    @Parameter(name = "sortOrder",defaultValue="descending", displayName = "Sort Order", typeHint = ComponentWrapper.ParameterType.STRING)
    String getSortOrder();
}

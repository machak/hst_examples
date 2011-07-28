
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

package com.machak.hst.components;

import com.machak.hst.componentsinfo.SearchInfo;
import org.hippoecm.hst.configuration.components.ParametersInfo;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ParametersInfo(type = SearchInfo.class)
public class Search extends BaseComponent {

    public static final Logger log = LoggerFactory.getLogger(Search.class);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) throws HstComponentException {

       SearchInfo info = getParametersInfo(request);
       HippoBean scope = getSiteContentBaseBean(request);

       String query = getPublicRequestParameter(request, "query");
       if(query == null) {
           // test namespaced query parameter
           query = request.getParameter("query");
       }
       createAndExecuteSearch(request, info, scope, query);
    }
}

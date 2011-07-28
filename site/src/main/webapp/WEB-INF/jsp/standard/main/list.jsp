
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://www.hippoecm.org/jsp/hst/core" prefix='hst'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%--
  /*
   * Copyright 2011 m.milicevic.
   *
   * Licensed under the Apache License, Version 2.0 (the "License");
   * you may not use this file except in compliance with the License.
   * You may obtain a copy of the License at
   *
   *       http://www.apache.org/licenses/LICENSE-2.0
   *
   * Unless required by applicable law or agreed to in writing, software
   * distributed under the License is distributed on an "AS IS" BASIS,
   * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   * See the License for the specific language governing permissions and
   * limitations under the License.
   */
  --%>

<div class=${info.cssClass}>
<p>
    ${info.title}
</p>

<ul>
   <c:forEach var="item" items="${result.hippoBeans}" varStatus="counter">
    <c:if test="${counter.index == 0}">
      <hst:link var="link" hippobean="${item}"/>
      <li style="background-color:${info.bgColor};">
         <div>
            <c:if test="${hst:isReadable(item, 'image')}">
               <hst:link var="img" hippobean="${item.image.thumbnail}"/>
               <div  style="float:left;margin-right:10px;" >
               <img src="${img}" title="${item.image.fileName}" alt="${item.image.fileName}"/>
               </div>
            </c:if>
            <div>
             <p>
               <c:if test="${hst:isReadable(item, 'date')}">
                  <fmt:formatDate value="${item.date.time}" type="Date" pattern="MMMM d, yyyy" /> - 
               </c:if>
               <a href="${link}">${item.title}</a>
             </p>
             <p>${item.summary}</p>
            </div>
         </div>
      </li>
    </c:if>
  </c:forEach>
</ul>

<ul>
  <c:forEach var="item" items="${result.hippoBeans}" varStatus="counter">
    <c:if test="${counter.index > 0}">
      <hst:link var="link" hippobean="${item}"/>
      <li style="background-color:${info.bgColor};">
         <c:if test="${hst:isReadable(item, 'date')}">
            <fmt:formatDate value="${item.date.time}" type="Date" pattern="MMMM d, yyyy" /> - 
         </c:if>
         <a href="${link}">${item.title}</a>
      </li>
    </c:if>
  </c:forEach>

</ul>
</div>

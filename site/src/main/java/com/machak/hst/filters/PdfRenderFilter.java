/*
 * Copyright 2011 m.milicevic.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.machak.hst.filters;


import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;


/**
 * @version $Id$
 */

/**
 * PdfRenderFilter: installing:
 * <p/>
 * <filter>
 * <filter-name>PdfRenderFilter</filter-name>
 * <filter-class>com.onehippo.filters.PdfRenderFilter</filter-class>
 * </filter>
 * <filter-mapping>
 * <filter-name>PdfRenderFilter</filter-name>
 * <url-pattern>/*</url-pattern>
 * <dispatcher>REQUEST</dispatcher>
 * </filter-mapping>
 * <p/>
 * <p/>
 * Maven dependencies
 * <dependency>
 * <groupId>nekohtml</groupId>
 * <artifactId>nekohtml</artifactId>
 * <version>1.9.6.2</version>
 * </dependency>
 * <dependency>
 * <groupId>org.xhtmlrenderer</groupId>
 * <artifactId>core-renderer</artifactId>
 * <version>R8</version>
 * </dependency>
 */
public class PdfRenderFilter implements Filter {

    public static final String MIME_PDF = "application/pdf";

    private URL basePath = null;

    public void init(FilterConfig config) throws ServletException {
        // no init parameters
    }

    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) resp;

        //Check to see if this filter should apply.
        String renderType = request.getParameter("contentType");

        if (renderType != null && renderType.equals("pdf")) {
            //Capture the content for this request
            XhtmlContentReader capContent = new XhtmlContentReader(response);
            filterChain.doFilter(request, capContent);

            try {
                if (basePath == null) {
                    basePath = new URL(request.getScheme() + "://" + request.getServerName() + ':' + request.getServerPort());// + request.getContextPath() + '/');
                }
                response.setContentType(MIME_PDF);
                OutputStream browserStream = response.getOutputStream();
                Document document = capContent.getContent();
                if (document == null) {
                    throw new ServletException("Document was null");
                }
                // create PDF:
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocument(document, basePath.toString());
                renderer.layout();
                renderer.createPDF(browserStream);

            } catch (DocumentException e) {
                throw new ServletException(e);
            }


        } else {
            // just continue with normal flow
            filterChain.doFilter(request, response);
        }
    }


    public void destroy() {
        // nothing to destroy..
    }
}
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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xhtmlrenderer.resource.FSEntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * XhtmlContentReader
 */
public class XhtmlContentReader extends HttpServletResponseWrapper {

    private static Logger log = LoggerFactory.getLogger(XhtmlContentReader.class);

    private ByteArrayOutputStream contentBuffer;
    private PrintWriter writer;

    public XhtmlContentReader(HttpServletResponse originalResponse) {
        super(originalResponse);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer == null) {
            contentBuffer = new ByteArrayOutputStream();
            writer = new PrintWriter(contentBuffer);
        }
        return writer;
    }

    public Document getContent() {
        try {
            writer.flush();
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setValidating(false);
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            builder.setEntityResolver(FSEntityResolver.instance());
            InputSource source = new InputSource(new ByteArrayInputStream(contentBuffer.toByteArray()));
            return builder.parse(source);

        } catch (SAXException e) {
            log.error("Error parsing document", e);
        } catch (IOException e) {
            log.error("IO exception on document document", e);
        } catch (ParserConfigurationException e) {
            log.error("Parsing exception {}", e);
        }

        return null;

    }
}
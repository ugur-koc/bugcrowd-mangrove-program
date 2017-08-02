/*

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.dom.svg;

import org.apache.batik.dom.AbstractDocument;
import org.apache.batik.dom.util.DoublyIndexedTable;
import org.apache.batik.util.SVGTypes;

import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGAnimatedString;
import org.w3c.dom.svg.SVGFETileElement;

/**
 * This class implements {@link SVGFETileElement}.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: SVGOMFETileElement.java 489964 2006-12-24 01:30:23Z cam $
 */
public class SVGOMFETileElement
    extends    SVGOMFilterPrimitiveStandardAttributes
    implements SVGFETileElement {

    /**
     * Table mapping XML attribute names to TraitInformation objects.
     */
    protected static DoublyIndexedTable xmlTraitInformation;
    static {
        DoublyIndexedTable t =
            new DoublyIndexedTable(SVGOMFilterPrimitiveStandardAttributes.xmlTraitInformation);
        t.put(null, SVG_IN_ATTRIBUTE,
                new TraitInformation(true, SVGTypes.TYPE_CDATA));
        xmlTraitInformation = t;
    }

    /**
     * The 'in' attribute value.
     */
    protected SVGOMAnimatedString in;

    /**
     * Creates a new SVGOMFETileElement object.
     */
    protected SVGOMFETileElement() {
    }

    /**
     * Creates a new SVGOMFETileElement object.
     * @param prefix The namespace prefix.
     * @param owner The owner document.
     */
    public SVGOMFETileElement(String prefix, AbstractDocument owner) {
        super(prefix, owner);
        initializeLiveAttributes();
    }

    /**
     * Initializes all live attributes for this element.
     */
    protected void initializeAllLiveAttributes() {
        super.initializeAllLiveAttributes();
        initializeLiveAttributes();
    }

    /**
     * Initializes the live attribute values of this element.
     */
    private void initializeLiveAttributes() {
        in = createLiveAnimatedString(null, SVG_IN_ATTRIBUTE);
    }

    /**
     * <b>DOM</b>: Implements {@link Node#getLocalName()}.
     */
    public String getLocalName() {
        return SVG_FE_TILE_TAG;
    }

    /**
     * <b>DOM</b>: Implements {@link SVGFETileElement#getIn1()}.
     */
    public SVGAnimatedString getIn1() {
        return in;
    }

    /**
     * Returns a new uninitialized instance of this object's class.
     */
    protected Node newNode() {
        return new SVGOMFETileElement();
    }

    /**
     * Returns the table of TraitInformation objects for this element.
     */
    protected DoublyIndexedTable getTraitInformationTable() {
        return xmlTraitInformation;
    }
}

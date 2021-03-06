/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id: BasicLinkLayoutManager.java 627367 2008-02-13 12:03:30Z maxberger $ */

package org.apache.fop.layoutmgr.inline;

import org.apache.fop.datatypes.URISpecification;
import org.apache.fop.fo.flow.BasicLink;
import org.apache.fop.layoutmgr.LayoutManager;
import org.apache.fop.layoutmgr.PageSequenceLayoutManager;
import org.apache.fop.area.inline.InlineArea;
import org.apache.fop.area.Trait;
import org.apache.fop.area.LinkResolver;

/**
 * LayoutManager for the fo:basic-link formatting object
 */
public class BasicLinkLayoutManager extends InlineLayoutManager {
    private BasicLink fobj;

    /**
     * Create an fo:basic-link layout manager.
     *
     * @param node the formatting object that creates the area
     */
    public BasicLinkLayoutManager(BasicLink node) {
        super(node);
        fobj = node;
    }

    /** {@inheritDoc} */
    protected InlineArea createArea(boolean bInlineParent) {
        InlineArea area = super.createArea(bInlineParent);
        setupBasicLinkArea(parentLM, area);
        return area;
    }

    /*
     * Detect internal or external link and add it as an area trait
     *
     * @param parentLM the parent LayoutManager
     * @param area the basic-link's area
     */
    private void setupBasicLinkArea(LayoutManager parentLM, InlineArea area) {
        // internal destinations take precedence:
        if (fobj.hasInternalDestination()) {
            String idref = fobj.getInternalDestination();
            PageSequenceLayoutManager pslm = getPSLM();
            // the INTERNAL_LINK trait is added by the LinkResolver
            // if and when the link is resolved:
            LinkResolver res = new LinkResolver(idref, area);
            res.resolveIDRef(idref, pslm.getFirstPVWithID(idref));
            if (!res.isResolved()) {
                pslm.addUnresolvedArea(idref, res);
            }
        } else if (fobj.hasExternalDestination()) {
            String url = URISpecification.getURL(fobj.getExternalDestination());
            if (url.length() > 0) {
                area.addTrait(Trait.EXTERNAL_LINK, url);
            }
        }
    }
}

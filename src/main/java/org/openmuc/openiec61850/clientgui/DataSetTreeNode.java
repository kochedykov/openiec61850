/*
 * Copyright 2011-17 Fraunhofer ISE, energy & meteo Systems GmbH and other contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.openmuc.openiec61850.clientgui;

import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

import org.openmuc.openiec61850.ClientAssociation;
import org.openmuc.openiec61850.DataSet;
import org.openmuc.openiec61850.ServiceError;

public class DataSetTreeNode extends DefaultMutableTreeNode implements DataTreeNode {

    private static final long serialVersionUID = 7919716359809465616L;

    private final DataSet node;

    public DataSetTreeNode(String name, DataSet node) {
        super(name);
        this.node = node;
    }

    public DataSet getNode() {
        return node;
    }

    @Override
    public void reset(ClientAssociation association) throws ServiceError, IOException {
        if (association != null) {
            association.getDataSetValues(node);
        }
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof DataObjectTreeNode) {
                DataTreeNode child = (DataTreeNode) getChildAt(i);
                child.reset(null);
            }
        }
    }

    @Override
    public void writeValues(ClientAssociation association) throws ServiceError, IOException {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof DataObjectTreeNode) {
                DataTreeNode child = (DataTreeNode) getChildAt(i);
                child.writeValues(null);
            }
        }
        if (association != null) {
            association.setDataSetValues(node);
        }
    }

    @Override
    public BasicDataBind<?> getData() {
        return null;
    }

    @Override
    public boolean writable() {
        return true;
    }

    @Override
    public boolean readable() {
        return true;
    }
}

// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.designer.mapper.model.tableentry;

import java.util.List;

import org.talend.commons.ui.swt.tableviewer.celleditor.IDataBean;
import org.talend.core.model.process.Problem;
import org.talend.designer.abstractmap.model.table.IDataMapTable;
import org.talend.designer.abstractmap.model.tableentry.ITableEntry;
import org.talend.expressionbuilder.test.shadow.Variable;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class DataMapTableEntry implements ITableEntry, IDataBean {

    private List<Variable> vars;

    private String expression;

    private IDataMapTable parent;

    private String name;

    private List<Problem> problems;

    public DataMapTableEntry(IDataMapTable abstractDataMapTable, String expression) {
        super();
        this.parent = abstractDataMapTable;
        this.expression = expression;
    }

    public DataMapTableEntry(IDataMapTable abstractDataMapTable) {
        this(abstractDataMapTable, null);
    }

    /**
     * DOC amaumont ITableEntry constructor comment.
     * 
     * @param abstractDataMapTable
     * @param name, can't be null
     */
    public DataMapTableEntry(IDataMapTable abstractDataMapTable, String name, String expression) {
        super();
        this.parent = abstractDataMapTable;
        this.name = name;
        if (this.name == null) {
            throw new IllegalArgumentException("Name of the TableEntry must not be null !"); //$NON-NLS-1$
        }
        this.expression = expression;
    }

    public String getExpression() {
        return this.expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public IDataMapTable getParent() {
        return this.parent;
    }

    public void setParent(IDataMapTable parent) {
        this.parent = parent;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        if (parent != null) {
            return parent.getName();
        } else {
            throw new IllegalStateException("parent null"); //$NON-NLS-1$
        }
    }

    public List<Problem> getProblems() {
        return this.problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.tableviewer.celleditor.IDataBean#getBeanData()
     */
    public Object getBeanData() {
        return getVars();
    }

    /**
     * Getter for vars.
     * 
     * @return the vars
     */
    public List<Variable> getVars() {
        return this.vars;
    }

    /**
     * Sets the vars.
     * 
     * @param vars the vars to set
     */
    public void setVars(List<Variable> vars) {
        this.vars = vars;
    }
}

// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.dbmap.ui.dnd;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.talend.commons.ui.ws.WindowSystem;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id: TableEntriesTransfer.java 1782 2007-02-03 07:57:38Z bqian $
 * 
 */
public class TableEntriesTransfer extends ByteArrayTransfer {

    private DraggedData draggedData;

    private static final String MAPPER_TABLE_ENTRIES_TYPE_NAME = "MAPPER_TABLE_ENTRIES"; //$NON-NLS-1$

    private static final int MAPPER_TABLE_ENTRIES_ID = registerType(MAPPER_TABLE_ENTRIES_TYPE_NAME);

    private static final TableEntriesTransfer INSTANCE = new TableEntriesTransfer();

    public static TableEntriesTransfer getInstance() {
        return INSTANCE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.dnd.Transfer#getTypeIds()
     */
    @Override
    protected int[] getTypeIds() {
        return new int[] { MAPPER_TABLE_ENTRIES_ID };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.dnd.Transfer#getTypeNames()
     */
    @Override
    protected String[] getTypeNames() {
        return new String[] { MAPPER_TABLE_ENTRIES_TYPE_NAME };
    }

    @Override
    protected void javaToNative(Object object, TransferData transferData) {
        if (WindowSystem.isOSX()) { // FIX for issue 1225
            super.javaToNative(new byte[0], transferData);
        }
    }

    @Override
    protected Object nativeToJava(TransferData transferData) {
        return new byte[0];
    }

    protected DraggedData getDraggedData() {
        return draggedData;
    }

    protected void setDraggedData(DraggedData draggedData) {
        this.draggedData = draggedData;
    }

}

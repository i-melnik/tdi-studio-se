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
package org.talend.sqlbuilder.erdiagram.ui.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.talend.sqlbuilder.erdiagram.ui.commands.DeleteTableCommand;
import org.talend.sqlbuilder.erdiagram.ui.nodes.ErDiagram;
import org.talend.sqlbuilder.erdiagram.ui.nodes.Table;

/**
 * DOC qzhang class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
public class TableEditPolicy extends ComponentEditPolicy {

    @Override
    protected Command createDeleteCommand(GroupRequest deleteRequest) {
        Object parent = getHost().getParent().getModel();
        if (!(parent instanceof ErDiagram)) {
            return null;
        }
        List<Table> nodeList = new ArrayList<Table>();
        for (int i = 0; i < deleteRequest.getEditParts().size(); i++) {
            if (deleteRequest.getEditParts().get(i) instanceof TablePart) {
                nodeList.add((Table) ((TablePart) deleteRequest.getEditParts().get(i)).getModel());
            }
        }
        DeleteTableCommand command = new DeleteTableCommand((ErDiagram) parent, nodeList);
        return command;
    }

}

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
package org.talend.designer.rowgenerator.ui.editor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.gef.commands.Command;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.core.model.metadata.IMetadataColumn;
import org.xml.sax.SAXException;


/**
 *  qzhang  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend-code-templates.xml 1 2007-3-27 上午11:01:03 (星期五, 29 九月 2006) qzhang $
 *
 */
public class MetadataImportXmlCommandExt extends Command {

    private File file;
    private ExtendedTableModel extendedTableModel;

    /**
     *  amaumont MetadataPasteCommand constructor comment.
     * @param extendedTableModel 
     * @param extendedTable
     * @param validAssignableType
     * @param indexStartAdd
     */
    public MetadataImportXmlCommandExt(ExtendedTableModel extendedTableModel, File file) {
        super();
        this.file = file;
        this.extendedTableModel = extendedTableModel;
    }

    /* (non-Java)
     * @see org.talend.commons.ui.command.CommonCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void execute() {
        try {
            MetadataSchemaExt ext = new MetadataSchemaExt();
            List<IMetadataColumn> metadataColumns = ext.initializeAllColumns(file);
            extendedTableModel.addAll(metadataColumns);

        } catch (ParserConfigurationException e) {
            ExceptionHandler.process(e);
        } catch (SAXException e) {
            ExceptionHandler.process(e);
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
    }
}

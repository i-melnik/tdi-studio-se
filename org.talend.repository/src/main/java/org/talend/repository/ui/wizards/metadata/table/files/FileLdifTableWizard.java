// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.repository.ui.wizards.metadata.table.files;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.swt.dialogs.ErrorDialogWidthDetailArea;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.repository.i18n.Messages;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.ProxyRepositoryFactory;
import org.talend.repository.ui.wizards.RepositoryWizard;

/**
 * DOC cantoine class global comment.
 * 
 * $Id: FileLdifTableWizard.java 88 2006-10-04 08:50:39 +0000 (mer., 04 oct. 2006) cantoine $
 * 
 */
public class FileLdifTableWizard extends RepositoryWizard implements INewWizard {

    private static Logger log = Logger.getLogger(FileLdifTableWizard.class);

    private FileTableWizardPage tableWizardpage;

    private ConnectionItem connectionItem;

    private MetadataTable metadataTable;

    /**
     * Constructor for TableWizard.
     * 
     * @param ISelection
     */
    @SuppressWarnings("unchecked")
    public FileLdifTableWizard(IWorkbench workbench, boolean creation, ConnectionItem connectionItem, MetadataTable metadataTable) {
        super(workbench, creation);
        this.connectionItem = connectionItem;
        this.metadataTable = metadataTable;
        setNeedsProgressMonitor(true);

        isRepositoryObjectEditable();
        initLockStrategy();
    }

    /**
     * Adding the page to the wizard.
     */

    public void addPages() {
        setWindowTitle(Messages.getString("SchemaWizard.windowTitle"));

        tableWizardpage = new FileTableWizardPage(connectionItem, metadataTable, isRepositoryObjectEditable());

        if (creation) {
            tableWizardpage.setTitle(Messages.getString("FileTableWizardPage.titleCreate", connectionItem.getProperty().getLabel()));
            tableWizardpage.setDescription(Messages.getString("FileTableWizardPage.descriptionCreate"));
            tableWizardpage.setPageComplete(false);
        } else {
            tableWizardpage.setTitle(Messages.getString("FileTableWizardPage.titleUpdate", metadataTable.getLabel()));
            tableWizardpage.setDescription(Messages.getString("FileTableWizardPage.descriptionUpdate"));
            tableWizardpage.setPageComplete(isRepositoryObjectEditable());
        }
        addPage(tableWizardpage);
    }

    /**
     * This method determine if the 'Finish' button is enable This method is called when 'Finish' button is pressed in
     * the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {
        if (tableWizardpage.isPageComplete()) {
            IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
            try {
                factory.save(repositoryObject.getProperty().getItem());
                closeLockStrategy();
            } catch (PersistenceException e) {
                String detailError = e.toString();
                new ErrorDialogWidthDetailArea(getShell(), PID, Messages.getString("CommonWizard.persistenceException"), detailError);
                log.error(Messages.getString("CommonWizard.persistenceException") + "\n" + detailError);
            }
            return true;
        } else {
            return false;
        }

    }

    /**
     * We will accept the selection in the workbench to see if we can initialize from it.
     * 
     * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
     */
    public void init(final IWorkbench workbench, final IStructuredSelection selection) {
        this.selection = selection;
    }

}

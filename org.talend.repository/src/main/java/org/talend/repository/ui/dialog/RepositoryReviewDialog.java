// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.talend.core.model.metadata.MetadataTable;
import org.talend.core.model.metadata.Query;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.designerproperties.RepositoryToComponentProperty;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNode.ENodeType;
import org.talend.repository.model.RepositoryNode.EProperties;
import org.talend.repository.ui.views.IRepositoryView;
import org.talend.repository.ui.views.RepositoryContentProvider;
import org.talend.repository.ui.views.RepositoryView;

/**
 * bqian check the content of the repository view. <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
public class RepositoryReviewDialog extends Dialog {

    ERepositoryObjectType type;

    String repositoryType;

    private FakeRepositoryView repositoryView;

    private RepositoryNode result;

    ITypeProcessor typeProcessor;

    /**
     * DOC bqian RepositoryReviewDialog constructor comment.
     * 
     * @param parentShell
     * @param type support ERepositoryObjectType.PROCESS -> process <br>
     * ERepositoryObjectType.METADATA --> Repository <br>
     * ERepositoryObjectType.METADATA_CON_TABLE --> Schema <br>
     * ERepositoryObjectType.METADATA_CON_QUERY --> Query <br>
     * 
     * @param repositoryType String repositoryType = elem.getElementParameter(paramName).getRepositoryValue();<br>
     * see DynamicComposite.updateRepositoryListExtra().<br>
     * 
     * 
     */
    public RepositoryReviewDialog(Shell parentShell, ERepositoryObjectType type, String repositoryType) {
        super(parentShell);
        setShellStyle(SWT.SHELL_TRIM | SWT.APPLICATION_MODAL | getDefaultOrientation());
        this.type = type;
        this.repositoryType = repositoryType;
        typeProcessor = createTypeProcessor();
    }

    /**
     * bqian create the correct TypeProcessor according to the type.
     * 
     * @return
     */
    private ITypeProcessor createTypeProcessor() {
        if (type == ERepositoryObjectType.PROCESS) {
            return new JobTypeProcessor();
        }
        if (type == ERepositoryObjectType.METADATA) {
            return new RepositoryTypeProcessor(repositoryType);
        }

        if (type == ERepositoryObjectType.METADATA_CON_TABLE) {
            return new SchemaTypeProcessor(repositoryType);
        }
        if (type == ERepositoryObjectType.METADATA_CON_QUERY) {
            return new QueryTypeProcessor(repositoryType);
        }
        throw new IllegalArgumentException("illegal argument:" + type);
    }

    /**
     * Configures the shell
     * 
     * @param shell the shell
     */
    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        // Set the title bar text and the size
        shell.setText("Repository Content");
        shell.setSize(500, 400);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite content = (Composite) super.createDialogArea(parent);

        IRepositoryView view = RepositoryView.show();
        repositoryView = new FakeRepositoryView(typeProcessor, type, repositoryType);
        try {
            repositoryView.init(view.getViewSite());
        } catch (PartInitException e) {
            e.printStackTrace();
        }

        repositoryView.createPartControl(content);
        repositoryView.refresh();
        repositoryView.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                boolean highlightOKButton = true;
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                if (selection == null || selection.size() != 1) {
                    highlightOKButton = false;
                } else {
                    RepositoryNode node = (RepositoryNode) selection.getFirstElement();
                    ERepositoryObjectType t = (ERepositoryObjectType) node.getProperties(EProperties.CONTENT_TYPE);
                    if (node.getType() != ENodeType.REPOSITORY_ELEMENT) {
                        highlightOKButton = false;
                    } else if (!typeProcessor.isSelectionValid(node)) {
                        highlightOKButton = false;
                    }
                }
                getButton(IDialogConstants.OK_ID).setEnabled(highlightOKButton);
            }
        });
        return content;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        super.createButtonsForButtonBar(parent);
        getButton(IDialogConstants.OK_ID).setEnabled(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        IStructuredSelection selection = (IStructuredSelection) repositoryView.getViewer().getSelection();
        result = (RepositoryNode) selection.getFirstElement();
        super.okPressed();
    }

    public RepositoryNode getResult() {
        return result;
    }
}

/**
 * DOC bqian class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
class FakeRepositoryView extends RepositoryView {

    ERepositoryObjectType type;

    private String repositoryType;

    ITypeProcessor typeProcessor;

    /**
     * DOC bqian SnippetsDialogTrayView constructor comment.
     * 
     * @param typeProcessor
     * 
     * @param type
     * @param type
     */
    public FakeRepositoryView(ITypeProcessor typeProcessor, ERepositoryObjectType type, String repositoryValue) {
        super();
        this.typeProcessor = typeProcessor;
        this.type = type;
        this.repositoryType = repositoryValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.views.RepositoryView#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        ViewerFilter filter = typeProcessor.makeFilter();
        if (filter != null) {
            getViewer().addFilter(filter);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.views.RepositoryView#refresh(java.lang.Object)
     */
    @Override
    public void refresh(Object object) {
        refresh();
        // viewer.refresh(object);
        if (object != null) {
            // getViewer().setExpandedState(object, true);
            getViewer().expandToLevel(object, AbstractTreeViewer.ALL_LEVELS);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.views.RepositoryView#refresh()
     */
    @Override
    public void refresh() {
        super.refresh();
        // getViewer().setInput(this.getViewSite());
        getViewer().setInput(getInput());

    }

    private RepositoryNode getInput() {
        RepositoryContentProvider contentProvider = (RepositoryContentProvider) getViewer().getContentProvider();
        return typeProcessor.getInputRoot(contentProvider);
    }

    @Override
    protected void makeActions() {
    }

    @Override
    protected void hookContextMenu() {
    }

    @Override
    protected void contributeToActionBars() {
    }

    @Override
    protected void initDragAndDrop() {
    }

    @Override
    protected void hookDoubleClickAction() {
    }

}

/**
 * bqian decouple the process logic of JOB, REPOSITORY, SCHEMA, QUERY <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
interface ITypeProcessor {

    boolean isSelectionValid(RepositoryNode node);

    RepositoryNode getInputRoot(RepositoryContentProvider contentProvider);

    ViewerFilter makeFilter();

}

/**
 * bqian TypeProcessor for Job. <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
class JobTypeProcessor implements ITypeProcessor {

    public RepositoryNode getInputRoot(RepositoryContentProvider contentProvider) {
        return contentProvider.getProcessNode();
    }

    public boolean isSelectionValid(RepositoryNode node) {
        if (node.getProperties(EProperties.CONTENT_TYPE) == ERepositoryObjectType.PROCESS) {
            return true;
        }
        return false;
        // else {
        // if (node.getProperties(EProperties.CONTENT_TYPE) != ERepositoryObjectType.METADATA_CON_TABLE) {
        // highlightOKButton = false;
        // }
        // }

    }

    public ViewerFilter makeFilter() {
        return null;
    }
}

/**
 * bqian TypeProcessor for Repository. <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
class RepositoryTypeProcessor implements ITypeProcessor {

    String repositoryType;

    /**
     * DOC bqian RepositoryTypeProcessor constructor comment.
     * 
     * @param repositoryType
     */
    public RepositoryTypeProcessor(String repositoryType) {
        this.repositoryType = repositoryType;
    }

    public RepositoryNode getInputRoot(RepositoryContentProvider contentProvider) {
        if (repositoryType.equals("DELIMITED")) {
            return contentProvider.getMetadataFileNode();
        }
        if (repositoryType.equals("POSITIONAL")) {
            return contentProvider.getMetadataFilePositionalNode();
        }
        if (repositoryType.equals("REGEX")) {
            return contentProvider.getMetadataFileRegexpNode();
        }
        if (repositoryType.equals("XML")) {
            return contentProvider.getMetadataFileXmlNode();
        }
        if (repositoryType.equals("LDIF")) {
            return contentProvider.getMetadataFileLdifNode();
        }
        if (repositoryType.equals("GENERIC")) {
            return contentProvider.getMetadataGenericSchemaNode();
        }
        if (repositoryType.equals("LDAP")) {
            return contentProvider.getMetadataLDAPSchemaNode();
        }
        if (repositoryType.equals("WSDL")) {
            return contentProvider.getMetadataWSDLSchemaNode();
        }

        if (repositoryType.startsWith("DATABASE")) { //$NON-NLS-1$
            return contentProvider.getMetadataConNode();
        }
        return null;
    }

    public boolean isSelectionValid(RepositoryNode node) {
        if (node.getProperties(EProperties.CONTENT_TYPE) == ERepositoryObjectType.PROCESS) {
            return true;
        }
        return true;
    }

    public ViewerFilter makeFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                // if (repositoryType.startsWith("DATABASE") && repositoryType.contains(":")) {
                RepositoryNode node = (RepositoryNode) element;
                if (node.getObject() == null || node.getObject().getProperty().getItem() == null) {
                    return false;
                }
                if (node.getObject() instanceof MetadataTable) {
                    return false;
                }
                Item item = node.getObject().getProperty().getItem();
                if (item instanceof FolderItem) {
                    return true;
                }

                if (repositoryType.startsWith("DATABASE")) { //$NON-NLS-1$
                    ConnectionItem connectionItem = (ConnectionItem) item;
                    Connection connection = connectionItem.getConnection();
                    String currentDbType = (String) RepositoryToComponentProperty.getValue(connection, "TYPE"); //$NON-NLS-1$
                    if (repositoryType.contains(":")) { // database
                        // is
                        // specified
                        // //$NON-NLS-1$
                        String neededDbType = repositoryType.substring(repositoryType.indexOf(":") + 1); //$NON-NLS-1$
                        if (!neededDbType.equals(currentDbType)) {
                            return false;
                        }
                    }
                }
                return true;
            }
        };
    }
}

/**
 * bqian TypeProcessor for Schema. <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
class SchemaTypeProcessor implements ITypeProcessor {

    String repositoryType;

    /**
     * DOC bqian RepositoryTypeProcessor constructor comment.
     * 
     * @param repositoryType
     */
    public SchemaTypeProcessor(String repositoryType) {
        this.repositoryType = repositoryType;
    }

    public RepositoryNode getInputRoot(RepositoryContentProvider contentProvider) {
        List<RepositoryNode> container = new ArrayList<RepositoryNode>();
        container.add(contentProvider.getMetadataFileNode());
        container.add(contentProvider.getMetadataFilePositionalNode());
        container.add(contentProvider.getMetadataFileRegexpNode());
        container.add(contentProvider.getMetadataFileXmlNode());
        container.add(contentProvider.getMetadataFileLdifNode());
        container.add(contentProvider.getMetadataGenericSchemaNode());
        container.add(contentProvider.getMetadataLDAPSchemaNode());
        container.add(contentProvider.getMetadataWSDLSchemaNode());
        container.add(contentProvider.getMetadataConNode());

        RepositoryNode node = new RepositoryNode(null, null, null);
        node.getChildren().addAll(container);
        return node;
    }

    public boolean isSelectionValid(RepositoryNode node) {
        if (node.getObject() instanceof MetadataTable) {
            return true;
        }
        return false;
    }

    public ViewerFilter makeFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                // if (repositoryType.startsWith("DATABASE") && repositoryType.contains(":")) {
                RepositoryNode node = (RepositoryNode) element;
                if (node.getObject() != null && (node.getObject() instanceof Query)) {
                    return false;
                }
                return true;
            }
        };
    }
}

/**
 * bqian TypeProcessor for Query. <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
class QueryTypeProcessor implements ITypeProcessor {

    String repositoryType;

    /**
     * bqian RepositoryTypeProcessor constructor comment.
     * 
     * @param repositoryType
     */
    public QueryTypeProcessor(String repositoryType) {
        this.repositoryType = repositoryType;
    }

    public RepositoryNode getInputRoot(RepositoryContentProvider contentProvider) {
        return contentProvider.getMetadataConNode();
    }

    public boolean isSelectionValid(RepositoryNode node) {
        if (node.getObject() instanceof Query) {
            return true;
        }
        return false;
    }

    public ViewerFilter makeFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                // if (repositoryType.startsWith("DATABASE") && repositoryType.contains(":")) {
                RepositoryNode node = (RepositoryNode) element;
                if (node.getObject() != null && (node.getObject() instanceof MetadataTable)) {
                    return false;
                }
                return true;
            }
        };
    }
}

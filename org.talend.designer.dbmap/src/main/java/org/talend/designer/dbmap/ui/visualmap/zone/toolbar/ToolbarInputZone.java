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
package org.talend.designer.dbmap.ui.visualmap.zone.toolbar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolItem;
import org.talend.commons.ui.image.EImage;
import org.talend.designer.dbmap.i18n.Messages;
import org.talend.designer.dbmap.managers.MapperManager;
import org.talend.designer.dbmap.ui.visualmap.zone.Zone;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id: ToolbarInputZone.java 1782 2007-02-03 07:57:38Z bqian $
 * 
 */
public class ToolbarInputZone extends ToolbarZone {

    private ToolItem addAlias;

    private ToolItem removeAlias;

    public static final String MINIMIZE_TOOLTIP = Messages.getString("ToolbarInputZone.minimizeTooltip"); //$NON-NLS-1$

    public static final String RESTORE_TOOLTIP = Messages.getString("ToolbarInputZone.restoreTooltip"); //$NON-NLS-1$

    private static final String MOVE_UP_TOOLTIP = Messages.getString("ToolbarInputZone.moveupTooltip"); //$NON-NLS-1$

    private static final String MOVE_DOWN_TOOLTIP = Messages.getString("ToolbarInputZone.movedownTooltip"); //$NON-NLS-1$

    /**
     * DOC amaumont MatadataToolbarEditor constructor comment.
     * 
     * @param parent
     * @param style
     * @param manager
     * @param metadatEditorView
     */
    public ToolbarInputZone(Composite parent, int style, MapperManager manager) {
        super(parent, style, manager);
        createComponents();
        addListeners();
    }

    /**
     * DOC amaumont Comment method "createComponents".
     */
    private void createComponents() {
        addAlias = new ToolItem(getToolBarActions(), SWT.PUSH);
        addAlias.setToolTipText(Messages.getString("ToolbarInputZone.widgetTooltip.addAlias")); //$NON-NLS-1$
        addAlias.setImage(org.talend.commons.ui.image.ImageProvider.getImage(org.talend.commons.ui.image.ImageProvider
                .getImageDesc(EImage.ADD_ICON)));

        removeAlias = new ToolItem(getToolBarActions(), SWT.PUSH);
        removeAlias.setEnabled(false);
        removeAlias.setImage(org.talend.commons.ui.image.ImageProvider
                .getImage(org.talend.commons.ui.image.ImageProvider.getImageDesc(EImage.MINUS_ICON)));
        removeAlias.setToolTipText(Messages.getString("ToolbarInputZone.widgetTooltip.removeAlias")); //$NON-NLS-1$

        addCommonsComponents();

    }

    /**
     * DOC amaumont Comment method "addListeners".
     */
    private void addListeners() {
        // final UIManager uiManager = getMapperManager().getUiManager();
        addAlias.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                getMapperManager().addInputAliasTable();
            }

        });

        removeAlias.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                getMapperManager().removeSelectedAliasInputTable();
            }

        });

    }

    public String getMinimizeTooltipText() {
        return MINIMIZE_TOOLTIP;
    }

    public String getRestoreTooltipText() {
        return RESTORE_TOOLTIP;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.dbmap.ui.visualmap.zone.ToolbarZone#getZone()
     */
    @Override
    public Zone getZone() {
        return Zone.INPUTS;
    }

    public String getMoveUpTooltipText() {
        return MOVE_UP_TOOLTIP;
    }

    public String getMoveDownTooltipText() {
        return MOVE_DOWN_TOOLTIP;
    }

    /**
     * DOC amaumont Comment method "setEnabledRemoveTableButton".
     * 
     * @param b
     */
    public void setEnabledRemoveAliasButton(boolean enabled) {
        removeAlias.setEnabled(enabled);
    }

}

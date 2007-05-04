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
package org.talend.repository.model.migration;

import java.util.Arrays;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.Project;
import org.talend.core.model.migration.AbstractMigrationTask;
import org.talend.core.model.migration.IProjectMigrationTask;
import org.talend.repository.model.migration.conversions.AddPropertyUniqueKeyFortUniqRowConversion;
import org.talend.repository.model.migration.conversions.IComponentConversion;
import org.talend.repository.model.migration.conversions.RemovePropertyComponentConversion;
import org.talend.repository.model.migration.filters.IComponentFilter;
import org.talend.repository.model.migration.filters.NameComponentFilter;

/**
 * Use to rename tDB(Input|Output|SQLRow) into tMysql(Input|Output|Row). Related bug 540.
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class UpgradetUniqRowMigrationTask extends AbstractMigrationTask implements IProjectMigrationTask {

    public boolean execute(Project project) {
        if (project.getLanguage() != ECodeLanguage.PERL) {
            return true;
        }
        try {
            IComponentFilter filter1 = new NameComponentFilter("tUniqRow"); //$NON-NLS-1$

            IComponentConversion removeProperty = new RemovePropertyComponentConversion("CASE_SENSITIVE"); //$NON-NLS-1$
            IComponentConversion addProperty = new AddPropertyUniqueKeyFortUniqRowConversion("UNIQUE_KEY", "TABLE"); //$NON-NLS-1$ //$NON-NLS-2$

            ModifyComponentsAction.searchAndModify(filter1, Arrays.<IComponentConversion> asList(removeProperty, addProperty));

            return true;
        } catch (Exception e) {
            ExceptionHandler.process(e);
            return false;
        }
    }
}

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
package org.talend.designer.components.model;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.SystemException;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class ComponentIconLoading {

    private File folder;

    private URL folderUrl;

    private ImageDescriptor image32;

    private ImageDescriptor image24;

    private ImageDescriptor image16;

    /**
     * DOC smallet ComponentIconLoading constructor comment.
     * 
     * @param folder
     */
    public ComponentIconLoading(File folder) {
        super();
        this.folder = folder;
        try {
            this.folderUrl = folder.toURL();
        } catch (MalformedURLException e) {
            ExceptionHandler.process(new SystemException("Cannot load component icon " + folder.getName(), e)); //$NON-NLS-1$
        }
    }

    public ImageDescriptor getImage32() {
        if (image32 == null) {
            image32 = getImage(ComponentFilesNaming.getInstance().getIcon32FileName(folder.getName()));
        }
        return image32;
    }

    public ImageDescriptor getImage24() {
        if (image24 == null) {
            File file24 = new File(folder, ComponentFilesNaming.getInstance().getIcon24FileName(folder.getName()));
            if (file24.exists()) {
                image24 = getImage(ComponentFilesNaming.getInstance().getIcon24FileName(folder.getName()));
            } else {
                // image24 = getImage32(); // Temp code
                image24 = ImageDescriptor.createFromImageData(image32.getImageData().scaledTo(24, 24));
            }
        }
        return image24;
    }

    public ImageDescriptor getImage16() {
        if (image16 == null) {
            File file16 = new File(folder, ComponentFilesNaming.getInstance().getIcon16FileName(folder.getName()));
            if (file16.exists()) {
                image16 = getImage(ComponentFilesNaming.getInstance().getIcon16FileName(folder.getName()));
            } else {
                image16 = ImageDescriptor.createFromImageData(image32.getImageData().scaledTo(16, 16));
            }
        }
        return image16;
    }

    private ImageDescriptor getImage(String name) {
        URL url;
        try {
            url = new URL(folderUrl + name);
            return ImageDescriptor.createFromURL(url);
        } catch (MalformedURLException e) {
            ExceptionHandler.process(new SystemException("Cannot load component icon " + name, e)); //$NON-NLS-1$
            return ImageProvider.getImageDesc(EImage.DEFAULT_IMAGE);
        }
    }
}

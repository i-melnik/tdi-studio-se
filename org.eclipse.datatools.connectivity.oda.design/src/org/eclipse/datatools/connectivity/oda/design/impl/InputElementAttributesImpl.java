/**
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: InputElementAttributesImpl.java,v 1.3 2006/02/12 06:45:56 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputElementUIHints;
import org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle;
import org.eclipse.datatools.connectivity.oda.design.ScalarValueChoices;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Element Attributes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#getDefaultScalarValue <em>Default Scalar Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#isEditable <em>Editable</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#isOptional <em>Optional</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#isMasksValue <em>Masks Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#getStaticValueChoices <em>Static Value Choices</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#getDynamicValueChoices <em>Dynamic Value Choices</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#getUiHints <em>Ui Hints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputElementAttributesImpl extends EObjectImpl implements InputElementAttributes
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getDefaultScalarValue() <em>Default Scalar Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultScalarValue()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_SCALAR_VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDefaultScalarValue() <em>Default Scalar Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultScalarValue()
     * @generated
     * @ordered
     */
    protected String m_defaultScalarValue = DEFAULT_SCALAR_VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #isEditable() <em>Editable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEditable()
     * @generated
     * @ordered
     */
    protected static final boolean EDITABLE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isEditable() <em>Editable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEditable()
     * @generated
     * @ordered
     */
    protected boolean m_editable = EDITABLE_EDEFAULT;

    /**
     * This is true if the Editable attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_editableESet = false;

    /**
     * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected static final boolean OPTIONAL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected boolean m_optional = OPTIONAL_EDEFAULT;

    /**
     * This is true if the Optional attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_optionalESet = false;

    /**
     * The default value of the '{@link #isMasksValue() <em>Masks Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isMasksValue()
     * @generated
     * @ordered
     */
    protected static final boolean MASKS_VALUE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isMasksValue() <em>Masks Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isMasksValue()
     * @generated
     * @ordered
     */
    protected boolean m_masksValue = MASKS_VALUE_EDEFAULT;

    /**
     * This is true if the Masks Value attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_masksValueESet = false;

    /**
     * The cached value of the '{@link #getStaticValueChoices() <em>Static Value Choices</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStaticValueChoices()
     * @generated
     * @ordered
     */
    protected ScalarValueChoices m_staticValueChoices = null;

    /**
     * The cached value of the '{@link #getDynamicValueChoices() <em>Dynamic Value Choices</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDynamicValueChoices()
     * @generated
     * @ordered
     */
    protected DynamicValuesQuery m_dynamicValueChoices = null;

    /**
     * The cached value of the '{@link #getUiHints() <em>Ui Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUiHints()
     * @generated
     * @ordered
     */
    protected InputElementUIHints m_uiHints = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InputElementAttributesImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass()
    {
        return DesignPackage.eINSTANCE.getInputElementAttributes();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#hasValueChoices()
     * @generated NOT
     */
    public boolean hasValueChoices()
    {
        return( getStaticValueChoices() != null ||
                getDynamicValueChoices() != null );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#setUiPromptStyle(org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle)
     * @generated NOT
     */
    public void setUiPromptStyle( InputPromptControlStyle value )
    {
        // sets attribute in current UIHints, if exists;
        // otherwise, creates a new one
        InputElementUIHints uiHints = getUiHints();
        boolean hasNoUIHints = ( uiHints == null );
        if( hasNoUIHints )
            uiHints = DesignFactory.eINSTANCE.createInputElementUIHints();
        uiHints.setPromptStyle( value );
        
        if( hasNoUIHints )
            setUiHints( uiHints );       
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDefaultScalarValue()
    {
        return m_defaultScalarValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefaultScalarValue( String newDefaultScalarValue )
    {
        String oldDefaultScalarValue = m_defaultScalarValue;
        m_defaultScalarValue = newDefaultScalarValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_SCALAR_VALUE, oldDefaultScalarValue, m_defaultScalarValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isEditable()
    {
        return m_editable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEditable( boolean newEditable )
    {
        boolean oldEditable = m_editable;
        m_editable = newEditable;
        boolean oldEditableESet = m_editableESet;
        m_editableESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE, oldEditable, m_editable, !oldEditableESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetEditable()
    {
        boolean oldEditable = m_editable;
        boolean oldEditableESet = m_editableESet;
        m_editable = EDITABLE_EDEFAULT;
        m_editableESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE, oldEditable, EDITABLE_EDEFAULT, oldEditableESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetEditable()
    {
        return m_editableESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isOptional()
    {
        return m_optional;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOptional( boolean newOptional )
    {
        boolean oldOptional = m_optional;
        m_optional = newOptional;
        boolean oldOptionalESet = m_optionalESet;
        m_optionalESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL, oldOptional, m_optional, !oldOptionalESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetOptional()
    {
        boolean oldOptional = m_optional;
        boolean oldOptionalESet = m_optionalESet;
        m_optional = OPTIONAL_EDEFAULT;
        m_optionalESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL, oldOptional, OPTIONAL_EDEFAULT, oldOptionalESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetOptional()
    {
        return m_optionalESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isMasksValue()
    {
        return m_masksValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMasksValue( boolean newMasksValue )
    {
        boolean oldMasksValue = m_masksValue;
        m_masksValue = newMasksValue;
        boolean oldMasksValueESet = m_masksValueESet;
        m_masksValueESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE, oldMasksValue, m_masksValue, !oldMasksValueESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetMasksValue()
    {
        boolean oldMasksValue = m_masksValue;
        boolean oldMasksValueESet = m_masksValueESet;
        m_masksValue = MASKS_VALUE_EDEFAULT;
        m_masksValueESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE, oldMasksValue, MASKS_VALUE_EDEFAULT, oldMasksValueESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetMasksValue()
    {
        return m_masksValueESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScalarValueChoices getStaticValueChoices()
    {
        return m_staticValueChoices;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStaticValueChoices( ScalarValueChoices newStaticValueChoices, NotificationChain msgs )
    {
        ScalarValueChoices oldStaticValueChoices = m_staticValueChoices;
        m_staticValueChoices = newStaticValueChoices;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES, oldStaticValueChoices, newStaticValueChoices);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStaticValueChoices( ScalarValueChoices newStaticValueChoices )
    {
        if (newStaticValueChoices != m_staticValueChoices)
        {
            NotificationChain msgs = null;
            if (m_staticValueChoices != null)
                msgs = ((InternalEObject)m_staticValueChoices).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES, null, msgs);
            if (newStaticValueChoices != null)
                msgs = ((InternalEObject)newStaticValueChoices).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES, null, msgs);
            msgs = basicSetStaticValueChoices(newStaticValueChoices, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES, newStaticValueChoices, newStaticValueChoices));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DynamicValuesQuery getDynamicValueChoices()
    {
        return m_dynamicValueChoices;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDynamicValueChoices( DynamicValuesQuery newDynamicValueChoices, NotificationChain msgs )
    {
        DynamicValuesQuery oldDynamicValueChoices = m_dynamicValueChoices;
        m_dynamicValueChoices = newDynamicValueChoices;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES, oldDynamicValueChoices, newDynamicValueChoices);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDynamicValueChoices( DynamicValuesQuery newDynamicValueChoices )
    {
        if (newDynamicValueChoices != m_dynamicValueChoices)
        {
            NotificationChain msgs = null;
            if (m_dynamicValueChoices != null)
                msgs = ((InternalEObject)m_dynamicValueChoices).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES, null, msgs);
            if (newDynamicValueChoices != null)
                msgs = ((InternalEObject)newDynamicValueChoices).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES, null, msgs);
            msgs = basicSetDynamicValueChoices(newDynamicValueChoices, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES, newDynamicValueChoices, newDynamicValueChoices));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputElementUIHints getUiHints()
    {
        return m_uiHints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUiHints( InputElementUIHints newUiHints, NotificationChain msgs )
    {
        InputElementUIHints oldUiHints = m_uiHints;
        m_uiHints = newUiHints;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS, oldUiHints, newUiHints);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUiHints( InputElementUIHints newUiHints )
    {
        if (newUiHints != m_uiHints)
        {
            NotificationChain msgs = null;
            if (m_uiHints != null)
                msgs = ((InternalEObject)m_uiHints).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS, null, msgs);
            if (newUiHints != null)
                msgs = ((InternalEObject)newUiHints).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS, null, msgs);
            msgs = basicSetUiHints(newUiHints, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS, newUiHints, newUiHints));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove( InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs )
    {
        if (featureID >= 0)
        {
            switch (eDerivedStructuralFeatureID(featureID, baseClass))
            {
                case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES:
                    return basicSetStaticValueChoices(null, msgs);
                case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES:
                    return basicSetDynamicValueChoices(null, msgs);
                case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS:
                    return basicSetUiHints(null, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet( EStructuralFeature eFeature, boolean resolve )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_SCALAR_VALUE:
                return getDefaultScalarValue();
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE:
                return isEditable() ? Boolean.TRUE : Boolean.FALSE;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL:
                return isOptional() ? Boolean.TRUE : Boolean.FALSE;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE:
                return isMasksValue() ? Boolean.TRUE : Boolean.FALSE;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES:
                return getStaticValueChoices();
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES:
                return getDynamicValueChoices();
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS:
                return getUiHints();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet( EStructuralFeature eFeature, Object newValue )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_SCALAR_VALUE:
                setDefaultScalarValue((String)newValue);
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE:
                setEditable(((Boolean)newValue).booleanValue());
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL:
                setOptional(((Boolean)newValue).booleanValue());
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE:
                setMasksValue(((Boolean)newValue).booleanValue());
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES:
                setStaticValueChoices((ScalarValueChoices)newValue);
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES:
                setDynamicValueChoices((DynamicValuesQuery)newValue);
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS:
                setUiHints((InputElementUIHints)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset( EStructuralFeature eFeature )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_SCALAR_VALUE:
                setDefaultScalarValue(DEFAULT_SCALAR_VALUE_EDEFAULT);
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE:
                unsetEditable();
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL:
                unsetOptional();
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE:
                unsetMasksValue();
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES:
                setStaticValueChoices((ScalarValueChoices)null);
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES:
                setDynamicValueChoices((DynamicValuesQuery)null);
                return;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS:
                setUiHints((InputElementUIHints)null);
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet( EStructuralFeature eFeature )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_SCALAR_VALUE:
                return DEFAULT_SCALAR_VALUE_EDEFAULT == null ? m_defaultScalarValue != null : !DEFAULT_SCALAR_VALUE_EDEFAULT.equals(m_defaultScalarValue);
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE:
                return isSetEditable();
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL:
                return isSetOptional();
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE:
                return isSetMasksValue();
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES:
                return m_staticValueChoices != null;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES:
                return m_dynamicValueChoices != null;
            case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS:
                return m_uiHints != null;
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString()
    {
        if ( eIsProxy() ) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (defaultScalarValue: "); //$NON-NLS-1$
        result.append(m_defaultScalarValue);
        result.append(", editable: "); //$NON-NLS-1$
        if (m_editableESet) result.append(m_editable); else result.append("<unset>"); //$NON-NLS-1$
        result.append(", optional: "); //$NON-NLS-1$
        if (m_optionalESet) result.append(m_optional); else result.append("<unset>"); //$NON-NLS-1$
        result.append(", masksValue: "); //$NON-NLS-1$
        if (m_masksValueESet) result.append(m_masksValue); else result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //InputElementAttributesImpl

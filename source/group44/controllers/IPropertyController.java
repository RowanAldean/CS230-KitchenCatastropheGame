package group44.controllers;

import group44.annotations.Editable;
import group44.models.PropertyInfo;
import group44.models.PropertyInfo.TypeInfo;

/**
 * An interface for {@link PropertyController}.
 *
 * @author Tomas Svejnoha.
 * @version 1.0
 */
public interface IPropertyController {
    /**
     * Sets currently edited object.
     *
     * @param object The edited object.
     * @throws IllegalArgumentException when object is <code>null</code>.
     */
    void setActiveObject(Object object) throws IllegalArgumentException;

    /**
     * Returns an array of {@link Editable} properties on currently edited
     * object.
     *
     * @return the {@link Editable} properties.
     */
    PropertyInfo[] getProperties();

    /**
     * Sets value of {@link Editable} property.
     *
     * @param info Information about the property.
     * @throws NoSuchFieldException when property is not found.
     * @throws SecurityException    when property can't be set.
     * @throws ClassCastException   when type is inconsistent with {@link TypeInfo}.
     */
    void setPropertyValue(PropertyInfo info)
            throws NoSuchFieldException, SecurityException, ClassCastException;
}

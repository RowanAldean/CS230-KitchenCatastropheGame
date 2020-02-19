package group44.controllers;

import group44.models.PropertyInfo;

public interface IPropertyController {
    void setActiveObject(Object object) throws IllegalArgumentException;

    PropertyInfo[] getProperties();
    void setPropertyValue(PropertyInfo info) throws NoSuchFieldException, SecurityException;
}

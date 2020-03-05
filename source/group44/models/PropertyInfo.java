package group44.models;

import group44.annotations.Editable;

/**
 * Holds information about {@link Editable} fields in level objects.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class PropertyInfo {
    /**
     * Name of the property.
     */
    private String name;
    /**
     * Value of the property.
     */
    private Object value;
    /**
     * Data type of the property.
     */
    private TypeInfo typeInfo;

    /**
     * Creates a new {@link PropertyInfo}.
     *
     * @param name     Name of the property.
     * @param value    Value of the property.
     * @param typeInfo Data type of the property.
     */
    public PropertyInfo(String name, Object value, TypeInfo typeInfo) {
        this.name = name;
        this.value = value;
        this.typeInfo = typeInfo;
    }

    /**
     * Returns the name of the property.
     *
     * @return property name.
     */
    public String getKey() {
        return name;
    }

    /**
     * Returns a value of the property.
     *
     * @return property value.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Sets the value of the property.
     *
     * @param value new value.
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Returns the type of the property.
     *
     * @return the property type.
     */
    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    public enum TypeInfo {
        String, Int, Boolean, CollectableItem, KeyType, MovableObject, Teleporter
    }
}

package group44.models;

public class PropertyInfo {
    private String key;
    private Object value;
    private TypeInfo typeInfo;

    public PropertyInfo(String key, Object value, TypeInfo typeInfo) {
        this.key = key;
        this.value = value;
        this.typeInfo = typeInfo;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    public enum TypeInfo {
        String, Int, Boolean, CollectableItem, KeyType, MovableObject, Teleporter
    }
}

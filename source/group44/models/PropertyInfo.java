package group44.models;

public class PropertyInfo {
    private String key;
    private String value;
    private TypeInfo typeInfo;

    public PropertyInfo(String key, String value, TypeInfo typeInfo) {
        this.key = key;
        this.value = value;
        this.typeInfo = typeInfo;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    public enum TypeInfo {
        String, Int, Boolean, CollectableItem, KeyType, MovableObject, Teleporter
    }
}

package group44.controllers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import group44.annotations.Editable;
import group44.models.PropertyInfo;
import group44.models.PropertyInfo.TypeInfo;

public class PropertyController implements IPropertyController {

    private Object activeObject;

    @Override
    public void setActiveObject(Object object) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException();
        }
        this.activeObject = object;
    }

    @Override
    public PropertyInfo[] getProperties() {
        ArrayList<PropertyInfo> infos = new ArrayList<PropertyInfo>();
        try {
            System.out.println(this.activeObject.getClass().getName());
            getPropertyInfos(infos, this.activeObject.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\n" + e.getMessage() + "\n");
        }

        infos.trimToSize();
        return infos.toArray(new PropertyInfo[infos.size()]); // ;
    }

    @Override
    public void setPropertyValue(PropertyInfo info)
            throws NoSuchFieldException, SecurityException {
        String fieldName = convertToField(info.getKey());

        @SuppressWarnings("rawtypes")
        Class cls = this.activeObject.getClass();
        Field field = getDeclaredFieldInHierarchy(fieldName, cls);
        field.setAccessible(true);
        try {
            switch (info.getTypeInfo()) {
            case Int:
                field.setInt(this.activeObject, (Integer) info.getValue());
                break;
            case Boolean:
                field.setBoolean(this.activeObject, (Boolean) info.getValue());
                break;
            // TODO: Resolve values from String

            default:
                field.set(this.activeObject, info.getValue());
                break;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    private static Field getDeclaredFieldInHierarchy(String name, Class cls)
            throws NoSuchFieldException, SecurityException {
        Field field = null;
        try {
            field = cls.getDeclaredField(name);
        } catch (Exception e) {
            // e.printStackTrace();
        }

        if (field != null) {
            return field;
        } else if (cls.getSuperclass() != null
                && !cls.getSuperclass().getName().equals("java.lang.Object")) {
            return getDeclaredFieldInHierarchy(name, cls.getSuperclass());
        } else {
            throw new NoSuchFieldException(name);
        }
    }

    @SuppressWarnings("rawtypes")
    private void getPropertyInfos(ArrayList<PropertyInfo> infos, Class cls)
            throws Exception {

        for (Field item : cls.getDeclaredFields()) {
            if (item.isAnnotationPresent(Editable.class)) {
                item.setAccessible(true);
                PropertyInfo.TypeInfo typeInfo = null;

                switch (item.getType().getSimpleName()) {
                case "int":
                    typeInfo = TypeInfo.Int;
                    break;
                case "String":
                    typeInfo = TypeInfo.String;
                    break;
                case "MovableObject":
                    typeInfo = TypeInfo.MovableObject;
                    break;
                case "CollectableItem":
                    typeInfo = TypeInfo.CollectableItem;
                    break;
                case "boolean":
                    typeInfo = TypeInfo.Boolean;
                    break;
                case "Teleporter":
                    typeInfo = TypeInfo.Teleporter;
                    break;
                case "KeyType":
                    typeInfo = TypeInfo.KeyType;
                    break;
                default:
                    String sName = item.getType().getSimpleName();
                    System.err.println(sName);
                    throw new Exception(item.getType().getSimpleName());
                }

                Object value = item.get(this.activeObject);
                infos.add(new PropertyInfo(convertName(item.getName()), value,
                        typeInfo));
            }
        }

        if (cls.getSuperclass() != null
                && !cls.getSuperclass().getName().equals("java.lang.Object")) {
            getPropertyInfos(infos, cls.getSuperclass());
        }
    }

    private String convertName(String name) {
        StringBuilder builder = new StringBuilder();

        Pattern pattern = Pattern.compile("^([a-z]+)|([A-Z][a-z]*)");
        Matcher matcher = pattern.matcher(name);

        while (matcher.find()) {
            builder.append(matcher.group());
            builder.append(' ');
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        Character firstChar = Character.toUpperCase(builder.charAt(0));
        builder.replace(0, 1, firstChar.toString());
        return builder.toString();
    }

    private String convertToField(String name) {
        name = name.replace(" ", "");

        StringBuilder builder = new StringBuilder(name);
        Character firstChar = Character.toLowerCase(builder.charAt(0));
        builder.replace(0, 1, firstChar.toString());

        return builder.toString();
    }
}

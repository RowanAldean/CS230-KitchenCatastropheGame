package group44.controllers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import group44.annotations.Editable;
import group44.entities.LevelObject;
import group44.entities.cells.Teleporter;
import group44.entities.collectableItems.CollectableItem;
import group44.entities.collectableItems.Key.KeyType;
import group44.entities.movableObjects.MovableObject;
import group44.models.PropertyInfo;
import group44.models.PropertyInfo.TypeInfo;

/**
 * A controller to change editable properties of {@link LevelObject}.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class PropertyController implements IPropertyController {

    private static final String OBJECT_TYPE_SIMPLE_NAME = "java.lang.Object";
    private static final String INT_TYPE_SIMPLE_NAME = "int";
    private static final String BOOLEAN_TYPE_SIMPLE_NAME = "boolean";
    private static final String STRING_TYPE_SIMPLE_NAME = "String";
    private static final String MOVABLE_OBJECT_TYPE_SIMPLE_NAME = "MovableObject";
    private static final String COLLECTABLE_ITEM_TYPE_SIMPLE_NAME = "CollectableItem";
    private static final String TELEPORTER_TYPE_SIMPLE_NAME = "Teleporter";
    private static final String KEY_TYPE_TYPE_SIMPLE_NAME = "KeyType";

    private static final String REGEX_PATTERN_FIELD_NAME = "^([a-z]+)|([A-Z][a-z]*)";

    private static final String CLASS_CAST_EXCEPTION_MESSAGE = "Inconsistent type for %s!";

    /**
     * Currently edited object.
     */
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
            throws NoSuchFieldException, SecurityException, ClassCastException {
        String fieldName = convertNameToField(info.getKey());

        @SuppressWarnings("rawtypes")
        Class cls = this.activeObject.getClass();
        Field field = this.getDeclaredFieldInHierarchy(fieldName, cls);
        field.setAccessible(true);
        try {
            switch (info.getTypeInfo()) {
                case String:
                    if (info.getValue().getClass() != String.class) {
                        throw new ClassCastException(String.format(CLASS_CAST_EXCEPTION_MESSAGE, info.getKey()));
                    }
                    field.set(this.activeObject, info.getValue());
                    break;
                case Int:
                    if (info.getValue().getClass() != Integer.class) {
                        throw new ClassCastException(String.format(CLASS_CAST_EXCEPTION_MESSAGE, info.getKey()));
                    }
                    field.setInt(this.activeObject, (Integer) info.getValue());
                    break;
                case Boolean:
                    if (info.getValue().getClass() != Boolean.class) {
                        throw new ClassCastException(String.format(CLASS_CAST_EXCEPTION_MESSAGE, info.getKey()));
                    }
                    field.setBoolean(this.activeObject, (Boolean) info.getValue());
                    break;
                case CollectableItem:
                    if (info.getValue().getClass() != CollectableItem.class) {
                        throw new ClassCastException(String.format(CLASS_CAST_EXCEPTION_MESSAGE, info.getKey()));
                    }
                    field.set(this.activeObject, (CollectableItem) info.getValue());
                    break;
                case KeyType:
                    if (info.getValue().getClass() != KeyType.class) {
                        throw new ClassCastException(String.format(CLASS_CAST_EXCEPTION_MESSAGE, info.getKey()));
                    }
                    field.set(this.activeObject, (KeyType) info.getValue());
                    break;
                case MovableObject:
                    if (info.getValue().getClass() != MovableObject.class) {
                        throw new ClassCastException(String.format(CLASS_CAST_EXCEPTION_MESSAGE, info.getKey()));
                    }
                    field.set(this.activeObject, (MovableObject) info.getValue());
                    break;
                case Teleporter:
                    if (info.getValue().getClass() != Teleporter.class) {
                        throw new ClassCastException(String.format(CLASS_CAST_EXCEPTION_MESSAGE, info.getKey()));
                    }
                    field.set(this.activeObject, (Teleporter) info.getValue());
                    break;

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

    /**
     * Returns a {@link Field} in an object or in its super-classes.
     *
     * @param name The name of the field.
     * @param cls  The instance of the object in which to look for the
     *             {@link Field}.
     * @return The field.
     * @throws NoSuchFieldException when the {@link Field} is not found.
     * @throws SecurityException    when the {@link Field} can't be accessed.
     */
    @SuppressWarnings("rawtypes")
    private Field getDeclaredFieldInHierarchy(String name, Class cls)
            throws NoSuchFieldException, SecurityException {
        Field field = null;
        try {
            field = cls.getDeclaredField(name);
        } catch (Exception e) {
            // e.printStackTrace();
        }

        if (field != null) {
            return field;
        } else if (cls.getSuperclass() != null && !cls.getSuperclass().getName()
                .equals(OBJECT_TYPE_SIMPLE_NAME)) {
            return this.getDeclaredFieldInHierarchy(name, cls.getSuperclass());
        } else {
            throw new NoSuchFieldException(name);
        }
    }

    /**
     * Returns information about {@link Editable} fields in the hierarchy.
     *
     * @param infos The {@link ArrayList} with already discovered {@link Field}s.
     * @param cls   The class in which to for the fields.
     * @throws Exception when something blows up.
     */
    @SuppressWarnings("rawtypes")
    private void getPropertyInfos(ArrayList<PropertyInfo> infos, Class cls)
            throws Exception {

        for (Field item : cls.getDeclaredFields()) {
            if (item.isAnnotationPresent(Editable.class)) {
                item.setAccessible(true);
                PropertyInfo.TypeInfo typeInfo = null;

                switch (item.getType().getSimpleName()) {
                    case INT_TYPE_SIMPLE_NAME:
                        typeInfo = TypeInfo.Int;
                        break;
                    case STRING_TYPE_SIMPLE_NAME:
                        typeInfo = TypeInfo.String;
                        break;
                    case MOVABLE_OBJECT_TYPE_SIMPLE_NAME:
                        typeInfo = TypeInfo.MovableObject;
                        break;
                    case COLLECTABLE_ITEM_TYPE_SIMPLE_NAME:
                        typeInfo = TypeInfo.CollectableItem;
                        break;
                    case BOOLEAN_TYPE_SIMPLE_NAME:
                        typeInfo = TypeInfo.Boolean;
                        break;
                    case TELEPORTER_TYPE_SIMPLE_NAME:
                        typeInfo = TypeInfo.Teleporter;
                        break;
                    case KEY_TYPE_TYPE_SIMPLE_NAME:
                        typeInfo = TypeInfo.KeyType;
                        break;
                    default:
                        String sName = item.getType().getSimpleName();
                        System.err.println(sName);
                        throw new Exception(item.getType().getSimpleName());
                }

                Object value = item.get(this.activeObject);
                infos.add(new PropertyInfo(convertfieldToName(item.getName()),
                        value, typeInfo));
            }
        }

        if (cls.getSuperclass() != null && !cls.getSuperclass().getName()
                .equals(OBJECT_TYPE_SIMPLE_NAME)) {
            getPropertyInfos(infos, cls.getSuperclass());
        }
    }

    /**
     * Converts field name to displayable name.
     *
     * @param name the field name.
     * @return the displayable name.
     */
    private String convertfieldToName(String name) {
        StringBuilder builder = new StringBuilder();

        Pattern pattern = Pattern.compile(REGEX_PATTERN_FIELD_NAME);
        Matcher matcher = pattern.matcher(name);

        while (matcher.find()) {
            builder.append(matcher.group());
            builder.append(" ");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        Character firstChar = Character.toUpperCase(builder.charAt(0));
        builder.replace(0, 1, firstChar.toString());
        return builder.toString();
    }

    /**
     * Converts the displayable name back to field name.
     *
     * @param name The displayable name.
     * @return The field name.
     */
    private String convertNameToField(String name) {
        name = name.replace(" ", "");

        StringBuilder builder = new StringBuilder(name);
        Character firstChar = Character.toLowerCase(builder.charAt(0));
        builder.replace(0, 1, firstChar.toString());

        return builder.toString();
    }
}

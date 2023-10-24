package utn.frc.backend.tutor.sac.lib.store;

import utn.frc.backend.tutor.sac.lib.store.annotations.StoreKey;
import utn.frc.backend.tutor.sac.lib.store.annotations.StoreNotBlank;
import utn.frc.backend.tutor.sac.lib.store.annotations.StoreNotNull;
import utn.frc.backend.tutor.sac.lib.store.exceptions.StoreableValidationException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Store <ET extends Updateable<ET>, KT> {

    protected boolean isolated;
    protected boolean incrementable;

    protected Map<KT, ET> data = new HashMap<>();

    public Store() {
        this(true, false);
    }

    public Store(boolean isolated, boolean incrementable) {
        super();
        this.isolated = isolated;
        this.incrementable = incrementable;
    }

    private Field getKeyField(ET element) throws StoreableValidationException {
        Field field = getKeyField(element.getClass());

        if (field == null) {
            field = getKeyField(element.getClass().getSuperclass());
        }

        if (field == null) {
            throw new StoreableValidationException(null, "Identificador no definido");
        }

        return field;
    }

    private Field getKeyField(Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotation(StoreKey.class) != null) {
                field.setAccessible(true);
                return field;
            }
        }

        return null;
    }

    private KT getKey(ET element) throws StoreableValidationException {
        try {
            return (KT) getKeyField(element).get(element);
        } catch (IllegalAccessException e) {
            throw new StoreableValidationException(getKeyField(element).getName(), e.getMessage());
        }
    }

    private void setKey(ET element, KT key) throws StoreableValidationException {
        try {
            getKeyField(element).set(element, key);
        } catch (IllegalAccessException e) {
            throw new StoreableValidationException(getKeyField(element).getName(), e.getMessage());
        }
    }

    protected abstract KT nextKey();

    public boolean exists(KT key) {
        return data.containsKey(key);
    }

    public boolean allExist(Set<KT> keys) {
        return keys.stream().allMatch(this::exists);
    }

    public ET find(KT key) {
        ET element = data.get(key);

        return (isolated && element != null)
                ? element.clone()
                : element;
    }

    public Set<ET> findAll() {
        return isolated
                ? data.values().stream().map(element -> element.clone()).collect(Collectors.toSet())
                : data.values().stream().collect(Collectors.toSet());
    }

    protected void validate(ET element) throws StoreableValidationException {
        validate(element, element.getClass().getSuperclass());
        validate(element, element.getClass());
    }

    private void validate(ET element, Class clazz) throws StoreableValidationException {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            StoreNotNull notNull = field.getAnnotation(StoreNotNull.class);
            if (notNull != null) {
                try {
                    Object value = field.get(element);
                    if (Objects.isNull(value)) {
                        throw new StoreableValidationException(field.getName(), notNull.msg());
                    }
                } catch (IllegalAccessException e) {
                    throw new StoreableValidationException(field.getName(), e.getMessage());
                }
            }

            StoreNotBlank notBlank = field.getAnnotation(StoreNotBlank.class);
            if (notBlank != null) {
                try {
                    String s = (String) field.get(element);
                    if (Objects.isNull(s) || s.isBlank()) {
                        throw new StoreableValidationException(field.getName(), notBlank.msg());
                    }
                } catch (IllegalAccessException e) {
                    throw new StoreableValidationException(field.getName(), e.getMessage());
                }
            }
        }
    }

    public ET save(ET element) throws StoreableValidationException {
        validate(element);

        KT key = getKey(element);

        if (data.containsKey(key)) {
            data.get(key).update(element);
        } else {
            if (incrementable) {
                key = nextKey();
            }
            if (key == null) {
                throw new StoreableValidationException(getKeyField(element).getName(), "Identificador nulo o incorrecto");
            }
            setKey(element, key);
            data.put(key, element.clone());
        }

        return (isolated)
                ? element
                : data.get(key);
    }

    public ET remove(KT key) {
        return data.remove(key);
    }

    public abstract void clear();

}

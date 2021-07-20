package com.ruslanshakirov.crm.util;

import com.ruslanshakirov.crm.entity.AbstractBaseEntity;
import com.ruslanshakirov.crm.entity.Persistable;
import com.ruslanshakirov.crm.exception.MyBadRequestException;
import com.ruslanshakirov.crm.exception.MyNotFoundException;

import java.util.Optional;

import static com.ruslanshakirov.crm.exception.ExceptionMessages.NOT_FOUND_ID;

public class ValidationUtil {

    public static <T extends AbstractBaseEntity> T checkNotFound(Optional<T> entity, String name, Long id) {
        return entity.orElseThrow(() ->
                new MyNotFoundException(String.format(NOT_FOUND_ID, name, id)));
    }

    public static void checkIdsMatch(Persistable<Long> entity, Long id) {
        if (entity.getId() == null) {
            entity.setId(id);
        }
        if (!id.equals(entity.getId())) {
            throw new MyBadRequestException(String.format("Id не соответствуют %d != %d", entity.getId(), id));
        }
    }

}

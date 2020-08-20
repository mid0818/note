package com.note.util;

import java.util.UUID;

public class UUid {

    public static String createId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}

package com.example.transaction_outbox_pattern.model.enums;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum OrderCommand {
    CREATE, VALIDATE, UNKNOWN;

    public static OrderCommand fromString(String command){
        try{
            return valueOf(command.toUpperCase());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}

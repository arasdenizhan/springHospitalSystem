package com.denzhn.repo.commons.exceptions;

public class WebClientBuildingException extends SystemException {
    public WebClientBuildingException(Throwable cause) {
        super("WebClient Building failed!", cause);
    }
}

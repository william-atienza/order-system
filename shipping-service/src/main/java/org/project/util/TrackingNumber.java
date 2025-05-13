package org.project.util;

import java.time.LocalDate;

public enum TrackingNumber {
    INSTANCE;
    public String generate(String orderId, String originatingCountryCode){
        //International Standard:  ISO 15459-1
        //but will create our own format since different shipping services has their own
        return LocalDate.now().getYear() + "-" + orderId + "-" + originatingCountryCode;
    }
}
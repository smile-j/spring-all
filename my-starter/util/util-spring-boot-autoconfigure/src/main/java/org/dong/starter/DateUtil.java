package org.dong.starter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;

public class DateUtil {

    @Autowired
    private UtilProperties utilProperties;

    public String getLocalTime(){
        int zone =0;
        if(utilProperties.getLatitude()!=null){
            zone = (int)Math.rint(utilProperties.getLatitude()* DateTimeConstants.HOURS_PER_DAY)/360;
        }
        DateTimeZone dz = DateTimeZone.forOffsetHours(zone);
        return new DateTime(dz).toString(utilProperties.getPattern());
    }

}

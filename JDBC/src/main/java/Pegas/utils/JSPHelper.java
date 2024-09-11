package Pegas.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JSPHelper {
    public String getPath(String jsp){
        return String.format("/%s.jsp",jsp);
    }
}

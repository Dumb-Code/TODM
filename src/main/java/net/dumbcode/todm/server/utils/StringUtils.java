package net.dumbcode.todm.server.utils;

import lombok.experimental.UtilityClass;

import java.util.Locale;

@UtilityClass
public class StringUtils
{
    public static String formatString(String string)
    {
        return string.toLowerCase(Locale.ENGLISH).replaceAll(" ", "_").replaceAll("'", "");
    }
}

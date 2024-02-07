package io.github.mathieusoysal.logement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AddressUtils {
    private static final Pattern PATTERN_REGEX_CITY = Pattern.compile("[^ \\d]+[^\\d]*[\\d \\.]*$", Pattern.MULTILINE);
    private static final Pattern PATTER_REGEX_ZIP_CODE = Pattern.compile(
            "( \\d{2} )?[\\d]+(?=[a-zA-Z]*[^\\d]*[\\d\\. ]*$)",
            Pattern.MULTILINE);
    private static final Pattern PATTERN_REGEX_STREET = Pattern.compile(
            "^.*[^ \\-](?= +[a-zA-Z]*[- ]*\\d{2} ?\\d{3,} +[^\\d]*[\\d\\. ]*$)",
            Pattern.MULTILINE);

    private AddressUtils() {
    }

    static String getCityFromString(String address) {
        Matcher matcher = PATTERN_REGEX_CITY.matcher(address);
        if (matcher.find())
            return matcher.group(0);
        return "";
    }

    static String getStreetFromString(String address) {
        Matcher matcher = PATTERN_REGEX_STREET.matcher(address);
        if (matcher.find())
            return matcher.group(0);
        return "";
    }

    static String getZipCodeFromString(String address) {
        Matcher matcher = PATTER_REGEX_ZIP_CODE.matcher(address);
        if (matcher.find())
            return matcher.group(0).replaceAll(" ", "");
        return "";
    }

}

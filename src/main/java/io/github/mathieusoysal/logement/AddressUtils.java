package io.github.mathieusoysal.logement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AddressUtils {
    private static final Pattern PATTERN_REGEX_CITY = Pattern.compile("([^ \\d]+[^\\d]*$)", Pattern.MULTILINE);
    private static final Pattern PATTER_REGEX_ZIP_CODE = Pattern.compile("\\d*(?=[a-zA-Z]*[^\\d]*$)",
            Pattern.MULTILINE);
    private static final Pattern PATTERN_REGEX_STREET = Pattern.compile(".*[^ ](?= +[a-zA-Z]*[\\d]+ +[^\\d]*$)",
            Pattern.MULTILINE);

    private AddressUtils() {
    }

    public static String getCityFromString(String address) {
        Matcher matcher = PATTERN_REGEX_CITY.matcher(address);
        if (matcher.find())
            return matcher.group(0);
        return "";
    }

    public static String getStreetFromString(String address) {
        Matcher matcher = PATTERN_REGEX_STREET.matcher(address);
        if (matcher.find())
            return matcher.group(0);
        return "";
    }

    public static String getZipCodeFromString(String address) {
        return null;
    }

}

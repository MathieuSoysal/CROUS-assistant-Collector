package io.github.mathieusoysal.logement;

import java.util.regex.Pattern;

class AddressUtils {
    private static final Pattern PATTERN_REGEX_CITY = Pattern.compile("(\\d =?)[^\\d]*$", Pattern.MULTILINE);
    private static final Pattern PATTER_REGEX_ZIP_CODE = Pattern.compile("\\d*(?=[a-zA-Z]*[^\\d]*$)",
            Pattern.MULTILINE);
    private static final Pattern PATTERN_REGEX_STREET = Pattern.compile(".*[^ ](?= +[a-zA-Z]*[\\d]+ +[^\\d]*$)",
            Pattern.MULTILINE);

    private AddressUtils() {
    }

    public static String getCityFromString(String address) {
        return PATTERN_REGEX_CITY.matcher(address).group(1);
    }

    public static String getZipCodeFromString(String address) {
        return PATTER_REGEX_ZIP_CODE.matcher(address).group(1);
    }

    public static String getStreetFromString(String address) {
        return PATTERN_REGEX_STREET.matcher(address).group(1);
    }

}

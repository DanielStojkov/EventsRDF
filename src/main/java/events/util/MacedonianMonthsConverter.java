package events.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for converting Macedonian months to English.
 */
public final class MacedonianMonthsConverter {
    private static final Map<String, String> translations = new HashMap<>();

    static {
        translations.put("јануари", "January");
        translations.put("февруари", "February");
        translations.put("март", "March");
        translations.put("април", "April");
        translations.put("мај", "May");
        translations.put("јуни", "June");
        translations.put("јули", "July");
        translations.put("август", "August");
        translations.put("септември", "September");
        translations.put("октомври", "October");
        translations.put("ноември", "November");
        translations.put("декември", "December");
    }

    public static String getEnglishNameForMonth(String macedonianNameForMonth) {
        return translations.get(macedonianNameForMonth.toLowerCase());
    }
}

package sem.gu.enums;

/**
 *  Class providing common enum operations statically
 *
 *  copied from a contribution on stackoverflow by PNS
 *  https://stackoverflow.com/questions/28332924/case-insensitive-matching-of-a-string-to-a-java-enum
 */
public class EnumOperations {

    public static <T extends Enum<?>> T searchEnum(Class<T> enumeration,
                                                   String search) {
        for (T each : enumeration.getEnumConstants()) {
            if (each.name().compareToIgnoreCase(search) == 0) {
                return each;
            }
        }
        return null;
    }
}

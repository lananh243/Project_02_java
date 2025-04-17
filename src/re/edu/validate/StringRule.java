package re.edu.validate;

public class StringRule {
        private final int maxLength;

    public StringRule(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isValidString(String value) {
            if (value == null) {
                return false;
            }
            return value.length() <= this.maxLength;
        }
}

package storm2013.smartdashboard;

import edu.wpi.first.smartdashboard.types.NamedDataType;

public class DoubleDoubleType extends NamedDataType {

    public static final String LABEL = "DoubleDoubleType";

    private DoubleDoubleType() {
        super(LABEL);
    }

    public static NamedDataType get() {
        if (NamedDataType.get(LABEL) != null) {
            return NamedDataType.get(LABEL);
        } else {
            return new DoubleDoubleType();
        }
    }
}
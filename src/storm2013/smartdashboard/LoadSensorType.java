package storm2013.smartdashboard;

import edu.wpi.first.smartdashboard.types.NamedDataType;

public class LoadSensorType extends NamedDataType {

    public static final String LABEL = "Load Sensor";

    private LoadSensorType() {
        super(LABEL);
    }

    public static NamedDataType get() {
        if (NamedDataType.get(LABEL) != null) {
            return NamedDataType.get(LABEL);
        } else {
            return new LoadSensorType();
        }
    }
}
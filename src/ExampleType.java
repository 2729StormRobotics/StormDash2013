import edu.wpi.first.smartdashboard.types.NamedDataType;

public class ExampleType extends NamedDataType {

    //the 'LABEL' property names the type so widgets can use it
    public static final String LABEL = "ExampleType";

    //just put the rest of this in, you need it

    private ExampleType() {
        super(LABEL);
    }

    public static NamedDataType get() {
        if (NamedDataType.get(LABEL) != null) {
            return NamedDataType.get(LABEL);
        } else {
            return new ExampleType();
        }
    }
}
package sciapi.api.unit;

public class MeasurementException extends RuntimeException {
    public MeasurementException(Measurement m, Measurement n, String proc) {
    	super(m.getMeasurementName() + " is not same with " + n.getMeasurementName()
    			+ ", so can't run this process: " + proc + ".");
    }
}

package Team3_BW.energy_services.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }
}
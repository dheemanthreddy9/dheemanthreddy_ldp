package interfaces;

/**
 * Interface Segregation Principle (ISP):
 * Only electric vehicles implement this interface.
 * Electric cars aren't forced to implement dummy refuel() methods.
 */
public interface Rechargeable {
    void charge(double kwh);
}

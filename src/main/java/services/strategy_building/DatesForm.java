package services.strategy_building;

/**
 * A form is the ordered collection of rules submitted to a director to create a DateStrategy.
 * An implementation should be a data collection structure whose order determines the directions of the director.
 */
public interface DatesForm extends Iterable<Rule> {

    void addRule(Rule rule);

}

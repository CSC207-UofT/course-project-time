package services.strategybuilding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Form that iterates over all added rules in FIFO order but sandwiched in a Union strategy
 */
public class UnionForm implements DatesForm {

    private final List<Rule> rules = new LinkedList<>();

    @Override
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public Iterator<Rule> iterator() {
        List<Rule> sandwichedRules = new ArrayList<>();
        sandwichedRules.add(new Rules.StartUnionRule());
        sandwichedRules.addAll(rules);
        sandwichedRules.add(new Rules.FinishRule());
        return sandwichedRules.listIterator();
    }
}

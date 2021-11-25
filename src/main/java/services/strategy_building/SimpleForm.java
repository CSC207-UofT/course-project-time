package services.strategy_building;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleForm implements DatesForm {

    private List<Rule> rules = new LinkedList<>();

    @Override
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public Iterator<Rule> iterator() {
        return rules.listIterator();
    }
}

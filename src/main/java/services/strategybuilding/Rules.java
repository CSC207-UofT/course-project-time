package services.strategybuilding;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Utility class containing Rule classes which manipulate a StrategyBuilder
 */
public class Rules {

    public static class FinishRule implements Rule {

        @Override
        public void execute(StrategyBuilder sb) {
            sb.finishCurrentStrategy();
        }
    }

    public static class StartSingleDateRule implements Rule {

        private final LocalDateTime dateTime;

        public StartSingleDateRule(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        @Override
        public void execute(StrategyBuilder sb) {
            sb.startSingleDateStrategy(dateTime);
        }
    }

    public static class StartWeeklyRule implements Rule {

        private final DayOfWeek day;
        private final LocalTime timeOfDay;

        public StartWeeklyRule(DayOfWeek dow, LocalTime timeOfDay) {
            this.day = dow;
            this.timeOfDay = timeOfDay;
        }

        @Override
        public void execute(StrategyBuilder sb) {
            sb.startWeeklyStrategy(day, timeOfDay);
        }
    }

    public static class StartUnionRule implements Rule {

        @Override
        public void execute(StrategyBuilder sb) {
            sb.startUnionStrategy();
        }
    }

    public static class OneSidedRestrictionRule implements Rule {

        public static final boolean IS_START = true;

        private final LocalDateTime bound;
        private final boolean startRestriction;

        public OneSidedRestrictionRule(LocalDateTime bound, boolean startRestriction) {
            this.bound = bound;
            this.startRestriction = startRestriction;
        }

        @Override
        public void execute(StrategyBuilder sb) {
            if (startRestriction)
                sb.addStartingRestriction(bound);
            else
                sb.addEndingRestriction(bound);
        }
    }

    public static class BoundedRestrictionRule implements Rule {
        private final LocalDateTime startFrom;
        private final LocalDateTime endAt;

        public BoundedRestrictionRule(LocalDateTime startFrom, LocalDateTime endAt) {
            this.startFrom = startFrom;
            this.endAt = endAt;
        }

        @Override
        public void execute(StrategyBuilder sb) {
            sb.addRangeRestriction(startFrom, endAt);
        }
    }
}

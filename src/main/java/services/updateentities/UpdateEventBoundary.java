package services.updateentities;

import services.strategybuilding.DatesForm;

import java.time.Duration;

public interface UpdateEventBoundary {
    void updateName(long id, String newName);

    void updateDateStrategy(long id, DatesForm form);

    void updateDuration(long id, Duration duration);

    void addTag(long id, String tag);

    void removeTag(long id, String tag);

    void markEventAsCompleted(long id);
}
package services.updateentities;

import services.strategybuilding.DatesForm;

public interface UpdateEventBoundary {
    void updateName(long id, String newName);

    void updateDateStrategy(long id, DatesForm form);

    void addTag(long id, String tag);

    void removeTag(long id, String tag);

    void markEventAsCompleted(long id);
}
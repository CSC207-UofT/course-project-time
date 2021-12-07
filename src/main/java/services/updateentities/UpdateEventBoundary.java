package services.updateentities;

import java.time.LocalTime;

public interface UpdateEventBoundary {
    void updateName(long id, String newName);

    void updateStartTime(long id, LocalTime newStartTime);

    void updateEndTime(long id, LocalTime newEndTime);

    void addTag(long id, String tag);

    void removeTag(long id, String tag);

    void markEventAsCompleted(long id);
}
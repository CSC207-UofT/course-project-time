package tests;

import main.java.console_app.event_adapters.CalendarEventData;
import main.java.console_app.task_to_event_adapters.EventFromTaskId;
import main.java.data_gateway.CalendarManager;
import main.java.data_gateway.EventReader;
import main.java.data_gateway.TaskReader;
import main.java.data_gateway.TodoListManager;
import main.java.services.Snowflake;
import main.java.services.event_creation.CalendarEventModel;
import main.java.services.event_from_task_creation.EventFromTaskCreator;
import main.java.services.event_from_task_creation.EventFromTaskModel;
import main.java.services.task_creation.TodoListTaskCreationModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EventFromTaskCreatorTest {
    MockCalendarManager calendarManager;
    MockTodoListManager todoListManager;
    EventFromTaskCreator eventFromTaskCreator;
    EventFromTaskModel eventFromTaskModel;
    Snowflake snowflake;

    @BeforeEach
    void setUp() {
        eventFromTaskModel = new EventFromTaskId(123, LocalDateTime.of(2021, 11, 15, 12, 00));
        eventFromTaskCreator = new EventFromTaskCreator(todoListManager, calendarManager);
        snowflake = new Snowflake(1, 2, 3);
        calendarManager = new MockCalendarManager(snowflake);
        todoListManager = new MockTodoListManager();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createEventFromTask() {
        boolean expected = eventFromTaskCreator.createEventFromTask(eventFromTaskModel);
        assertTrue(expected);
    }



    private class MockCalendarManager implements CalendarManager {
        private final Snowflake snowflake;
        private final ArrayList<CalendarEventData> eventList;

        public MockCalendarManager(Snowflake snowflake) {
            this.snowflake = snowflake;
            this.eventList = new ArrayList<>();
        }

        @Override
        public boolean addEvent(CalendarEventModel eventData) {
            return eventList.add((CalendarEventData) eventData);
        }

        public ArrayList<CalendarEventData> getEventList() {
            return eventList;
        }

        @Override
        public List<EventReader> getAllEvents() {
            return null;
        }

        @Override
        public void loadEvents(String filePath) throws IOException {

        }

        @Override
        public void saveEvents(String savePath) throws IOException {

        }
    }

    private class MockTodoListManager implements TodoListManager{

        @Override
        public int addTask(TodoListTaskCreationModel taskData) {
            return 0;
        }

        @Override
        public int createTodoList() {
            return 0;
        }

        @Override
        public TaskReader getTask(long todoListId, long taskId) {
            return null;
        }

        @Override
        public Map<Long, List<TaskReader>> getAllTasks() {
            return null;
        }

        @Override
        public void loadTodo(String filepath) throws IOException {

        }

        @Override
        public void saveTodo(String filepath) throws IOException {

        }
    }
}
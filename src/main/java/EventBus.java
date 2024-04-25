import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus{
    private Map<EventType, List<IObserver>> subscribers = new HashMap<>();
    private static EventBus instance;

    // Singleton Pattern ensures that only one instance of the event bus exists
    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    // Method to subscribe an observer
    public void attach(IObserver observer, EventType eventType) {
        if (subscribers.containsKey(eventType)) {
            subscribers.get(eventType).add(observer);
        } else {
            List<IObserver> newEventObservers = new ArrayList<>();
            newEventObservers.add(observer);
            subscribers.put(eventType, newEventObservers);
        }
    }

    // Method to alert relevant observers
    public void postMessage(EventType eventType, String eventDescription) {
        if (subscribers.containsKey(eventType)) {
            for (IObserver observer : subscribers.get(eventType)) {
                observer.update(eventType, eventDescription);
            }
        }
    }
}

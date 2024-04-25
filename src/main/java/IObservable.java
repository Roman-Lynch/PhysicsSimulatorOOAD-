import java.util.List;

public interface IObservable {
    void subscribe(IObserver observer, List<EventType> interestedEvents);
}


import java.io.IOException;
import java.util.List;

public class AudibleObserver implements IObserver {
    private IObservable GUI;
    private List<EventType> interestingEvents;
    private int delayInSeconds;

    // Constructor without the arcaneGame parameter
    public AudibleObserver(IObservable arcaneGame, List<EventType> interestingEvents, Integer delayInSeconds) {
        this.interestingEvents = interestingEvents;
        this.delayInSeconds = delayInSeconds;
        this.GUI = arcaneGame;
    }

    @Override
    public void update(EventType eventType, String eventDescription) {
        if (interestingEvents.contains(eventType)) {
            try {
                sayMessage(eventDescription);
                Thread.sleep(delayInSeconds * 1000); // Adding a delay
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sayMessage(String message) throws IOException {
        String[] cmd = {"say", message}; // For MacOS
        Runtime.getRuntime().exec(cmd);
    }
}
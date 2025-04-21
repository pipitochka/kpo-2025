package zoo.domains.events;

public interface EventHandler<T extends DomainEvent> {
    void handle(T event);
}
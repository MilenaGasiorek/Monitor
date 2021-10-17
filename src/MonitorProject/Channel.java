package MonitorProject;


public interface Channel {

    void attach(User user);

    void detach(User user);

    void notifyUsers();

    void setText(String text);

    String getText();

    String getName();
}

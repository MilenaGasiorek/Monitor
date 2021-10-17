package MonitorProject;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MainChannel implements Channel {

    private Set<User> users = new HashSet<>();
    private final String name = "main";
    private String text;


    @Override
    public void attach(User user) {
        users.add(user);
    }

    @Override
    public void detach(User user) {
        users.remove(user);
    }

    @Override
    public void notifyUsers() {
        for (User u : users) {
            u.update("main");
        }
    }


    public void setText(String text) {
        this.text = text;
        notifyUsers();

    }

    public String getText() {
        return this.text;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainChannel that = (MainChannel) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

package MonitorProject;

import java.util.*;

public class User {
    private Set<Channel> channels = new HashSet<>();
    private String username;

    User(String username) {
        this.username = username;
    }

    public void update(String ch) {
        // akcja powiadomienia uzytkownika
        Channel channel = null;
        for (Channel c : channels) {
            if (c.getName().equals(ch)) {
                channel = c;
            }
        }
        if (channel != null) {
            System.out.println(channel.getName() + ": " + username + " " + channel.getText());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addChannel(Channel channel) {
        channels.add(channel);
    }

    public void removeChannel(Channel channel) {
        channels.remove(channel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}

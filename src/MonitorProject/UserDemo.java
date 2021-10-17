package MonitorProject;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UserDemo {

    private static Scanner scanner = new Scanner(System.in);
    private static Channel mainChannel = new MainChannel();
    private static Channel channel;
    private static Channel extraChannel = new ExtraChannel();
    private static Set<User> users = new HashSet<>();


    public static void registration() {
        System.out.println("Road Map" + "\n" +
                "Please register yourself and select the channel or channels with the use of below template:" + "\n" +
                "add userName main/extra" + "\n" +
                "If you want to finish registration process type /end/" + "\n" +
                "If you want to remove the user who already has been registered, use below template: " + "\n" +
                "remove userName main/extra" + "\n" +
                "to submit please use ENTER"
        );

        String message = scanner.nextLine();

        while (!message.equals("end")) {
            // tworzenie uzytkownikow i rejestracja kanalow
            // add username channel [main/extra]
            // remove username channel

            String[] data = message.split(" ");
            String command = data[0];
            String username = data[1];
            String channel = data[2];


            User user = new User(username);
            for (User u : users) {
                if (u.equals(user)) {
                    user = u;
                    break;
                }
            }

            users.add(user);

            switch (channel) {
                case "main":
                    if (command.equals("add")) {
                        user.addChannel(mainChannel);
                        mainChannel.attach(user);
                    } else if (command.equals("remove")) {
                        user.removeChannel(mainChannel);
                        mainChannel.detach(user);
                    }
                    break;
                case "extra":
                    if (command.equals("add")) {
                        user.addChannel(extraChannel);
                        extraChannel.attach(user);
                    } else if (command.equals("remove")) {
                        user.removeChannel(extraChannel);
                        extraChannel.detach(user);
                    }
                    break;
            }


            message = scanner.nextLine();
        }
    }

    public static void main(String[] args) throws Exception {
        registration();

        System.out.println("You've been registered!");

        File extraFile = new File("./src/MonitorProject/res/extra.txt");
        File mainFile = new File("./src/MonitorProject/res/main.txt");
        Scanner extraScanner = new Scanner(extraFile);
        Scanner mainScanner = new Scanner(mainFile);

        while (extraScanner.hasNextLine() || mainScanner.hasNextLine()) {
            if (extraScanner.hasNextLine()) {
                String line = extraScanner.nextLine();
                extraChannel.setText(line);
            }
            if (mainScanner.hasNextLine()) {
                String line = mainScanner.nextLine();
                mainChannel.setText(line);
            }
            Thread.sleep(1000);
        }

        System.out.println("That's all for today...");
        System.out.println("Type your own message, use below template:" +  "\n" +
                "channel (main/extra) yourMessage, and then submit with ENTER");

        String message = scanner.nextLine();
        // wrzucenie wiadomosci do kanalow
        while (!message.equals("exit")) {
            // channel message

            String[] data = message.split(" ");
            String channelChoice = data[0];
            String text = data[1];


            if (channelChoice.equals("main")) {
                mainChannel.setText(text);
            } else {
                extraChannel.setText(text);
            }

            message = scanner.nextLine();

        }

        channel.getText();
    }
}

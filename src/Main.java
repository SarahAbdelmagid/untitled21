import java.io.*;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;


public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Member> eliteMembers = new ArrayList<>();
    static ArrayList<Member> generalMembers = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Welcome to the Membership Manager!");
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> addEliteMember();
                case 2 -> addGeneralMember();
                case 3 -> sendMailMenu();
                case 4 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void printMainMenu() {
        System.out.println("\n1- Add elite member");
        System.out.println("2- Add general member");
        System.out.println("3- Send mail");
        System.out.println("4- Exit");
        System.out.print("Enter your choice: ");
    }

    public static void addEliteMember() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter surname: ");
        String surname = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        EliteMember member = new EliteMember(name, surname, email);
        eliteMembers.add(member);
        System.out.println("Elite member added successfully.");
        saveMembersToFile();
    }

    public static void addGeneralMember() {
        System.out.println("Enter name:");
        String name = sc.nextLine();
        System.out.println("Enter surname:");
        String surname = sc.nextLine();
        System.out.println("Enter email:");
        String email = sc.nextLine();
        GeneralMember member = new GeneralMember(name, surname, email);
        generalMembers.add(member);
        System.out.println("General member added successfully.");
        saveMembersToFile();
    }

    public static void sendMailMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n1- Email to elite members");
            System.out.println("2- Email to general members");
            System.out.println("3- Email to all members");
            System.out.println("4- Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> sendMailTo(eliteMembers);
                case 2 -> sendMailTo(generalMembers);
                case 3 -> {
                    ArrayList<Member> allMembers = new ArrayList<>();
                    allMembers.addAll(eliteMembers);
                    allMembers.addAll(generalMembers);
                    sendMailTo(allMembers);
                }
                case 4 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static <MimeMessage> void sendMailTo(ArrayList<Member> members) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                PasswordAuthentication auth = new PasswordAuthentication("sarahabdelmagid120@gmail.com", "sarahabdelmagid@1209".toCharArray());
                System.out.println("Email authentication successful");
                return auth;
            }
        });


        try {
            MimeMessage message;
            message = (MimeMessage) new equals(session);
            message.equals(new InternetAddress("sarahabdelmagid120@gmail.com")); // replace with your email address
            for (Member ignored : members) {
                message.wait();
            }
            message.equals("Membership Information");
            message.wait();
            Transport.send(message);
            System.out.println("Mail sent successfully.");
        } catch (InterruptedException e) {
            System.out.println("Error sending mail: " + e.getMessage());
        }
    }

    public static void saveMembersToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("members.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(eliteMembers);
            oos.writeObject(generalMembers);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error saving members to file: " + e.getMessage());
        }
    }
}

class Member implements Serializable {
    public Member(String ignoredName, String ignoredSurname, String ignoredEmail) {
    }

}

class EliteMember extends Member {
    public EliteMember(String name, String surname, String email) {
        super(name, surname, email);
    }
}

class GeneralMember extends Member {
    public GeneralMember(String name, String surname, String email) {
        super(name, surname, email);
    }
}

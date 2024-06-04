import java.util.*;

public class Main {
    private static void showCommands() {
        System.out.println("help         - Afiseaza aceasta lista de comenzi");
        System.out.println("add          - Adauga o noua persoana (inscriere)");
        System.out.println("check        - Verifica daca o persoana este inscrisa la eveniment");
        System.out.println("remove       - Sterge o persoana existenta din lista");
        System.out.println("update       - Actualizeaza detaliile unei persoane");
        System.out.println("guests       - Lista de persoane care participa la eveniment");
        System.out.println("waitlist     - Persoanele din lista de asteptare");
        System.out.println("available    - Numarul de locuri libere");
        System.out.println("guests_no    - Numarul de persoane care participa la eveniment");
        System.out.println("waitlist_no  - Numarul de persoane din lista de asteptare");
        System.out.println("subscribe_no - Numarul total de persoane inscrise");
        System.out.println("search       - Cauta toti invitatii conform sirului de caractere introdus");
        System.out.println("save         - Salveaza lista cu invitati");
        System.out.println("restore      - Completeaza lista cu informatii salvate anterior");
        System.out.println("reset        - Sterge informatiile salvate despre invitati");
        System.out.println("quit         - Inchide aplicatia");
    }

    private static void addNewGuest(Scanner sc, GuestsList list) {
        // TO DO:
        String lastName = sc.nextLine();
        String firstName = sc.nextLine();
        String email = sc.nextLine();
        String phoneNumber = sc.nextLine();

        Guest newGuest = new Guest(lastName, firstName, email, phoneNumber);
        list.addGuest(newGuest);
    }
    private static Guest getGuestByCriteria(Scanner sc, GuestsList list){
        int option = Integer.parseInt(sc.nextLine());
        Guest guest = null;
        switch (option){
            case 1:
                String lastName = sc.nextLine();
                String firstName = sc.nextLine();
                guest = list.getGuestByName(lastName,firstName);
                break;
            case 2:
                String email = sc.nextLine();
                guest = list.getGuestByEmail(email);
                break;
            case 3:
                String phoneNumber = sc.nextLine();
                guest = list.getGuestByPhoneNumber(phoneNumber);
                break;
        }
        return guest;
    }
    private static void checkGuest(Scanner sc, GuestsList list) {

        Guest guest = getGuestByCriteria(sc, list);
        if (guest!=null)
            System.out.println(guest);
    }

    private static void removeGuest(Scanner sc, GuestsList list) {

        Guest guest = getGuestByCriteria(sc, list);
        if(guest!=null)
            list.removeGuest(guest);
    }
    private static void updateGuest(Scanner sc, GuestsList list) {
        Guest guest = getGuestByCriteria(sc, list);
        if(guest != null) {
            int option = Integer.parseInt(sc.nextLine());
            String newValue = sc.nextLine();
            list.updateGuest(guest, option, newValue);
        }
    }

    private static void search(Scanner sc, GuestsList list) {
        String search = sc.nextLine();
        List<Guest> guestList = list.partialSearch(search);
        if(guestList.isEmpty())
            System.out.println("Nothing found");
        else
            for(Guest g : guestList)
                System.out.println(g.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());

        GuestsList list = new GuestsList(size);

        boolean running = true;
        while (running) {
            String command = scanner.nextLine();
            switch (command) {
                case "help":
                    showCommands();
                    break;
                case "add":
                    addNewGuest(scanner, list);
                    break;
                case "check":
                    checkGuest(scanner, list);
                    break;
                case "remove":
                    removeGuest(scanner, list);
                    break;
                case "update":
                    updateGuest(scanner, list);
                    break;
                case "guests":
                    List<Guest> guestList = list.getGuests();
                    for(int i=0; i<guestList.size();i++)
                        System.out.println(i+1 + ". " + guestList.get(i).toString());
                    break;
                case "waitlist":
                    List<Guest> waitingGuestList = list.getWaitingList();
                    if(waitingGuestList.isEmpty())
                        System.out.println("Lista de asteptare este goala...");
                    else
                        for(int i=0; i<waitingGuestList.size();i++)
                            System.out.println(i+1 + ". " + waitingGuestList.get(i).toString());
                    break;
                case "available":
                    System.out.println("Numarul de locuri ramase: " + list.getNumberOfSeatsAvailable());
                    break;
                case "guests_no":
                    System.out.println("Numarul de participanti: " + list.getNumberOfGuests());
                    break;
                case "waitlist_no":
                    System.out.println("Dimensiunea listei de asteptare: " + list.getNumberOfGuestsWaiting());
                    break;
                case "subscribe_no":
                    System.out.println("Numarul total de persoane: " + list.getTotalNumberOfGuests());
                    break;
                case "search":
                    search(scanner, list);
                    break;
                case "quit":
                    System.out.println("Aplicatia se inchide...");
                    scanner.close();
                    running = false;
                    break;
                default:
                    System.out.println("Comanda introdusa nu este valida." + command);
                    System.out.println("Incercati inca o data.");

            }
        }
    }
}
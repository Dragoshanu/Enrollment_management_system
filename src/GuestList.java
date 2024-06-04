import java.util.*;

class GuestsList {

    private final Queue<Guest> guests;
    private final Queue<Guest> waitingList;
    private final int capacity;

    public GuestsList(int guestsCapacity) {
        this.guests = new LinkedList<>();
        this.waitingList = new LinkedList<>();
        this.capacity = guestsCapacity;
    }

    public int addGuest(Guest guest) {
        if(isAlreadyRegistered(guest)) {
            System.out.println("Persoana este deja inscrisa la eveniment.");
            return -1;
        }
        if(guests.size() < capacity) {
            guests.add(guest);
            System.out.println("[" + guest.fullName() + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
            return 0;
        }
        waitingList.add(guest);
        System.out.println("[" + guest.fullName() + "] Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " + waitingList.size() + ". Te vom notifica daca un loc devine disponibil.");
        return waitingList.size();
    }

    public boolean isAlreadyRegistered(Guest guest) {
        for(Guest g : guests)
            if(guest.equals(g))
                return true;
        for(Guest g : waitingList)
            if(guest.equals(g))
                return true;
        return false;
    }

    public boolean removeGuest(Guest guest){
        Iterator<Guest> iterator = guests.iterator();
        while(iterator.hasNext()){
            Guest g = iterator.next();
            if(guest.equals(g)){
                iterator.remove();
                Guest newGuest = waitingList.poll();
                if (newGuest != null){
                    guests.add(newGuest);
                    System.out.println("[" + newGuest.fullName() + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
                }
                return true;
            }
        }

        iterator = waitingList.iterator();
        while(iterator.hasNext()){
            Guest g = iterator.next();
            if(guest.equals(g)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Guest> partialSearch(String search){
        search = search.toLowerCase();
        List<Guest> result = new ArrayList<>();
        for(Guest g : guests)
            if (g.fullName().toLowerCase().contains(search) || g.getEmail().toLowerCase().contains(search) || g.getPhoneNumber().toLowerCase().contains(search))
                result.add(g);
        for(Guest g : waitingList)
            if (g.fullName().toLowerCase().contains(search) || g.getEmail().toLowerCase().contains(search) || g.getPhoneNumber().toLowerCase().contains(search))
                result.add(g);
        return result;
    }

    public Guest getGuestByName(String lastName, String firstName) {
        for(Guest g : guests){
            if(g.getLastName().equals(lastName) && g.getFirstName().equals(firstName))
                return g;
        }
        for(Guest g : waitingList){
            if(g.getLastName().equals(lastName) && g.getFirstName().equals(firstName))
                return g;
        }
        return null;
    }

    public Guest getGuestByEmail(String email) {
        for(Guest g : guests){
            if(g.getEmail().equals(email))
                return g;
        }
        for(Guest g : waitingList){
            if(g.getEmail().equals(email))
                return g;
        }
        return null;
    }

    public Guest getGuestByPhoneNumber(String phoneNumber) {
        for(Guest g : guests){
            if(g.getPhoneNumber().equals(phoneNumber))
                return g;
        }for(Guest g : waitingList){
            if(g.getPhoneNumber().equals(phoneNumber))
                return g;
        }
        return null;
    }
    public void updateGuest(Guest g, int option, String newValue) {
        switch (option){
            case 1:
                g.setLastName(newValue);
                break;
            case 2:
                g.setFirstName(newValue);
                break;
            case 3:
                g.setEmail(newValue);
                break;
            case 4:
                g.setPhoneNumber(newValue);
                break;
            default: break;
        }
    }
    public List<Guest> getGuests() {
        return new ArrayList<>(guests);
    }

    public List<Guest> getWaitingList() {
        return new ArrayList<>(waitingList);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumberOfSeatsAvailable(){
        return capacity - guests.size();
    }

    public int getNumberOfGuests(){
        return guests.size();
    }

    public int getNumberOfGuestsWaiting(){
        return waitingList.size();
    }

    public int getTotalNumberOfGuests(){
        return getNumberOfGuests() + getNumberOfGuestsWaiting();
    }
}
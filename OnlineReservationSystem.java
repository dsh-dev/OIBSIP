import java.util.*;
class Passenger{
    String name;
    int age;
    String gender;
    Passenger(String name,int age,String gender){
        this.name=name;
        this.age=age;
        this.gender=gender;
    }
}
class Ticket{
    String pnr;   //passenger name record
    Passenger passenger;
    int trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String fromPlace;
    String destination;
    String seatNumber;
    Ticket(String pnr,Passenger passenger,int trainNumber,String trainName,String classType,String dateOfJourney,String fromPlace,String destination,String seatNumber){
        this.pnr=pnr;
        this.passenger=passenger;
        this.trainNumber=trainNumber;
        this.trainName=trainName;
        this.classType=classType;
        this.dateOfJourney=dateOfJourney;
        this.fromPlace=fromPlace;
        this.destination=destination;
        this.seatNumber=seatNumber;
    }
    void displayTicket(){
        System.out.println("\n=================================");
        System.out.println("      ONLINE RESERVATION SYSTEM");
        System.out.println("=================================");
        System.out.println("PNR             :"+pnr);
        System.out.println("Name            :"+passenger.name);
        System.out.println("Age             :"+passenger.age);
        System.out.println("Gender          :"+passenger.gender);
        System.out.println("Train Number    :"+trainNumber);
        System.out.println("Train Name      :"+trainName);
        System.out.println("Class Type      :"+classType);
        System.out.println("Date Of Journey :"+dateOfJourney);
        System.out.println("From Place      :"+fromPlace);
        System.out.println("Destination     :"+destination);
        System.out.println("Seat Number     :"+seatNumber);
    }
}
public class OnlineReservationSystem{
    static Scanner sc=new Scanner(System.in);
    static HashMap<String,Ticket> database=new HashMap<>();
    static String loginId="admin";
    static String password="1234";
    public static void main(String args[])
{
    System.out.println("\n=================================");
    System.out.println("      ONLINE RESERVATION SYSTEM");
    System.out.println("=================================");

    if(login())
    {
        int choice;
        do{
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1.Reservation System");
            System.out.println("2.Cancellation Form");
            System.out.println("3.View Reservation");
            System.out.println("4.Exit");
            System.out.print("Enter your choice:");
            choice=sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                    reservationSystem();
                    break;
                case 2:
                    cancellationForm();
                    break;
                case 3:
                    viewReservation();
                    break;
                case 4:
                    System.out.println("Thank You");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while(choice!=4);
    }  
}
    public static boolean login()
    {
        System.out.print("Enter loginId:");
        String loginId=sc.nextLine();
        System.out.print("Enter password:");
        String password=sc.nextLine();
        return loginId.equals(OnlineReservationSystem.loginId)&&password.equals(OnlineReservationSystem.password);
    }
    public static void reservationSystem()
    {
        System.out.println("\n========== RESERVATION FORM ==========");
        System.out.print("Enter Name:");
        String name=sc.nextLine();
        System.out.print("Enter Age:");
        int age=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Gender:");
        String gender=sc.nextLine();
        Passenger passenger = new Passenger(name, age, gender);
        System.out.print("Enter Train Number:");
        int trainNumber=sc.nextInt();
        sc.nextLine();
        String trainName=getTrainName(trainNumber);
        System.out.println("Train Name:"+trainName);
        System.out.print("Enter Class Type:");
        String classType=sc.nextLine();
        System.out.print("Enter Date Of Journey:");
        String dateOfJourney=sc.nextLine();
        System.out.print("From place:");
        String from = sc.nextLine();
        System.out.print("Destination:");
        String destination = sc.nextLine();
        String pnr=generatePNR();
        String seatNumber=generateSeatNumber(trainNumber);
        Ticket ticket=new Ticket(pnr,passenger,trainNumber,trainName,classType,dateOfJourney,from,destination,seatNumber);
        database.put(pnr,ticket);
        System.out.println("\nReservation Successful!");
        System.out.println("Your PNR Number is:"+pnr);
        ticket.displayTicket();
    }
    static void cancellationForm()
    {
        System.out.println("\n========== CANCELLATION FORM ==========");
        System.out.print("Enter PNR Number:");
        String pnr=sc.nextLine();
        if(database.containsKey(pnr))
        {
            Ticket ticket=database.get(pnr);
            ticket.displayTicket();
            System.out.print("\nPress OK to Confirm Cancellation (yes/no):");
            String choice=sc.nextLine();
            if(choice.equalsIgnoreCase("yes"))
            {
                database.remove(pnr);
                System.out.println("Cancellation Successful!");
            }
            else
            {
                System.out.println("Cancellation Unsuccessful!");
            }
        }
        else
        {
            System.out.println("Invalid PNR Number");
        }
    }
    static void viewReservation()
    {
        System.out.println("\n========== VIEW RESERVATION ==========");
        System.out.print("Enter PNR Number:");
        String pnr=sc.nextLine();
        if(database.containsKey(pnr))
        {
            Ticket ticket=database.get(pnr);
            ticket.displayTicket();
        }
        else
        {
            System.out.println("Invalid PNR Number");
        }
    }
    static String getTrainName(int trainNumber)
    {
        if(trainNumber==123)
        {
            return "Chennai Express";
        }
        else if(trainNumber==456)
        {
            return "Mumbai Express";
        }
        else
        {
            return "Kolkata Express";
        }
    }
    static String generatePNR()
    {
        Random random=new Random();
        return "PNR"+(100000+random.nextInt(900000));
    }
    static String generateSeatNumber(int trainNumber)
    {
        Random random=new Random();
        int coach=random.nextInt(10)+1;
        int seat=random.nextInt(72)+1;
        return "S"+coach+"-"+seat;
    }
}
import Enums.Plans;
import Interfaces.ICustomer;

import java.util.ArrayList;

public class Customer implements ICustomer {

    private int ID;
    private String Name;
    private String CardNumber;
    private String Birthday;
    private Plans SubPlan;
    private int points;
    private boolean freeRentAllowed;
    private ArrayList<CustomerTitle> TitlesRented;

    public Customer (int ID, String Name, String CardNumber, String Birthday, Plans SubscriptionPlan, int Points){
        this.ID = ID;
        this.Name = Name;
        this.CardNumber = CardNumber;
        this.Birthday = Birthday;
        this.SubPlan = SubscriptionPlan;
        this.TitlesRented = new ArrayList<CustomerTitle>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public Plans getSubPlan() {
        return SubPlan;
    }

    public void setSubPlan(Plans subPlan) {
        SubPlan = subPlan;
    }

    public ArrayList<CustomerTitle> getArrayTitlesRented(){
        return TitlesRented;
    }

    public void addArrayTitlesRented(CustomerTitle title){
        TitlesRented.add(title);
    }

    public void addPoints(int points){
        this.points += points;
        setRentAllowed();
    }

    public boolean availFreeRent(){
        if(this.isFreeRentAllowed()){
            this.points -= 100;
            setRentAllowed();
            return true;
        }else {
            return false;
        }
    }

    private void setRentAllowed(){
        if (this.points >= 100){
            this.freeRentAllowed = true;
        }else {
            this.freeRentAllowed = false;
        }
    }

    public int getPoints(){
        return points;
    }

    public boolean isFreeRentAllowed(){
        return freeRentAllowed;
    }

    public void showCustomersDetails(){
        System.out.println("ID: " + this.ID + " | Name: " + this.Name + " | Date of Birthday: " + this.Birthday + "| Subs Plan: " + this.SubPlan + " Points: " + this.getPoints());

    }

}

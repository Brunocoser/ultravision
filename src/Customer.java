import Enums.Plans;
import Interfaces.ICustomer;

import java.util.ArrayList;

public class Customer implements ICustomer {

    private int intId;
    private String name;
    private String cardNumber;
    private String birthday;
    private Plans subPlan;
    private int points;
    private boolean freeRentAllowed;
    private final ArrayList<CustomerTitle> titlesRented;

    public Customer (int intId, String name, String cardNumber, String birthday, Plans SubscriptionPlan, int points){
        this.intId = intId;
        this.name = name;
        this.cardNumber = cardNumber;
        this.birthday = birthday;
        this.subPlan = SubscriptionPlan;
        this.titlesRented = new ArrayList<>();
    }

    public int getIntId() {
        return intId;
    }

    public void setIntId(int iD) {
        intId = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Plans getSubPlan() {
        return subPlan;
    }

    public void setSubPlan(Plans subPlan) {
        this.subPlan = subPlan;
    }

    public ArrayList<CustomerTitle> getArrayTitlesRented(){
        return titlesRented;
    }

    public void addArrayTitlesRented(CustomerTitle title){
        titlesRented.add(title);
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
        System.out.println("ID: " + this.intId + " | Name: " + this.name + " | Date of Birthday: " + this.birthday + "| Subs Plan: " + this.subPlan + " Points: " + this.getPoints());

    }

}

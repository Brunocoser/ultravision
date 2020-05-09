package Interfaces;

import Enums.Plans;

public interface ICustomer {
    int getIntId();
    void setIntId(int iD);
    String getName();
    void setName(String name);
    String getCardNumber();
    void setCardNumber(String cardNumber);
    String getBirthday();
    void setBirthday(String birthday);
    Plans getSubPlan();
    void setSubPlan(Plans subPlan);
    void showCustomersDetails();



}

import Enums.MediaFormats;
import Enums.Plans;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class KioskManager {
    public void searchCustomers(ArrayList<Customer> listCustomers) {

        boolean returnMenu = false;
        Scanner scan = new Scanner(System.in);

        do {
            boolean found = false;

            System.out.println("---------- Search for Customers  ----------\n* To return to the menu, type 'exit' *\n");
            System.out.println("Please, enter a Customer Name (or type 'all' to show all): ");

            String customerName = scan.nextLine();

            if(customerName.isEmpty()){
                System.out.println("Please enter a valid name:");
            }
            else if (customerName.toLowerCase().contentEquals("exit")) {
                returnMenu = true;
            } else if (customerName.toLowerCase().contentEquals("all")){
                System.out.println();
                for (Customer customer : listCustomers) {
                    customer.showCustomersDetails();
                    found = true;
                }
                System.out.println();
            } else{
                System.out.println();
                for (Customer customer : listCustomers) {
                    if (customer.getName().toLowerCase().contains(customerName.toLowerCase())) {
                        customer.showCustomersDetails();
                        found = true;
                    }
                }
                System.out.println();
            }
            if (!found && !returnMenu && !customerName.isEmpty()) {
                System.out.println(customerName + " not found.");
            }
        } while (!returnMenu);

    }

    public void addCustomers(ArrayList<Customer> listCustomers) {

        boolean valid;
        String strCustomer;
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\\n");

        System.out.println("---------- New Customers ----------\n* To return to the menu, type 'exit' *\n");
        System.out.println("Enter a Customer name:");
        String name = scan.nextLine();

        if (checkForExit(name)) {
            return;
        } else {
            do {
                valid = false;

                if (checkForExit(name)) {
                    return;
                }

                if (name.isEmpty()) {
                    System.out.println("Please enter a valid name:");
                    name = scan.nextLine();
                } else {
                    valid = true;
                }
            } while (!valid);
        }

        String cardNumber = null;
        do {
            valid = false;
            System.out.println("Please enter the card number: ");
            cardNumber = scan.nextLine();

            if (!cardNumber.matches("(\\d{4}[-. ]?){4}|\\d{4}[-. ]?\\d{6}[-. ]?\\d{5}")) {
                System.out.println("Please enter a valid number");
            } else {
                valid = true;
            }
        } while (!valid);

        System.out.println("Please enter the date of Birthday(DD/MM/YYYY): ");

        String date;
        do {
            date = scan.next();
            if (date.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((18|19|20|21)\\d\\d)")) {
                valid = true;
            } else {
                System.out.println("Please enter a valid date");
                valid = false;
                System.out.println("Please enter a valid date of Birthday (DD/MM/YYYY)");
            }

        } while (!valid);

        Customer newCustomer = new Customer(listCustomers.size() + 1, name, cardNumber, date, Plans.NONE, 0);
        listCustomers.add(newCustomer);

        strCustomer = newCustomer.getIntId() + "|" + newCustomer.getName() + "|" + newCustomer.getCardNumber() + "|" + date + "|NONE|0|";

        try {
            FileWriter fw = new FileWriter("Customers.txt", true);
            fw.write(strCustomer + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(newCustomer.getName() + " was added to the list.");
    }

    public boolean checkForExit(String value){
        return value.toLowerCase().contentEquals("exit");
    }

    public void updateCustomers(ArrayList<Customer> listCustomers) {

        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\\n");
        boolean valid = false;

        do {
            String iDNumber;
            System.out.println();
            System.out.println("----------  Updating Customers and Subscription Plan ----------\n* To return to the menu, type 'exit' *\n");
            System.out.println("'all' FOR SHOW ALL THE CUSTOMERS");
            System.out.println("Please, enter a ID Customer:");

            try {
                iDNumber = scan.next();
                if (iDNumber.isEmpty()){
                    return;
                }
                if (iDNumber.toLowerCase().contentEquals("exit")) {
                    return;

                } else if (iDNumber.toLowerCase().contentEquals("all")) {
                    System.out.println();
                    for (Customer customerUpdated : listCustomers) {
                        customerUpdated.showCustomersDetails();
                    }
                } else {
                    for (Customer customerUpdated : listCustomers) {
                        if (customerUpdated.getIntId() == Integer.parseInt(iDNumber)) {
                            System.out.println("The customer was found.");
                            customerUpdated.showCustomersDetails();

                            System.out.println("Please enter a valid name");
                            String name ;

                            do {
                                valid = false;
                                name = scan.next();
                                if (name.isEmpty()) {
                                    System.out.println("The name must have only letters");
                                } else {
                                    valid = true;
                                }
                            } while (!valid);

                            String cardNumber;

                            do {
                                valid = false;

                                System.out.println("Please enter the card number:");
                                cardNumber = scan.next();

                                if (!cardNumber.matches("(\\d{4}[-. ]?){4}|\\d{4}[-. ]?\\d{6}[-. ]?\\d{5}")) {
                                    System.out.println("Please enter a valid Card Number:");
                                } else {
                                    valid = true;
                                }
                            } while (!valid);

                            System.out.println("Enter a new date of birthday (dd/mm/yyyy)");
                            String date = scan.next();

                            do {
                                valid = false;
                                if (date.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((18|19|20|21)\\d\\d)")) {
                                    valid = true;
                                } else {
                                    System.out.println("Invalid date");
                                    System.out.println("Please enter a valid date of birthday (dd/mm/yyyy)");
                                    date = scan.next();
                                }

                            } while (!valid);

                            customerUpdated.setName(name);
                            customerUpdated.setCardNumber(cardNumber);
                            customerUpdated.setBirthday(date);

                            do {
                                valid = false;

                                System.out.println("Please choose one of the access levels listed below");
                                System.out.println("- (ML) Music Lovers: Can only rent Music CDs and Live Concert Videos\r\n"
                                        + "- (VL) Video Lovers: Can only rent Movies (excluding Live Concert Videos)\r\n"
                                        + "- (TV) TV Lover: Can only rent Box Sets.\r\n"
                                        + "- (PR) Premium: Can rent any title\n"
                                        + "- (NONE) No Plan");
                                String subPlan = scan.next();

                                switch (subPlan.toUpperCase()) {
                                    case "ML":
                                        customerUpdated.setSubPlan(Plans.ML);
                                        valid = true;
                                        break;
                                    case "VL":
                                        customerUpdated.setSubPlan(Plans.VL);
                                        valid = true;
                                        break;
                                    case "TV":
                                        customerUpdated.setSubPlan(Plans.TV);
                                        valid = true;
                                        break;
                                    case "PR":
                                        customerUpdated.setSubPlan(Plans.PR);
                                        valid = true;
                                        break;
                                    case "NONE":
                                        customerUpdated.setSubPlan(Plans.NONE);
                                        valid = true;
                                        break;
                                    default:
                                        System.out.println("Invalid option. Please try it again");
                                        valid = false;
                                        break;
                                }
                            } while (!valid);
                            valid = true;
                            System.out.println("Details updated");

                            break;

                        }
                    }
                    updateCustomersList(listCustomers);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("It wasn't possible to update the customer details.");
            }
        }while (!valid);
    }

    public void updateCustomersList(ArrayList<Customer> listCustomers) {

        boolean renew = false;

        for (int i = 0; i < listCustomers.size(); i++) {
            String strCustomer = listCustomers.get(i).getIntId() + "|" + listCustomers.get(i).getName() + "|"
                    + listCustomers.get(i).getCardNumber() + "|" + listCustomers.get(i).getBirthday() + "|"
                    + listCustomers.get(i).getSubPlan() + "|" + listCustomers.get(i).getPoints() + "|";
            try {
                if (i > 0) {
                    renew = true;
                }
                FileWriter fw = new FileWriter("Customers.txt", renew);
                fw.write(strCustomer + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void searchTitle(ArrayList<Title> listTitles) {
        Scanner scan = new Scanner(System.in);
        boolean returnMenu = false;
        do {
            boolean found = false;

            showDetails(null, listTitles, "");
            System.out.println("");
            System.out.println("---------- Search for Titles ----------\n* To return to the menu, type 'exit' *\n");
            System.out.println("Please, enter a Title Name (or type 'all' to show all titles)");

            String titleName = scan.nextLine();

            if (titleName.toLowerCase().contentEquals("exit")) {
                returnMenu = true;
            }
            else if(titleName.toLowerCase().contentEquals("all")){
                System.out.println();
                for (Title title : listTitles){
                    title.showTitleDetails();
                    found = true;
                }

            }else{
                found = showDetails(null, listTitles, titleName.toLowerCase());
            }

            if (!found && !returnMenu) {
                System.out.println(titleName + " was not found. Please try it again.");
            }
        } while (!returnMenu);
    }

    public void addTitle(ArrayList<Title> listTitles, Scanner scan) {
        boolean returnMenu = false;
        String strTitle;

        do {
            boolean validFormat;

            System.out.println("---------- Add new Titles ----------\n"
                    + "------------------------------------\n* To return to the menu, type 'exit' *\n");
            String title;

            System.out.println("Enter a name for the new title: ");

            title = scan.nextLine();

            do {
                validFormat = false;

                if (title.toLowerCase().contentEquals("exit")) {
                    returnMenu = true;
                    break;
                } else {
                    if (title.isEmpty()){
                        System.out.println("The name must only contains letters and numbers.");
                        System.out.println("Please enter a valid name:");
                        title = scan.nextLine();
                    } else {
                        if (showDetails(null, listTitles, title)){
                            System.out.println("This title is already on the system");
                            System.out.println("Please enter a valid name:");
                            title = scan.nextLine();
                        } else {
                            validFormat = true;
                        }
                    }
                }
            } while (!validFormat);

            if (returnMenu) {
                break;
            }

            String sYear;
            int year = 0;
            System.out.println("Please enter the year of release of this title");

            do {
                validFormat = false;

                sYear = scan.nextLine();

                if (sYear.length() != 4 || !sYear.matches("^[0-9]+")) {
                    System.out.println("Please enter a valid number");
                    System.out.println("Please enter the year of release of this title");
                } else {
                    if (Integer.parseInt(sYear) > 0) {
                        year = Integer.parseInt(sYear);
                        validFormat = true;
                    } else {
                        System.out.println("The year must be greater than zero");
                    }
                }
            } while (!validFormat);

            String genre;

            do {
                validFormat = false;

                System.out.println("Enter a genre for this new title");
                genre = scan.nextLine();

                if (genre.isEmpty() || !genre.matches("[a-zA-Z]+")) {
                    System.out.println("The genre must have only letters");
                } else {
                    validFormat = true;
                }
            } while (!validFormat);

            String directorOrBand;
            do {
                validFormat = false;
                System.out.println("Please enter director or band for this title: ");
                directorOrBand = scan.nextLine();

                if (directorOrBand.isEmpty()) {
                    System.out.println("Please enter a valid name");
                } else {
                    validFormat = true;
                }
            } while (!validFormat);

            Title newTitle = new Title(listTitles.size() + 1, title, year, genre, directorOrBand, null, null, false);

            do {
                validFormat = false;
                System.out.println("Please enter a format for the new title (CD, DVD or BluRay)");
                String format = scan.nextLine();

                    switch (format.toLowerCase()) {
                        case "cd":
                            newTitle.setFormatValue(MediaFormats.CD);
                            validFormat = true;
                            break;
                        case "dvd":
                            newTitle.setFormatValue(MediaFormats.DVD);
                            validFormat = true;
                            break;
                        case "bluray":
                            newTitle.setFormatValue(MediaFormats.BluRay);
                            validFormat = true;
                            break;
                        default:
                            System.out.println("Please enter a valid format");
                            break;
                }
            } while (!validFormat);

            do {
                System.out.println("Please enter a type for the title");
                System.out.println("(ML) Music or Live Concert Videos");
                System.out.println("(VL) Movie");
                System.out.println("(TV) Box Set");

                String type = scan.nextLine();

                switch (type.toUpperCase()) {
                    case "ML":
                        newTitle.setType(Plans.ML);
                        validFormat = true;
                        break;
                    case "VL":
                        newTitle.setType(Plans.VL);
                        validFormat = true;
                        break;
                    case "TV":
                        newTitle.setType(Plans.TV);
                        validFormat = true;
                        break;
                    default:
                        System.out.println("Please enter a valid format");
                        validFormat = false;
                        break;
                }
            } while (!validFormat);

            listTitles.add(newTitle);

            strTitle = newTitle.getCode() + "|" + newTitle.getTitle() + "|"
                    + newTitle.getYearRelease() + "|" + newTitle.getGenre() + "|"
                    + newTitle.getDirectorOrBand() + "|" + newTitle.getFormatValue() + "|" + newTitle.getType() + "|false|";

            try {
                FileWriter fw = new FileWriter("Titles.txt", true);
                fw.write(strTitle + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(newTitle.getTitle() + " was added to the inventory");

            do {
                validFormat = false;

                System.out.println("Do you want to add another one? Y/N");

                String res = scan.nextLine();

                if (res.equalsIgnoreCase("Y")) {
                    validFormat = true;
                } else if (res.equalsIgnoreCase("N")) {
                    returnMenu = true;
                    validFormat = true;
                } else {
                    System.out.println("Please enter a valid option");
                }
            } while (!validFormat);
        } while (!returnMenu);
    }

    public void registerRent(ArrayList<Customer> listCustomers, ArrayList<Title> listTitles, Scanner scan) {

        String iDNumber;
        CustomerTitle customerTitle;

        System.out.println("*----------  Register a Rent ----------\\n* To return to the menu, type 'exit' *\\n");
        System.out.println("Please, enter a ID Title:");

        try {
            iDNumber = scan.next();
            boolean foundTitle = false;
            boolean done = false;

            if (iDNumber.toLowerCase().contentEquals("exit")) {
                return;
            }

            for (Title title : listTitles) {

                if (title.getCode() == Integer.parseInt(iDNumber)) {
                    foundTitle = true;
                    System.out.println("The title found");
                    showDetails(null, new ArrayList<>(Arrays.asList(title)), null);

                    if (title.isRented()) {
                        System.out.println("Title is already rented");
                        return;
                    }

                    System.out.println("Would you like to rent this title for a customer? \n If yes, please enter the customer ID, otherwise enter 'no': ");
                    String answer = "";
                    answer = scan.next();

                    if (!answer.equalsIgnoreCase("no")) {

                        try {
                            int iDCustomer = Integer.parseInt(answer);
                            boolean found = false;

                            for (Customer newCustomer : listCustomers) {

                                if (newCustomer.getIntId() == iDCustomer) {
                                    found = true;

                                    if (newCustomer.getArrayTitlesRented().size() < 4) {
                                        if (newCustomer.getSubPlan().equals(Plans.PR) || newCustomer.getSubPlan().equals(title.getType())) {

                                            customerTitle = titleToCustomerTitle(title, iDCustomer);
                                            customerTitle.rentTitle();
                                            title.setRented(true);
                                            renewTitles(listTitles, customerTitle.getCode(), true);

                                            if (newCustomer.isFreeRentAllowed()) {
                                                System.out.println("This customer has a free rent. \n Would you like to use the points?('Y/N'");

                                                String answerPoints = scan.next();

                                                if (answerPoints.toLowerCase().equalsIgnoreCase("y")) {

                                                    newCustomer.availFreeRent();

                                                    System.out.println(newCustomer.getName() + "rented " + title.getTitle()
                                                            + "and it is free of charge. 100 loyalty points deducted.");
                                                    done = true;
                                                }
                                            }
                                            if (!done) {
                                                newCustomer.addPoints(10);

                                                System.out.println(newCustomer.getName() + " rented " + title.getTitle()
                                                        + "and 10 loyalty points has been added to his account");
                                            }
                                            newCustomer.getArrayTitlesRented().add(customerTitle);

                                            String strTitle = customerTitle.getIntIdCustomer() + "|" + customerTitle.getCode() + "|" +
                                                    customerTitle.getStringDateRent() + "|" + customerTitle.getStringDateReturn() + "|";
                                            try {
                                                FileWriter fw = new FileWriter("CustomerTitle.txt", true);
                                                fw.write(strTitle + "\n");
                                                fw.close();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            updateCustomersList(listCustomers);
                                            break;
                                        } else {
                                            System.out.println("Customer plan(" + newCustomer.getSubPlan().toString() + ") can not rent this title(" +
                                                    title.getType().toString() + ")");
                                        }
                                    } else {
                                        System.out.println("The customer has already rented " + newCustomer.getArrayTitlesRented().size() + " titles.");

                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("Customer not found");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please try it again");
                        }
                    }
                }
            }
            if (!foundTitle) {
                System.out.println("The title does not exist");
            }

        } catch (Exception e) {
            System.out.println("Error: It was not possible to register a rent. Please, enter a valid ID and try it again ");
        }
    }

    public void returnTitle(ArrayList<Customer> listCustomers, ArrayList<Title> listTitles, Scanner scan) {

        String iDNumber;
        boolean found = false;

        System.out.println("----------  Register a Return ----------\n* To return to the menu, type 'exit' *\n");
        System.out.println("Please enter the customer ID: ");

        iDNumber = scan.next();

        try {
            if (iDNumber.toLowerCase().contentEquals("exit")) {
                return;
            }

            for (Customer customer : listCustomers) {

                if (customer.getIntId() == Integer.parseInt(iDNumber)) {

                    if (!customer.getArrayTitlesRented().isEmpty()) {
                        customer.showCustomersDetails();
                        found = true;

                        showDetails(customer.getArrayTitlesRented(), null, null);

                        System.out.println("\n Please enter code of the title: ");

                        int code = scan.nextInt();

                        for (CustomerTitle cTitle : customer.getArrayTitlesRented()) {
                            if (cTitle.getCode() == code) {
                                cTitle.show();

                                Date currDate = new Date();
                                long diffDays = 0;

                                try {
                                    long diff = currDate.getTime() - cTitle.getDateReturn().getTime();

                                    diffDays = diff / (24 * 60 * 60 * 1000);

                                    if (currDate.after(cTitle.getDateReturn())) {
                                        System.out.println("This title should be returned " + (diffDays * -1)
                                                + " days ago. There is a penalty" + ((diffDays * -1) + 3));
                                    } else {
                                        System.out.println("This title should be returned on " + cTitle.getStringDateReturn());
                                    }
                                    System.out.println("Would you like to return the title: " + cTitle.getTitle() + "? (Y/N)");
                                    String answer = scan.next();

                                    if (answer.toLowerCase().equalsIgnoreCase("y")) {
                                        if (diffDays <= 0) {
                                            System.out.println("The amount that have to be paid is: " + (diffDays * -1));
                                        } else {
                                            System.out.println("The amount that have to be paid is: " + ((diffDays * -1) + 3));
                                        }

                                        customer.getArrayTitlesRented().remove(cTitle);

                                        renewCustomersTitles(listCustomers);
                                        renewTitles(listTitles, cTitle.getCode(), false);

                                        System.out.println(cTitle.getTitle() + " was returned");
                                        break;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error: it was not possible to return the title. Please try it again");
                                }
                            }
                        }
                    } else {
                        found = true;
                        System.out.println("There are no records");
                    }
                }
            }
            if (!found) {
                System.out.println("It was not possible to return the title. Please, enter a valid ID and try it again");
            }
        } catch (Exception e) {
            System.out.println("Error : it was not possible to return the title. Please, enter a valid ID and try it again");
        }
    }

    public CustomerTitle titleToCustomerTitle(Title title, int idCustomer) {
        CustomerTitle cTitle = new CustomerTitle(title.getCode(), title.getTitle(), title.getYearRelease(),
                title.getGenre(), title.getDirectorOrBand(), title.getFormatValue(), title.getType(), title.isRented());
        cTitle.setIntIdCustomer(idCustomer);
        return cTitle;
    }

    public void renewCustomersTitles(ArrayList<Customer> listCustomers) {
        boolean renew = false;
        int controlRenew = -1;

        for (Customer customer : listCustomers) {
            for (CustomerTitle cTitle : customer.getArrayTitlesRented()) {
                String strTitle = cTitle.getIntIdCustomer() + "|" + cTitle.getCode() + "|" + cTitle.getStringDateRent() + "|"
                        + cTitle.getStringDateReturn() + "|";
                controlRenew++;

                try {
                    if (controlRenew > 0) {
                        renew = true;
                    }
                    FileWriter fw = new FileWriter("CustomerTitle.txt", renew);
                    fw.write(strTitle + "\n");
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (controlRenew == -1) {
            try {
                FileWriter fw = new FileWriter("CustomerTitle.txt", false);
                fw.write("");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void renewTitles(ArrayList<Title> listTitles, int code, boolean rented){
        boolean renew = false;
        int controlRenew = -1;

        for (Title renewTitle : listTitles){
            if (renewTitle.getCode() == code){
                renewTitle.setRented(rented);
            }

            String strTitle = renewTitle.getCode() + "|" + renewTitle.getTitle() + "|" + renewTitle.getYearRelease() + "|" +
                    renewTitle.getGenre() + "|" + renewTitle.getDirectorOrBand() + "|" + renewTitle.getFormatValue() + "|" +
                    renewTitle.getType() + "|" + renewTitle.isRented() + "|";

            controlRenew++;

            try{
                if(controlRenew > 0){
                    renew = true;
                }
                FileWriter fw = new FileWriter("Titles.txt", renew);
                fw.write(strTitle + "\n");
                fw.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public boolean showDetails(ArrayList<CustomerTitle> listCTitles, ArrayList<Title> listTitles, String TitleName){
        boolean found = false;

        if (listCTitles != null){
            for (CustomerTitle cTitle : listCTitles){
                cTitle.show();
            }
        } else {
            if (TitleName == null) {
                for (Title title : listTitles){
                    System.out.println(title.printAll());
                }
            } else {
                for (Title title : listTitles) {
                    if (title.getTitle().toLowerCase().equals(TitleName.toLowerCase())){
                        System.out.println(title.printAll());
                        found = true;
                    }
                }
            }
        }
        return found;
    }
}

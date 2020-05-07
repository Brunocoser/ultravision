import Enums.MediaFormats;
import Enums.Plans;

import javax.imageio.IIOException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Functionality {
    public void SearchCustomers(ArrayList<Customer> listCustomers) {

        boolean returnMenu = false;
        Scanner scan = new Scanner(System.in);

        do {
            boolean found = false;

            System.out.println("---------- Search for Customers  ----------\n* To return to the menu, type 'exit' *\n");
            System.out.println("Please, enter a Customer Name (or type 'all' to show all): ");

            String CustomerName = scan.nextLine();

            if(CustomerName.isEmpty()){
                System.out.println("Please enter a valid name:");
            }
            else if (CustomerName.toLowerCase().contentEquals("exit")) {
                returnMenu = true;
            } else if (CustomerName.toLowerCase().contentEquals("all")){
                System.out.println();
                for (Customer customer : listCustomers) {
                    customer.ShowCustomersDetails();
                    found = true;
                }
                System.out.println();
            } else{
                System.out.println();
                for (Customer customer : listCustomers) {
                    if (customer.getName().toLowerCase().contains(CustomerName.toLowerCase())) {
                        customer.ShowCustomersDetails();
                        found = true;
                    }
                }
                System.out.println();
            }
            if (!found && !returnMenu && !CustomerName.isEmpty()) {
                System.out.println(CustomerName + " not found.");
            }
        } while (!returnMenu);

    }

    public void AddCustomers(ArrayList<Customer> listCustomers) throws IOException {

        boolean valid = false;
        String strCustomer = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("---------- New Customers ----------\n* To return to the menu, type 'exit' *\n");
        System.out.println("Enter a Customer name:");
        String Name = scan.nextLine();

        if (Name.toLowerCase().contentEquals("exit")) {
            return;
        } else {
            do {
                valid = false;

                if (Name.isEmpty() || !Name.matches("[a-zA-Z]+")) {
                    System.out.println("Name must ave only letters");
                    System.out.println("Please enter a valid name:");
                    Name = scan.nextLine();
                } else {
                    valid = true;
                }
            } while (!valid);
        }

        String CardNumber = null;
        do {
            valid = false;
            System.out.println("Please enter the card number: ");
            CardNumber = scan.nextLine();

            if (!CardNumber.matches("(\\d{4}[-. ]?){4}|\\d{4}[-. ]?\\d{6}[-. ]?\\d{5}")) {
                System.out.println("Please enter a valid number");
            } else {
                valid = true;
            }
        } while (!valid);

        System.out.println("Please enter the date of Birthday(DD/MM/YYYY): ");

        String date = scan.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date Birthday = null;

        do {
            try {
                valid = false;
                if (date.matches("(([1-2][0-9])|([1-9])|(3[0-1]))/((1[0-2])|([1-9]))/[0-9]{4}")) {
                    if ((dateFormat.parse(date).getTime() - dateFormat.parse("00/00/0000").getTime()) > 0) {
                        Birthday = dateFormat.parse(date);
                        valid = true;
                    }
                } else {
                    System.out.println("Please enter a valid date");
                    valid = false;
                    System.out.println("Please enter a valid date of Birthday (DD/MM/YYYY");
                    date = scan.next();
                }
            } catch (ParseException e) {
                System.out.println("Invalid date");
                valid = false;
                System.out.println("Please enter a valid date of birthday (DD/MM/YYYY");
                date = scan.next();
            }
        } while (!valid);

        Customer c = new Customer(listCustomers.size() + 1, Name, CardNumber, date, Plans.NONE, 0);
        listCustomers.add(c);

        strCustomer = c.getID() + "|" + c.getName() + "|" + c.getCardNumber() + "|" + date + "|NONE|0|";

        try {
            FileWriter fw = new FileWriter("Customers.txt", true);
            fw.write(strCustomer + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(c.getName() + " was added to the list.");
    }

    public void UpdateCustomers(ArrayList<Customer> listCustomers) {

        Scanner scan = new Scanner(System.in);
        String IdNumber = "-1";
        boolean valid = false;

        System.out.println("----------  Updating Customers and Subscription Plan ----------\n* To return to the menu, type 'exit' *\n");
        System.out.println("Please, enter a ID Customer:");

        try {
            IdNumber = scan.next();
            if (IdNumber.toLowerCase().contentEquals("exit")) {
                return;
            }
            for (Customer cust : listCustomers) {
                if (cust.getID() == Integer.parseInt(IdNumber)) {
                    System.out.println("The customer was found.");
                    cust.ShowCustomersDetails();

                    String name = null;

                    do {
                        valid = false;

                        if (name.isEmpty() || !name.matches("[a-zA-Z]+")) {
                            System.out.println("The name must have only letters");

                            System.out.println("Please enter a valid name");
                            name = scan.nextLine();
                        } else {
                            valid = true;
                        }
                    } while (!valid);

                    String CardNumber = null;

                    do {
                        valid = false;

                        System.out.println("Please enter the card number: ");
                        CardNumber = scan.nextLine();

                        if (!CardNumber.matches("(\\d{4}[-. ]?){4}|\\d{4}[-. ]?\\d{6}[-. ]?\\d{5}")) {
                            System.out.println("Please enter a valid number");
                        } else {
                            valid = true;
                        }
                    } while (!valid);

                    System.out.println("Enter a new date of birthday (dd/mm/yyyy");
                    String date = scan.next();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    Date Birthday = null;

                    valid = false;

                    do {
                        try {
                            valid = false;
                            if (date.matches("(([1-2][0-9])|([1-9])|(3[0-1]))/((1[0-2])|([1-9]))/[0-9]{4}")) {
                                if ((dateFormat.parse(date).getTime() - dateFormat.parse("00/00/0000").getTime()) > 0) {
                                    Birthday = dateFormat.parse(date);
                                    valid = true;
                                }
                            } else {
                                System.out.println("Invalid date");
                                valid = false;
                                System.out.println("Please enter a valid date of birthday (dd/mm/yyyy");
                                date = scan.next();
                            }
                        } catch (ParseException e) {
                            System.out.println("Invalid date.");
                            valid = false;
                            System.out.println("Please enter a valid date of birthday (dd/mm/yyyy");
                            date = scan.next();
                        }
                    } while (!valid);

                    cust.setName(name);
                    cust.setCardNumber(CardNumber);
                    cust.setBirthday(date);

                    do {
                        valid = false;

                        System.out.println("Please choose one of the access levels listed below");
                        System.out.println("- (ML) Music Lovers: Can only rent Music CDs and Live Concert Videos\r\n"
                                + "- (VL) Video Lovers: Can only rent Movies (excluding Live Concert Videos)\r\n"
                                + "- (TV) TV Lover: Can only rent Box Sets.\r\n"
                                + "- (PR) Premium: Can rent any title\n"
                                + "- (NONE) No Plan");
                        scan.nextLine();
                        String SubPlan = scan.nextLine();

                        switch (SubPlan.toUpperCase()) {
                            case "ML":
                                cust.setSubPlan(Plans.ML);
                                valid = true;
                                break;
                            case "VL":
                                cust.setSubPlan(Plans.VL);
                                valid = true;
                                break;
                            case "TV":
                                cust.setSubPlan(Plans.TV);
                                valid = true;
                                break;
                            case "PR":
                                cust.setSubPlan(Plans.PR);
                                valid = true;
                                break;
                            case "NONE":
                                cust.setSubPlan(Plans.NONE);
                                valid = true;
                                break;
                            default:
                                System.out.println("Invalid option. Please try it again");
                                valid = false;
                                break;
                        }
                    } while (!valid);

                    System.out.println("Details updated");

                    break;

                }
            }
            UnloadCustomers(listCustomers);
        } catch (Exception e) {
            System.out.println("It wasn't possible to update the customer details.");
        }
    }

    public void UnloadCustomers(ArrayList<Customer> listCustomers) {

        Boolean renew = false;

        for (int i = 0; i < listCustomers.size(); i++) {
            String strCustomer = listCustomers.get(i).getID() + "|" + listCustomers.get(i).getName() + "|"
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

    public void SearchTitle(ArrayList<Title> listTitles) {
        Scanner scan = new Scanner(System.in);
        boolean returnMenu = false;
        do {
            boolean found = false;

            ShowDetails(null, listTitles, "");
            System.out.println("");
            System.out.println("---------- Search for Titles ----------\n---------------------------------------\n");
            System.out.println("    Please, enter a title name: \n* To return to the menu, type 'exit' *\n");

            String TitleName = scan.nextLine();

            if (TitleName.toLowerCase().contentEquals("exit")) {
                returnMenu = true;
            } else {
                found = ShowDetails(null, listTitles, TitleName.toLowerCase());
            }

            if (!found && !returnMenu) {
                System.out.println(TitleName + " was not found. Please try it again.");
            }
        } while (!returnMenu);
    }

    public void AddTitle(ArrayList<Title> listTitles, Scanner scan) {
        boolean returnMenu = false;
        String strTitle;

        do {
            boolean validFormat = false;

            System.out.println("---------- Add new Titles ----------\n"
                    + "------------------------------------\n* To return to the menu, type 'exit' *\n");
            String title = null;

            System.out.println("Enter a name for the new title: ");

            title = scan.nextLine();

            do {
                validFormat = false;

                if (title.toLowerCase().contentEquals("exit")) {
                    returnMenu = true;
                    break;
                } else {
                    if (title.isEmpty() || !title.matches("[a-zA-Z]+")) {
                        System.out.println("The name must only contains letters");
                        System.out.println("Please enter a valid name");
                        title = scan.nextLine();
                    } else {
                        if (ShowDetails(null, listTitles, title)){
                            System.out.println("This title is already added to the system");

                            System.out.println("Please enter a valid name: ");
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

            String sYear = null;
            int year = 0;
            System.out.println("Please enter the year of release of this title");

            do {
                validFormat = false;

                sYear = scan.nextLine();

                if (sYear.length() != 4 || sYear.matches("[a-zA-Z]+")) {
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

            String genre = null;

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

            String DirectorOrBand = null;
            do {
                validFormat = false;
                System.out.println("Please enter director or band for this title: ");
                DirectorOrBand = scan.nextLine();

                if (DirectorOrBand.isEmpty() || !DirectorOrBand.matches("[a-zA-Z]+")) {
                    System.out.println("The director or band must have only letters");
                } else {
                    validFormat = true;
                }
            } while (!validFormat);

            Title t = new Title(listTitles.size() + 1, title, year, genre, DirectorOrBand, null, null, false);

            do {
                validFormat = false;
                System.out.println("Please enter a format for the new title (CD, DVD or BluRay:");
                String format = scan.nextLine();

                    switch (format.toLowerCase()) {
                        case "cd":
                            t.setFormatValue(MediaFormats.CD);
                            validFormat = true;
                            break;
                        case "dvd":
                            t.setFormatValue(MediaFormats.DVD);
                            validFormat = true;
                            break;
                        case "bluray":
                            t.setFormatValue(MediaFormats.BluRay);
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

                String type = scan.next();

                switch (type.toUpperCase()) {
                    case "ML":
                        t.setType(Plans.ML);
                        validFormat = true;
                        break;
                    case "VL":
                        t.setType(Plans.VL);
                        validFormat = true;
                        break;
                    case "TV":
                        t.setType(Plans.TV);
                        validFormat = true;
                        break;
                    default:
                        System.out.println("Please enter a valid format");
                        validFormat = false;
                        break;
                }
            } while (!validFormat);

            listTitles.add(t);

            strTitle = t.getCode() + "|" + t.getTitle() + "|"
                    + t.getYearRelease() + "|" + t.getGenre() + "|"
                    + t.getDirectorOrBand() + "|" + t.getFormatValue() + "|" + t.getType() + "|false|";

            try {
                FileWriter fw = new FileWriter("Titles.txt", true);
                fw.write(strTitle + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(t.getTitle() + "was added to the inventory");

            do {
                validFormat = false;

                System.out.println("Do you want to add another one? Y/N");

                String res = scan.next();

                if (res.equalsIgnoreCase("Y")) {
                    returnMenu = true;
                    validFormat = true;
                } else if (res.equalsIgnoreCase("N")) {
                    returnMenu = false;
                    validFormat = true;
                } else {
                    System.out.println("Please enter a valid option");
                }
            } while (!validFormat);
        } while (!returnMenu);
    }

    public void RegisterRent(ArrayList<Customer> listCustomers, ArrayList<Title> listTitles, Scanner scan) {

        String IdNumber = "-1";
        CustomerTitle cTitle = null;

        System.out.println("*----------  Register a Rent ----------\\n* To return to the menu, type 'exit' *\\n");
        System.out.println("Please, enter a ID Title:");

        try {
            IdNumber = scan.next();
            boolean foundTitle = false;
            boolean done = false;

            if (IdNumber.toLowerCase().contentEquals("exit")) {
                return;
            }

            for (Title title : listTitles) {

                if (title.getCode() == Integer.parseInt(IdNumber)) {
                    foundTitle = true;
                    System.out.println("The title found");
                    ShowDetails(null, new ArrayList<Title>(Arrays.asList(title)), null);

                    if (title.isRented()) {
                        System.out.println("Title is already rented");
                        return;
                    }

                    System.out.println("Would you like to rent this title for a customer? \n If yes, please enter the customer ID, otherwise enter 'no': ");
                    String answer = "";
                    answer = scan.next();

                    if (!answer.equalsIgnoreCase("no")) {

                        try {
                            int IDCustomer = Integer.parseInt(answer);
                            boolean found = false;

                            for (Customer c : listCustomers) {

                                if (c.getID() == IDCustomer) {
                                    found = true;

                                    if (c.getArrayTitlesRented().size() < 4) {
                                        if (c.getSubPlan().equals(Plans.PR) || c.getSubPlan().equals(title.getType())) {

                                            cTitle = TitleToCustomerTitle(title, IDCustomer);
                                            cTitle.rentTitle();

                                            title.setRented(true);
                                            RenewTitles(listTitles, cTitle.getCode(), true);

                                            if (c.isfreeRentAllowed()) {
                                                System.out.println("This customer has a free rent. \n Would you like to use the points?('Y/N'");

                                                String answerPoints = scan.next();

                                                if (answerPoints.toLowerCase().equalsIgnoreCase("y")) {

                                                    c.availFreeRent();

                                                    System.out.println(c.getName() + "rented " + title.getTitle()
                                                            + "and it is free of charge. 100 loyalty points deducted.");
                                                    done = true;
                                                }
                                            }
                                            if (!done) {
                                                c.addPoints(10);

                                                System.out.println(c.getName() + " rented " + title.getTitle()
                                                        + "and 10 loyalty points has been added to his account");
                                            }
                                            c.getArrayTitlesRented().add(cTitle);

                                            String strTitle = cTitle.getIDCustomer() + "|" + cTitle.getCode() + "|" +
                                                    cTitle.getStringDateRent() + "|" + cTitle.getStringDateReturn() + "|";
                                            try {
                                                FileWriter fw = new FileWriter("CustomerTitle.txt", true);
                                                fw.write(strTitle + "\n");
                                                fw.close();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            UnloadCustomers(listCustomers);
                                            break;
                                        } else {
                                            System.out.println("Customer plan(" + c.getSubPlan().toString() + ") can not rent this title(" +
                                                    title.getType().toString() + ")");
                                        }
                                    } else {
                                        System.out.println("The customer has already rented " + c.getArrayTitlesRented().size() + " titles.");

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

    public void ReturnTitle(ArrayList<Customer> listCustomers, ArrayList<Title> listTitles, Scanner scan) {

        String IdNumber = "-1";
        Boolean found = false;

        System.out.println("----------  Register a Return ----------\n* To return to the menu, type 'exit' *\n");
        System.out.println("Please enter the customer ID: ");

        IdNumber = scan.next();

        try {
            if (IdNumber.toLowerCase().contentEquals("exit")) {
                return;
            }

            for (Customer cust : listCustomers) {

                if (cust.getID() == Integer.parseInt(IdNumber)) {

                    if (!cust.getArrayTitlesRented().isEmpty()) {
                        cust.ShowCustomersDetails();
                        found = true;

                        ShowDetails(cust.getArrayTitlesRented(), null, null);

                        System.out.println("\n Please enter code of the title: ");

                        int code = scan.nextInt();

                        for (CustomerTitle cTitle : cust.getArrayTitlesRented()) {
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

                                        cust.getArrayTitlesRented().remove(cTitle);

                                        RenewCustomerstitles(listCustomers);
                                        RenewTitles(listTitles, cTitle.getCode(), false);

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

    public CustomerTitle TitleToCustomerTitle(Title title, int idCustomer) {
        CustomerTitle cTitle = new CustomerTitle(title.getCode(), title.getTitle(), title.getYearRelease(),
                title.getGenre(), title.getDirectorOrBand(), title.getFormatValue(), title.getType(), title.isRented());
        cTitle.setIDCustomer(idCustomer);
        return cTitle;
    }

    public void RenewCustomerstitles(ArrayList<Customer> listCustomers) {
        Boolean renew = false;
        int controlRenew = -1;

        for (Customer customer : listCustomers) {
            for (CustomerTitle cTitle : customer.getArrayTitlesRented()) {
                String strTitle = cTitle.getIDCustomer() + "|" + cTitle.getCode() + "|" + cTitle.getStringDateRent() + "|"
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

    public void RenewTitles(ArrayList<Title> listTitles, int code, boolean rented){
        boolean renew = false;
        int controlRenew = -1;

        for (Title t : listTitles){
            if (t.getCode() == code){
                t.setRented(rented);
            }

            String strTitle = t.getCode() + "|" + t.getTitle() + "|" + t.getYearRelease() + "|" +
                    t.getGenre() + "|" + t.getDirectorOrBand() + "|" + t.getFormatValue() + "|" +
                    t.getType() + "|" + t.isRented() + "|";

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

    public boolean ShowDetails(ArrayList<CustomerTitle> listCTitles, ArrayList<Title> listTitles, String TitleName){
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

import Enums.MediaFormats;
import Enums.Plans;
import Interfaces.ITitle;

public class title implements ITitle {
    private int code;
    private int yearRelease;
    private String title;
    private String genre;
    private String directorOrBand;
    private MediaFormats formatValue;
    private Plans type;
    private boolean rented;

    public title(int code, String title, int yearRelease, String genre, String directorOrBand, MediaFormats format, Plans type, boolean rented) {
        this.code = code;
        this.title = title;
        this.yearRelease = yearRelease;
        this.genre = genre;
        this.directorOrBand = directorOrBand;
        this.formatValue = format;
        this.type = type;
        this.rented = rented;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) { this.code = code; }

    public int getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(int yearRelease) {
        this.yearRelease = yearRelease;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirectorOrBand() {
        return directorOrBand;
    }

    public void setDirectorOrBand(String director) {
        directorOrBand = director;
    }

    public MediaFormats getFormatValue() {
        return formatValue;
    }

    public void setFormatValue(MediaFormats formatValue) {
        this.formatValue = formatValue;
    }

    public Plans getType() {
        return type;
    }

    public void setType(Plans type) {
        this.type = type;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public String printAll() {
        return "ID: " + getCode() + " Title: " + getTitle()
                + " | Year Release: " + getYearRelease()
                + " | Genre: " + getGenre()
                + " | Director: " + getDirectorOrBand()
                + " | Format: " + getFormatValue()
                + " | Type: " + getType();
    }

    public void showTitleDetails() {
        System.out.println("ID: " + this.code + " | Title: " + this.title + " | Year Release: " + this.yearRelease + " | Genre: " + this.genre +
                "| Director: " + this.directorOrBand + " | Format: " + this.formatValue + " | Type: " + this.type);

    }
}

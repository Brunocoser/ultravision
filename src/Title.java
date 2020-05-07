import Enums.MediaFormats;
import Enums.Plans;
import Interfaces.ITitle;
import com.sun.tools.javah.Gen;

public class Title implements ITitle {

    private int Code;
    private int YearRelease;
    private String Title;
    private String Genre;
    private String DirectorOrBand;
    private MediaFormats FormatValue;
    private Plans Type;
    private boolean Rented;

    public Title(int Code, String Title, int YearRelease, String Genre, String DirectorOrBand, MediaFormats format, Plans type, boolean rented){
        this.Code = Code;
        this.Title = Title;
        this.YearRelease = YearRelease;
        this.Genre = Genre;
        this.DirectorOrBand = DirectorOrBand;
        this.FormatValue = format;
        this.Type = type;
        this.Rented = rented;
    }


    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public int getYearRelease() {
        return YearRelease;
    }

    public void setYearRelease(int yearRelease) {
        YearRelease = yearRelease;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirectorOrBand() {
        return DirectorOrBand;
    }

    public void setDirectorOrBand(String director) {
        DirectorOrBand = director;
    }

    public MediaFormats getFormatValue() {
        return FormatValue;
    }

    public void setFormatValue(MediaFormats formatValue) {
        FormatValue = formatValue;
    }
    public Plans getType(){
        return Type;
    }
    public void setType(Plans type){
        Type = type;
    }
    public boolean isRented(){
        return Rented;
    }
    public void setRented(boolean rented){
        Rented = rented;
    }
}

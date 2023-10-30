import java.util.Date;

public class Souvenir {
    private String name;
    private String manufacturerDetails;
    private Date releaseDate;
    private double price;

    public Souvenir(String name, String manufacturerDetails, Date releaseDate, double price) {
        this.name = name;
        this.manufacturerDetails = manufacturerDetails;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturerDetails() {
        return manufacturerDetails;
    }

    public void setManufacturerDetails(String manufacturerDetails) {
        this.manufacturerDetails = manufacturerDetails;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}


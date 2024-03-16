package stores;
import java.time.LocalDate;

import structures.Collection;

public class Movie {
    private String title;
    private String originalTitle;
    private String overview;
    private String tagline;
    private String status;
    private Genre[] genres;
    private LocalDate release; 
    private long budget;
    private long revenue;
    private String[] languages;
    private String originalLanguage;
    private double runtime;
    private String homepage;
    private boolean adult;
    private boolean video;
    private String poster;
    private double voteAverage;
    private int voteCount;
    private Collection collection;
    private String imdbID;
    private double popularity;

    //The production companies
    private Company[] companies;

    // The inital number of elements in companies
    private int size;

    //The production countries
    private String[] countries;

    // The initial number of elements in countries
    private int num;

    // The initial capacity of the arrays
    private int capacity=10;

    //Checks if a value is set
    private boolean flag = false;

    public Movie( String title, String originalTitle, String overview, String tagline, String status, Genre[] genres, LocalDate release, long budget, long revenue, String[] languages, String originalLanguage, double runtime, String homepage, boolean adult, boolean video, String poster) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.tagline = tagline;
        this.status = status;
        this.genres = genres;
        this.release = release;
        this.budget = budget;
        this.revenue = revenue;
        this.languages = languages;
        this.originalLanguage = originalLanguage;
        this.runtime = runtime;
        this.homepage = homepage;
        this.adult = adult;
        this.video = video;
        this.poster = poster;

        size = 0;
        num = 0;
        // Creates a new 10 element array
        companies = new Company[capacity];
        countries = new String[capacity];
    }

    public String getTitle(){
        return title;
    }

    public String getOriginalTitle(){
        return originalTitle;
    }

    public String getOverview(){
        return overview;
    }
    
    public String getTagline(){
        return tagline;
    }

    public String getStatus(){
        return status;
    }

    public Genre[] getGenres(){
        return genres;
    }

    public LocalDate getRelease(){
        return release;
    }

    public long getBudget(){
        return budget;
    }

    public long getRevenue(){
        return revenue;
    }

    public String[] getLanguages(){
        return languages;
    }

    public String getOriginalLanguage(){
        return originalLanguage;
    }

    public double getRuntime(){
        return runtime;
    }

    public String getHomepage(){
        return homepage;
    }

    public boolean getAdult(){
        return adult;
    }

    public boolean getVideo(){
        return video;
    }

    public String getPoster(){
        return poster;
    }

    public double getVoteAverage(){
        return voteAverage;
    }

    public int getVoteCount(){
        return voteCount;
    }

    public void setVoteAverage(double voteAverage){
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(int voteCount){
        this.voteCount = voteCount;
    }

    public void setCollection(Collection collection){
        this.collection = collection;
    }

    public Collection getCollection(){
        return collection;
    }

    public void setimdbID(String imdbID){
        this.imdbID = imdbID;
    }

    public String getimdbID(){
        return imdbID;
    }

    public void setPopularity(double popularity){
        this.popularity = popularity;
        // Makes the flag true if a popularity has been set
        flag = true;
    }

    public double getPopularity(){
        // If the popularity has been set
        if (flag) {
            // Returns the passed in value
            return popularity;}
        // Otherwise, gives back 0.0
        else {
            return 0.0;
        }
    }
    //==========================================================================================================================
    //To add a company to the array
    public void addProdCompany(Company company){
        int count = 0;
        Company[] newArr;

        // If the array is full, it resizes
        if (size == companies.length){
            resize();
        }
        
        // Adds the company in the next free space
        companies[size] = company;
        size = size + 1;

        // Counts how many items are in the array
        for (int i = 0; i < companies.length; i++){
            if (companies[i] != null){
                count = count + 1;
            }
        }

        // Only keeps companies and removes all empty spaces
        newArr = new Company[count];
        for (int i = 0; i < newArr.length; i++){
            newArr[i] = companies[i];
        }

        // Sets companies to the new array only containing the companies for that movie
        companies = newArr;
    }

    // Resizes the array if it is full
    // Makes it one space bigger and copies over all items 
    public void resize(){
        Company[] newArr = new Company[size+1];

        for (int i=0; i < size; i++){
            newArr[i] = companies[i];
        }
        
        companies = newArr;
    }

    public Company[] getCompanies(){
        return companies;
    }
    //==========================================================================================================================
    //To add a country to the array
    public void addProdCountry(String country){
        int count = 0;
        String[] newArr;

        // If the array is full, it resizes
        if (num == countries.length){
            resizeCountries();
        }
        
        // Adds the country in the next free space
        countries[num] = country;
        num = num + 1;

        // Counts how many items are in the array
        for (int i = 0; i < countries.length; i++){
            if (countries[i] != null){
                count = count + 1;
            }
        }

        // Only keeps countries and removes all empty spaces
        newArr = new String[count];
        for (int i = 0; i < newArr.length; i++){
            newArr[i] = countries[i];
        }

        // Sets countries to the new array only containing the countries for that movie
        countries = newArr;
    }

    // Resizes the array if it is full
    // Makes it one space bigger and copies over all items 
    public void resizeCountries(){
        String[] newArr = new String[num+1];

        for (int i=0; i < num; i++){
            newArr[i] = countries[i];
        }
        
        countries = newArr;
    }

    public String[] getCountries(){
        return countries;
    }



}

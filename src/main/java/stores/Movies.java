package stores;

import java.time.LocalDate;

import interfaces.IMovies;
import structures.*;

public class Movies implements IMovies{
    Stores stores;
    private MyHashMap<Integer, Movie> hashmap;
    private MyHashMap<Integer, Collection> map;

    /**
     * The constructor for the Movies data store. This is where you should
     * initialise your data structures.
     * @param stores An object storing all the different key stores,
     *               including itself
     */
    public Movies(Stores stores) {
        this.stores = stores;
        // Creates a hash map that stores the id as a key and the other data in a Movie object
        hashmap = new MyHashMap<>();
        // Creates a hashmap that stores the collection id as a key and a collection object as a value
        map = new MyHashMap<>();
    }

    /**
     * Adds data about a film to the data structure
     * 
     * @param id               The unique ID for the film
     * @param title            The English title of the film
     * @param originalTitle    The original language title of the film
     * @param overview         An overview of the film
     * @param tagline          The tagline for the film (empty string if there is no
     *                         tagline)
     * @param status           Current status of the film
     * @param genres           An array of Genre objects related to the film
     * @param release          The release date for the film
     * @param budget           The budget of the film in US Dollars
     * @param revenue          The revenue of the film in US Dollars
     * @param languages        An array of ISO 639 language codes for the film
     * @param originalLanguage An ISO 639 language code for the original language of
     *                         the film
     * @param runtime          The runtime of the film in minutes
     * @param homepage         The URL to the homepage of the film
     * @param adult            Whether the film is an adult film
     * @param video            Whether the film is a "direct-to-video" film
     * @param poster           The unique part of the URL of the poster (empty if
     *                         the URL is not known)
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean add(int id, String title, String originalTitle, String overview, String tagline, String status, Genre[] genres, LocalDate release, long budget, long revenue, String[] languages, String originalLanguage, double runtime, String homepage, boolean adult, boolean video, String poster) {
        
        // If a movie with this id already exists, returns false
        if (hashmap.hasKey(id)) {
            return false;
        }

        //Creates the movie object
        Movie movie = new Movie(title, originalTitle, overview, tagline, status, genres, release, budget, revenue, languages, originalLanguage, runtime, homepage, adult, video, poster);
        // Adds the id and movie to the hashmap and returns true
        hashmap.put(id, movie);
        return true;
    }

    /**
     * Removes a film from the data structure, and any data
     * added through this class related to the film
     * 
     * @param id The film ID
     * @return TRUE if the film has been removed successfully, FALSE otherwise
     */
    @Override
    public boolean remove(int id) {
        //  Removes the film with the given key and returns true if removed or false if not
        boolean isRemoved = hashmap.remove(id);
        if (isRemoved == true) {
            return true; }
        else {
            return false; }
    }

    /**
     * Gets all the IDs for all films
     * 
     * @return An array of all film IDs stored
     */
    @Override
    public int[] getAllIDs() {
        // Gives back the array of film IDs using the keyArray() method defined in MyHashMap
        return hashmap.keyArray();
    }

    /**
     * Finds the film IDs of all films released within a given range. If a film is
     * released either on the start or end dates, then that film should not be
     * included
     * 
     * @param start The start point of the range of dates
     * @param end   The end point of the range of dates
     * @return An array of film IDs that were released between start and end
     */
    @Override
    public int[] getAllIDsReleasedInRange(LocalDate start, LocalDate end) {
        // Creates an a new array with the maximum length of ids required
        int [] arr = new int[getAllIDs().length];

        //Is a count for the number of ids stored
        int count = 0;

        // Loops through the array of all of the ids stored
        for (int id : getAllIDs()) {

            //Gets the release date
            LocalDate date = hashmap.get(id).getRelease();

            //Checks that this date is after the starting data and before the end date
            if (date.isAfter(start) == true && date.isBefore(end) == true) {
                // Stores the id of this date in the array
                arr[count] = id;
                // Increments the count
                count = count + 1;
            }
        }

        // This creates a new array with exactly the number of ids needing to be stored
        // This is done as the previous array is the maximum size of ids and may have wasted space 
        int [] newArray = new int [count];

        // Copies over each id from the old array to the new one
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = arr[i];
        }

        // Returns the new array
        return newArray;
        
    }

    /**
     * Gets the title of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The title of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getTitle(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return null; }
        // Else it finds the given film, uses .getTitle to get the title and returns it 
        else {
            return hashmap.get(id).getTitle();
        }
    }

    /**
     * Gets the original title of a particular film, given the ID number of that
     * film
     * 
     * @param id The movie ID
     * @return The original title of the requested film. If the film cannot be
     *         found, then return null
     */
    @Override
    public String getOriginalTitle(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return null; }
        // Else it finds the given film, uses .getOriginalTitle to get the  original title and returns it 
        else {
            return hashmap.get(id).getOriginalTitle(); 
        }
    }

    /**
     * Gets the overview of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The overview of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getOverview(int id) {
         //If the film with the given id isn't in the hash map it returns null
         if (hashmap.get(id) == null) {
            return null; }
        // Else it finds the given film, uses .getOverview to get the overview and returns it 
        else {
            return hashmap.get(id).getOverview(); 
        }
    }

    /**
     * Gets the tagline of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The tagline of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getTagline(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return null; }
        // Else it finds the given film, uses .getTagline to get the tagline and returns it 
        else {
            return hashmap.get(id).getTagline(); 
        }
    }

    /**
     * Gets the status of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The status of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getStatus(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return null; }
        // Else it finds the given film, uses .getStatus to get the status and returns it 
        else {
            return hashmap.get(id).getStatus(); 
        }
    }

    /**
     * Gets the genres of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The genres of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public Genre[] getGenres(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return null; }
        // Else it finds the given film, uses .getGenres to get the genres and returns them
        else {
            return hashmap.get(id).getGenres(); 
        }
    }

    /**
     * Gets the release date of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The release date of the requested film. If the film cannot be found,
     *         then return null
     */
    @Override
    public LocalDate getRelease(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return null; }
        // Else it finds the given film, uses .getRelease to get the release date and returns it 
        else {
            return hashmap.get(id).getRelease(); 
        }
    }

    /**
     * Gets the budget of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The budget of the requested film. If the film cannot be found, then
     *         return -1
     */
    @Override
    public long getBudget(int id) {
        //If the film with the given id isn't in the hash map it returns -1
        if (hashmap.get(id) == null) {
            return -1; }
        // Else it finds the given film, uses .getBudget to get the budget and returns it 
        else {
            return hashmap.get(id).getBudget(); 
        }
    }

    /**
     * Gets the revenue of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The revenue of the requested film. If the film cannot be found, then
     *         return -1
     */
    @Override
    public long getRevenue(int id) {
        //If the film with the given id isn't in the hash map it returns -1
        if (hashmap.get(id) == null) {
            return -1; }
        // Else it finds the given film, uses .getRevenue to get the revenue and returns it 
        else {
            return hashmap.get(id).getRevenue(); 
        }
    }

    /**
     * Gets the languages of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The languages of the requested film. If the film cannot be found,
     *         then return null
     */
    @Override
    public String[] getLanguages(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return null; }
        // Else it finds the given film, uses .getLanguages to get the languages and returns them
        else {
            return hashmap.get(id).getLanguages(); 
        }
    }

    /**
     * Gets the original language of a particular film, given the ID number of that
     * film
     * 
     * @param id The movie ID
     * @return The original language of the requested film. If the film cannot be
     *         found, then return null
     */
    @Override
    public String getOriginalLanguage(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return null; }
        // Else it finds the given film, uses .getOriginalLanguage to get the original language and returns it 
        else {
            return hashmap.get(id).getOriginalLanguage(); 
        }
    }

    /**
     * Gets the runtime of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The runtime of the requested film. If the film cannot be found, then
     *         return -1
     */
    @Override
    public double getRuntime(int id) {
        //If the film with the given id isn't in the hash map it returns -1
        if (hashmap.get(id) == null) {
            return -1; }
        // Else it finds the given film, uses .getRuntime to get the runtime and returns it 
        else {
            return hashmap.get(id).getRuntime(); 
        }
    }

    /**
     * Gets the homepage of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The homepage of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getHomepage(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return null; }
        // Else it finds the given film, uses .getHomepage to get the homepage and returns it 
        else {
            return hashmap.get(id).getHomepage(); 
        }
    }

    /**
     * Gets weather a particular film is classed as "adult", given the ID number of
     * that film
     * 
     * @param id The movie ID
     * @return The "adult" status of the requested film. If the film cannot be
     *         found, then return false
     */
    @Override
    public boolean getAdult(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return false; }
        // Else it finds the given film, uses .getAdult to get the adult flag and returns it 
        else {
            return hashmap.get(id).getAdult(); 
        }
    }

    /**
     * Gets weather a particular film is classed as "direct-to-video", given the ID
     * number of that film
     * 
     * @param id The movie ID
     * @return The "direct-to-video" status of the requested film. If the film
     *         cannot be found, then return false
     */
    @Override
    public boolean getVideo(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return false; }
        // Else it finds the given film, uses .getVideo to get the video flag and returns it 
        else {
            return hashmap.get(id).getVideo(); 
        }
    }

    /**
     * Gets the poster URL of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The poster URL of the requested film. If the film cannot be found,
     *         then return null
     */
    @Override
    public String getPoster(int id) {
        //If the film with the given id isn't in the hash map it returns null
        if (hashmap.get(id) == null) {
            return null; }
        // Else it finds the given film, uses .getPoster to get the poster and returns it 
        else {
            return hashmap.get(id).getPoster(); 
        }
    }

    /**
     * Sets the average IMDb score and the number of reviews used to generate this
     * score, for a particular film
     * 
     * @param id          The movie ID
     * @param voteAverage The average score on IMDb for the film
     * @param voteCount   The number of reviews on IMDb that were used to generate
     *                    the average score for the film
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean setVote(int id, double voteAverage, int voteCount) {
        
        //Gets the movie using the id
        Movie movie = hashmap.get(id);
        
        // If the movie is not found, returns false
        if (movie == null){
            return false; }
        // Else, sets the average IMDb score and number of reviews to the passes in values
        else {
            movie.setVoteAverage(voteAverage);
            movie.setVoteCount(voteCount);
        }
        return true;
    }

    /**
     * Gets the average score for IMDb reviews of a particular film, given the ID
     * number of that film
     * 
     * @param id The movie ID
     * @return The average score for IMDb reviews of the requested film. If the film
     *         cannot be found, then return -1
     */
    @Override
    public double getVoteAverage(int id) {
        // If the movie with the given id is not found
        if (hashmap.get(id) == null){
            return -1;}
        // Returns the average score for IMDb reviews
        else {
            return hashmap.get(id).getVoteAverage();
        }
    }

    /**
     * Gets the amount of IMDb reviews used to generate the average score of a
     * particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The amount of IMDb reviews used to generate the average score of the
     *         requested film. If the film cannot be found, then return -1
     */
    @Override
    public int getVoteCount(int id) {
        // If the movie with the given id is not found
        if (hashmap.get(id) == null){
            return -1;}
        // Returns the number of IMDb reviews
        else {
            return hashmap.get(id).getVoteCount();
        }
    }

    /**
     * Adds a given film to a collection. The collection is required to have an ID
     * number, a name, and a URL to a poster for the collection
     * 
     * @param filmID                 The movie ID
     * @param collectionID           The collection ID
     * @param collectionName         The name of the collection
     * @param collectionPosterPath   The URL where the poster can
     *                               be found
     * @param collectionBackdropPath The URL where the backdrop can
     *                               be found
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean addToCollection(int filmID, int collectionID, String collectionName, String collectionPosterPath, String collectionBackdropPath) {
        // // If the filmId does not exist, the filmID is not added
        if (hashmap.hasKey(filmID) != true) {
            return false;
        }

        //Creates a new collection object
        Collection collection = new Collection(collectionID, collectionName, collectionPosterPath, collectionBackdropPath);

        // Adds the filmID to the collection
        collection.addFilmID(filmID);

        // Adds the collection ID as a key and the Collection object as a value to a hashmap
        map.put(collectionID, collection);

        // Sets the collection as an attrubute to the movie
        hashmap.get(filmID).setCollection(collection);

        // Returns true if the data is added
        return true;
    }

    /**
     * Get all films that belong to a given collection
     * 
     * @param collectionID The collection ID to be searched for
     * @return An array of film IDs that correspond to the given collection ID. If
     *         there are no films in the collection ID, or if the collection ID is
     *         not valid, return an empty array.
     */
    @Override
    public int[] getFilmsInCollection(int collectionID) {
        // Creates an empty array
        int[] empty = new int[0];

        // If the collectionID leads to no movie being found in the map, it returns the empty list
        if (map.get(collectionID) == null){
            return empty; }
        // If the collectionID is valid, it returns an array with the filmIDs for that list
        else{
            return map.get(collectionID).getFilmIDs();
        }
    }

    /**
     * Gets the name of a given collection
     * 
     * @param collectionID The collection ID
     * @return The name of the collection. If the collection cannot be found, then
     *         return null
     */
    @Override
    public String getCollectionName(int collectionID) {
        // If the collection can't be found
        if (map.get(collectionID) == null){
            return null; }
        // Otherwise, retrives the name of the collection
        else {
            return map.get(collectionID).getCollectionName();
        }
    }

    /**
     * Gets the poster URL for a given collection
     * 
     * @param collectionID The collection ID
     * @return The poster URL of the collection. If the collection cannot be found,
     *         then return null
     */
    @Override
    public String getCollectionPoster(int collectionID) {
        // If the collection can't be found
        if (map.get(collectionID) == null){
            return null; }
        // Otherwise, retrives the poster of the collection
        else {
            return map.get(collectionID).getCollectionPosterPath();
        }
    }

    /**
     * Gets the backdrop URL for a given collection
     * 
     * @param collectionID The collection ID
     * @return The backdrop URL of the collection. If the collection cannot be
     *         found, then return null
     */
    @Override
    public String getCollectionBackdrop(int collectionID) {
        // If the collection can't be found
        if (map.get(collectionID) == null){
            return null; }
        // Otherwise, retrives the backdrop of the collection
        else {
            return map.get(collectionID).getCollectionBackdropPath();
        }
    }

    /**
     * Gets the collection ID of a given film
     * 
     * @param filmID The movie ID
     * @return The collection ID for the requested film. If the film cannot be
     *         found, then return -1
     */
    @Override
    public int getCollectionID(int filmID) {
        // If the requested film cannot be found
        if (hashmap.hasKey(filmID) == false){
            return -1;}
        // Finds the movie with that ID, then gets the relevant Collection object for that movie and then gets the ID of that Collection
        else {
            return hashmap.get(filmID).getCollection().getCollectionID();
        }

    }

    /**
     * Sets the IMDb ID for a given film
     * 
     * @param filmID The movie ID
     * @param imdbID The IMDb ID
     * @return TRUE if the data able to be set, FALSE otherwise
     */
    @Override
    public boolean setIMDB(int filmID, String imdbID) {
        // If the film with the given ID cannot be found, returns false
        if (hashmap.hasKey(filmID) == false){
            return false;}
        // Otherwise, sets the given imdbID to the film and returns true
        else {
            hashmap.get(filmID).setimdbID(imdbID);
            return true;
        }
    }

    /**
     * Gets the IMDb ID for a given film
     * 
     * @param filmID The movie ID
     * @return The IMDb ID for the requested film. If the film cannot be found,
     *         return null
     */
    @Override
    public String getIMDB(int filmID) {
        // If the film with the given ID cannot be found, returns false
        if (hashmap.hasKey(filmID) == false){
            return null;}
        // Returns the imdbID as a string for the given film
        else {
            return hashmap.get(filmID).getimdbID();
        }
    }

    /**
     * Sets the popularity of a given film. If the popularity for a film already exists, replace it with the new value
     * 
     * @param id         The movie ID
     * @param popularity The popularity of the film
     * @return TRUE if the data able to be set, FALSE otherwise
     */
    @Override
    public boolean setPopularity(int id, double popularity) {
        //Checks if the movie exists
        if (hashmap.hasKey(id) == false) {
            return false; }
        // Sets the popularity
        // As this is done with classes, if there is a previously existing value it is already updated
        else {
            hashmap.get(id).setPopularity(popularity);
            return true;
        } 
    }

    /**
     * Gets the popularity of a given film
     * 
     * @param id The movie ID
     * @return The popularity value of the requested film. If the film cannot be
     *         found, then return -1.0. If the popularity has not been set, return 0.0
     */
    @Override
    public double getPopularity(int id) {
        // If the film cannot be found
        if (hashmap.hasKey(id) == false) {
            return -1.0; }
        // If the film can be found
        // Uses a flag in the Movie class which is updated when a value has been set
        // If the value is set, it is returned, otherwise 0.0 is given
        else {
            return hashmap.get(id).getPopularity();
        }
        
    }

    /**
     * Adds a production company to a given film
     * 
     * @param id      The movie ID
     * @param company A Company object that represents the details on a production
     *                company
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean addProductionCompany(int id, Company company) {
        // Checks that the given film exists and if it doesn't, returns false
        if (hashmap.hasKey(id) == false) {
            return false; }
        // Otherwise adds the company and returns true
        else {
            hashmap.get(id).addProdCompany(company);
            return true;
        }
        
    }

    /**
     * Adds a production country to a given film
     * 
     * @param id      The movie ID
     * @param country A ISO 3166 string containing the 2-character country code
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean addProductionCountry(int id, String country) {
        // Checks that the given film exists and if it doesn't, returns false
        if (hashmap.hasKey(id) == false) {
            return false; }
        // Otherwise adds the country and returns true
        else {
            hashmap.get(id).addProdCountry(country);;
            return true;
        }
    }

    /**
     * Gets all the production companies for a given film
     * 
     * @param id The movie ID
     * @return An array of Company objects that represent all the production
     *         companies that worked on the requested film. If the film cannot be
     *         found, then return null
     */
    @Override
    public Company[] getProductionCompanies(int id) {
        // Checks that the given film exists and if it doesn't, returns null
        if (hashmap.hasKey(id) == false) {
            return null; }
        // Otherwise, gives an array of all of the companies
        else {
            return hashmap.get(id).getCompanies();
        }
    }

    /**
     * Gets all the production companies for a given film
     * 
     * @param id The movie ID
     * @return An array of Strings that represent all the production countries (in
     *         ISO 3166 format) that worked on the requested film. If the film
     *         cannot be found, then return null
     */
    @Override
    public String[] getProductionCountries(int id) {
        // Checks that the given film exists and if it doesn't, returns null
        if (hashmap.hasKey(id) == false) {
            return null; }
        // Otherwise, gives an array of all of the countries
        else {
            return hashmap.get(id).getCountries();
        }
    }

    /**
     * States the number of movies stored in the data structure
     * 
     * @return The number of movies stored in the data structure
     */
    @Override
    public int size() {
        return hashmap.size();
    }

    /**
     * Produces a list of movie IDs that have the search term in their title,
     * original title or their overview
     * 
     * @param searchTerm The term that needs to be checked
     * @return An array of movie IDs that have the search term in their title,
     *         original title or their overview. If no movies have this search term,
     *         then an empty array should be returned
     */
    @Override
    public int[] findFilms(String searchTerm) {

        // To count the number of keys actually added
        int count = 0;

        // To make an empty array
        int[] newArr;

        // The array of all of the keys
        int[] allKeys = hashmap.keyArray();

        // Makes an array to store the keys linked to the movies with the required search term
        // Makes this the maximum length it could be
        int [] IDsWanted = new int[allKeys.length];

        for (int i=0; i < allKeys.length; i++){
            // Gets the movie associated with each key
            Movie movie = hashmap.get(allKeys[i]);
            // Checks if the search term is in any 
            if (movie.getTitle().contains(searchTerm) || movie.getOriginalTitle().contains(searchTerm) || movie.getOverview().contains(searchTerm)) {
                IDsWanted[count] = allKeys[i];
                count++;
            }
        }

        // Makes the an array thats the length of the number of keys added
        newArr = new int[count];
        for (int i=0; i < newArr.length; i++) {
            newArr[i] = IDsWanted[i];
        }

        return newArr;
    }
}

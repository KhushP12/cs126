package structures;

public class Collection {
    private int filmID;
    private int collectionID;
    private String collectionName;
    private String collectionPosterPath;
    private String collectionBackdropPath;

    //The array to hold filmIDs
    private int[] filmIDs;
    // Stores the number of film IDs in the array
    private int size;
    // A starting capacity
    private int capacity = 10;
    

    public Collection(int collectionID ,String collectionName, String collectionPosterPath, String collectionBackdropPath) {
        this.collectionID = collectionID;
        this.collectionName = collectionName;
        this.collectionPosterPath = collectionPosterPath;
        this.collectionBackdropPath = collectionBackdropPath;

        size = 0;
        // Creates a new 10 element array
        filmIDs = new int[capacity];
    }

    //To add a film to the array
    public void addFilmID(int filmID){
        int count = 0;
        int[] newArr;

        // If the array is full, it resizes
        if (size == filmIDs.length){
            resize();
        }
        
        // Adds the filmID in the next free space
        filmIDs[size] = filmID;
        size = size + 1;

        // Counts how many items are in the array
        for (int i = 0; i < filmIDs.length; i++){
            if (filmIDs[i] != 0){
                count = count + 1;
            }
        }

        // Only keeps ids and removes all empty spaces
        newArr = new int[count];
        for (int i = 0; i < newArr.length; i++){
            newArr[i] = filmIDs[i];
        }

        // Sets filmIDs to the new array only containing the movie IDs
        filmIDs = newArr;
    }

    // Resizes the array if it is full
    // Makes it one space bigger and copies over all items 
    public void resize(){
        int[] newArr = new int[size+1];

        for (int i=0; i < size; i++){
            newArr[i] = filmIDs[i];
        }
        
        filmIDs = newArr;
    }



    public int getfilmID(){
        return filmID;
    }

    public String getCollectionName(){
        return collectionName;
    }

    public String getCollectionPosterPath(){
        return collectionPosterPath;
    }

    public String getCollectionBackdropPath(){
        return collectionBackdropPath;
    }

    public void setFilmID(int filmID){
        this.filmID = filmID;
    }

    public int[] getFilmIDs(){
        return filmIDs;
    }
    
    public int getCollectionID(){
        return collectionID;
    }
}

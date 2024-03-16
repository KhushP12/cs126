package stores;

import java.time.LocalDateTime;

import interfaces.IRatings;
import structures.*;

public class Ratings implements IRatings {
    Stores stores;
    // The tree for userids
    private MyAVLtree<Integer, Rating> uTree;
    // The tree for movie ids
    private MyAVLtree<Integer, Rating> mTree;
    // The hashmaps for users and movies
    private MyHashMap<Integer, MyAVLtree<Integer, Rating>> userMap;
    private MyHashMap<Integer, MyAVLtree<Integer, Rating>> movieMap;

    // The binary search tree 
    private BinaryTree<Integer, Rating> moviesTree;
    private BinaryTree<Integer, Rating> usersTree;
    private MyHashMap<Integer, BinaryTree<Integer, Rating>> mMap;
    private MyHashMap<Integer, BinaryTree<Integer, Rating>> uMap;


    /**
     * The constructor for the Ratings data store. This is where you should
     * initialise your data structures.
     * @param stores An object storing all the different key stores,
     *               including itself
     */
    public Ratings(Stores stores) {
        this.stores = stores;
        //Create the trees and hashmaps 
        uTree = new MyAVLtree<>();
        mTree = new MyAVLtree<>();
        userMap = new MyHashMap<>();
        movieMap = new MyHashMap<>();

        moviesTree = new BinaryTree<>();
        usersTree = new BinaryTree<>();
        mMap = new MyHashMap<>();
        uMap = new MyHashMap<>();

    }

    /**
     * Adds a rating to the data structure. The rating is made unique by its user ID
     * and its movie ID
     * 
     * @param userID    The user ID
     * @param movieID   The movie ID
     * @param rating    The rating gave to the film by this user (between 0 and 5
     *                  inclusive)
     * @param timestamp The time at which the rating was made
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean add(int userid, int movieid, float rating, LocalDateTime timestamp) {
        // If the userid/movieid is already in the hash map
        // if (userMap.hasKey(userid) || movieMap.hasKey(movieid)){
        //     return false;
        // }

        if (mMap.hasKey(movieid) || uMap.hasKey(userid)) {
            return false;
        }

        Rating rate = new Rating(rating, timestamp);

        // // Inserts the rating as nodes in the rating tree and movie tree
        // uTree.insert(movieid, rate);
        // mTree.insert(userid, rate);

        // // Adds both of these to the hash map
        // userMap.put(userid, uTree);
        // movieMap.put(movieid, mTree);

        // return true;
        usersTree.inOrder();
        usersTree.add(movieid, rate);

        moviesTree.add(userid, rate);

        uMap.put(userid, usersTree);
        mMap.put(movieid, moviesTree);

        return true;
    }

    /**
     * Removes a given rating, using the user ID and the movie ID as the unique
     * identifier
     * 
     * @param userID  The user ID
     * @param movieID The movie ID
     * @return TRUE if the data was removed successfully, FALSE otherwise
     */
    @Override
    public boolean remove(int userid, int movieid) {
        // // If the userid or movie id cannot be found
        // if (userMap.hasKey(userid) == false || movieMap.hasKey(movieid) == false) {
        //     return false; }
        // // If they can be found 
        // else {
        //     userMap.get(userid).remove(movieid);
        //     movieMap.get(movieid).remove(userid); 
        //     return true;}
        // If the userid or movie id cannot be found
        if (uMap.hasKey(userid) == false || mMap.hasKey(movieid) == false) {
            return false; }
        else {
            uMap.get(userid).remove(movieid);
            mMap.get(movieid).remove(userid);
            return true; }

    }

    /**
     * Sets a rating for a given user ID and movie ID. Therefore, should the given
     * user have already rated the given movie, the new data should overwrite the
     * existing rating. However, if the given user has not already rated the given
     * movie, then this rating should be added to the data structure
     * 
     * @param userID    The user ID
     * @param movieID   The movie ID
     * @param rating    The new rating to be given to the film by this user (between
     *                  0 and 5 inclusive)
     * @param timestamp The time at which the rating was made
     * @return TRUE if the data able to be added/updated, FALSE otherwise
     */
    @Override
    public boolean set(int userid, int movieid, float rating, LocalDateTime timestamp) {

        // // If the user has already rated the movie, the rating is updated

        // Rating rate = new Rating(rating, timestamp);

        // // Inserts the rating as nodes in the rating tree and movie tree
        // uTree.insert(movieid, rate);
        // mTree.insert(userid, rate);

        // // Adds both of these to the hash map
        // userMap.put(userid, uTree);
        // movieMap.put(movieid, mTree);
        
        // return true;
        Rating rate = new Rating(rating, timestamp);
        usersTree.set(movieid, rate);
        moviesTree.set(userid, rate);

        uMap.put(userid, usersTree);
        mMap.put(movieid, moviesTree);

        return true;
        
    }

    /**
     * Get all the ratings for a given film
     * 
     * @param movieID The movie ID
     * @return An array of ratings. If there are no ratings or the film cannot be
     *         found, then return an empty array
     */
    @Override
    public float[] getMovieRatings(int movieid) {
        // TODO Implement this function
        float[] empty = new float[0];
        if (mMap.hasKey(movieid) == false) {
            return empty; }
        else {
            Rating[] ratings = mMap.get(movieid).traverse();

            mMap.get(movieid).traversalInOrder(); // For testing 

            float [] newarr = new float[ratings.length];

            for (int i = 0; i < ratings.length ; i++){
                newarr[i] = ratings[i].getRating();
                System.out.println(ratings[i].getRating());
            }
            
            return newarr;
        }
    }   

    /**
     * Get all the ratings for a given user
     * 
     * @param userID The user ID
     * @return An array of ratings. If there are no ratings or the user cannot be
     *         found, then return an empty array
     */
    @Override
    public float[] getUserRatings(int userid) {
        // TODO Implement this function
        float[] empty = new float[0];
        if (uMap.hasKey(userid) == false) {
            return empty; }
        else {
            Rating[] ratings = uMap.get(userid).traverse();

            uMap.get(userid).traversalInOrder(); // For testing 
            
            float [] newarr = new float[ratings.length];

            for (int i = 0; i < ratings.length ; i++){
                newarr[i] = ratings[i].getRating();
                System.out.println(newarr[i]);
            }
            
            return newarr;
        }
    }

    /**
     * Get the average rating for a given film
     * 
     * @param movieID The movie ID
     * @return Produces the average rating for a given film. 
     *         If the film cannot be found in ratings, but does exist in the movies store, return 0.0f. 
     *         If the film cannot be found in ratings or movies stores, return -1.0f.
     */
    @Override
    public float getMovieAverageRating(int movieid) {
        // TODO Implement this function
        return -1;
    }

    /**
     * Get the average rating for a given user
     * 
     * @param userID The user ID
     * @return Produces the average rating for a given user. If the user cannot be
     *         found, or there are no rating, return -1
     */
    @Override
    public float getUserAverageRating(int userid) {
        // TODO Implement this function
        return -1;
    }

    /**
     * Gets the top N movies with the most ratings, in order from most to least
     * 
     * @param num The number of movies that should be returned
     * @return A sorted array of movie IDs with the most ratings. The array should be
     *         no larger than num. If there are less than num movies in the store,
     *         then the array should be the same length as the number of movies
     */
    @Override
    public int[] getMostRatedMovies(int num) {
        // TODO Implement this function
        return null;
    }

    /**
     * Gets the top N users with the most ratings, in order from most to least
     * 
     * @param num The number of users that should be returned
     * @return A sorted array of user IDs with the most ratings. The array should be
     *         no larger than num. If there are less than num users in the store,
     *         then the array should be the same length as the number of users
     */
    @Override
    public int[] getMostRatedUsers(int num) {
        // TODO Implement this function
        return null;
    }

    /**
     * Gets the number of ratings in the data structure
     * 
     * @return The number of ratings in the data structure
     */
    @Override
    public int size() {
        // TODO Implement this function
        return -1;
    }

    /**
     * Get the number of ratings that a movie has
     * 
     * @param movieid The movie id to be found
     * @return The number of ratings the specified movie has. 
     *         If the movie exists in the movies store, but there
     *         are no ratings for it, then return 0. If the movie
     *         does not exist in the ratings or movies store, then
     *         return -1
     */
    @Override
    public int getNumRatings(int movieid) {
        // TODO Implement this function
        return -1;
    }

    /**
     * Get the highest average rated film IDs, in order of there average rating
     * (hightst first).
     * 
     * @param numResults The maximum number of results to be returned
     * @return An array of the film IDs with the highest average ratings, highest
     *         first. If there are less than num movies in the store,
     *         then the array should be the same length as the number of movies
     */
    @Override
    public int[] getTopAverageRatedMovies(int numResults) {
        // TODO Implement this function
        return null;
    }
}

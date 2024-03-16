package stores;

import java.time.LocalDateTime;

// The ratings class
public class Rating {
    private float rating;
    private LocalDateTime timestamp;

    public Rating(float rating, LocalDateTime timestamp) {
        this.rating = rating;
        this.timestamp = timestamp;
    }

    public float getRating(){
        return rating;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }


//     public float findRating(int userID, int movieID){

//     }
}

package src.users;

import java.io.Serializable;

public class VotingManager implements Serializable {
    private static final long serialVersionUID = 1L;
    /** The number of users who have voted in the system.
    */
    private static int voteCount;  
   
    public static void vote() {
        voteCount++;
    }

    public static int getVoteCount() {
        return voteCount;
    }
    public static void setVoteCount(int n) {
    	voteCount = n;
    }
}

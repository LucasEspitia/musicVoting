package users;

import java.util.HashSet;
import java.util.Set;

import music.Song;

/**
 * Public class Voter
 * This class is in charge of voting and containing the user instances in our application
 * It uses a Set to be able to know if the user has already voted for the respective song.
 */
public class Voter extends User {

    private Set<String> votedSongs;
    private boolean doVoteAlready;
    private int remainingVotes;
    private Admin admin = Admin.getInstance();

    /**
     * Constructor for Voter class.
     * @param username The username of the voter.
     * @param password The password of the voter.
     */
    public Voter(String username, String password) {
        super(username, password);
        setRemainingVotes(2);
        setDoVoteAlready(false);
        votedSongs = new HashSet<>();
    }

    /**
     * Getter for remaining votes.
     * @return The number of remaining votes.
     */
    public int getRemainingVotes() {
        return remainingVotes;
    }

    /**
     * Getter for whether the voter has already voted.
     * @return True if the voter has already voted, otherwise false.
     */
    public boolean getDoVoteAlready() {
        return doVoteAlready;
    }

    /**
     * Setter for remaining votes.
     * @param remainingVotes The number of remaining votes to set.
     */
    public void setRemainingVotes(int remainingVotes) {
        this.remainingVotes = remainingVotes;
    }

    /**
     * Setter for whether the voter has already voted.
     * @param doVoteAlready True if the voter has already voted, otherwise false.
     */
    public void setDoVoteAlready(boolean doVoteAlready) {
        this.doVoteAlready = doVoteAlready;
    }

    /**
     * Method to vote for a song.
     * @param song The song to vote for.
     */
    public void doVote(Song song) {
        if (remainingVotes > 0) {
            if (!votedSongs.contains(song.getTitle())) {
                song.addVote();
                remainingVotes -= 1;
                votedSongs.add(song.getTitle());
                System.out.println("Remaining votes: " + getRemainingVotes());
                if(remainingVotes == 0 ) {
                    admin.addNumbersOfUserVoted();
                    doVoteAlready = true;
                }
            } else {
                System.out.println("You have already voted for this song.");
            }
        } else {
            System.out.println("No remaining votes left.");
        }
    }

    /**
     * Method to check if the voter has voted for a specific song.
     * @param song The song to check for.
     * @return True if the voter has voted for the song, otherwise false.
     */
    public boolean hasVotedFor(Song song) {
        return votedSongs.contains(song.getTitle());
    }

}

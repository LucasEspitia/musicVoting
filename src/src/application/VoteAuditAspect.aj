package src.application;

import java.time.LocalDateTime;

import src.users.Voter;

/**
 * VoteAuditAspect is an aspect class that performs audit logging for user votes.
 * It logs the details of user votes, including the voter username, genre, and selected song.
 */
public aspect VoteAuditAspect {
    
    /**
     * Pointcut for user vote.
     * It captures the execution of the confirmVote method in UserLogic.
     */
    pointcut userVote(Voter user, String genre, String songSelected):
        execution(void UserLogic.confirmVote(Voter, String, String)) &&
        args(user, genre, songSelected);
    
    /**
     * Advice executed before a user vote.
     * Logs the timestamp, voter username, genre, and selected song for the vote.
     */
    before(Voter user, String genre, String songSelected): userVote(user, genre, songSelected) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("User " + user.getUsername() + " voted for song " + songSelected + " in genre " + genre + " at " + dateTime);
    }   
}

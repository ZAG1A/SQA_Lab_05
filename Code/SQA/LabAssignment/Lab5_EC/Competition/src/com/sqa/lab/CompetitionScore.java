package com.sqa.lab;

/**
 * * CP353201 Software Quality Assurance (1/2569)
 * Lab#5.2 – Equivalence class testing
 * Instructor: Asst.Prof. Chitsutha Soomlek
 
 * CompetitionScore Class
 * Find the final score by using the highest score out of 3 attempts in a competition
 * Maximum score = 500
 */
public class CompetitionScore  {
    
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 500;
    private static final int TOTAL_ATTEMPTS = 3;
    
    /**
     * Find the maximum score of a participant
     * @param score1 = score obtained from the 1st attempt (0-500)
     * @param score2 = score obtained from the 2nd attempt (0-500)
     * @param score3 = score obtained from the 3rd attempt (0-500)
     * @return the highest score
     * @throws IllegalArgumentException when a score is out of range (0-500)
     */
    public int findMaxScore(int score1, int score2, int score3) {
        validateScore(score1);
        validateScore(score2);
        validateScore(score3);
        
        int maxScore = score1;
        
        if (score2 > maxScore) {
            maxScore = score2;
        }
        
        if (score3 > maxScore) {
            maxScore = score3;
        }
        
        return maxScore;
    }
    
    /**
     * Find the maximum score in the array
     * @param scores array - array size = 3
     * @return the highest score
     * @throws IllegalArgumentException when the size of the array is not 3 and a score is out of range (0-500)
     */
    public int findMaxScore(int[] scores) {
        if (scores == null) {
            throw new IllegalArgumentException("A score cannot be null!!");
        }
        
        if (scores.length != TOTAL_ATTEMPTS) {
            throw new IllegalArgumentException(
                "There should be " + TOTAL_ATTEMPTS + " scores but found " + scores.length + " attempts."
            );
        }
        
        int maxScore = scores[0];
        validateScore(scores[0]);
        
        for (int i = 1; i < scores.length; i++) {
            validateScore(scores[i]);
            if (scores[i] > maxScore) {
                maxScore = scores[i];
            }
        }
        
        return maxScore;
    }
    
    /**
     * Check if the score is in the valid range (0-500)
     * @param score to be checked
     * @throws IllegalArgumentException when a score is out of range (0-500)
     */
    private void validateScore(int score) {
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException(
                "The valid range of a score is " + MIN_SCORE + " to " + MAX_SCORE + 
                ". But, the input score is " + score + "."
            );
        }
    }
}
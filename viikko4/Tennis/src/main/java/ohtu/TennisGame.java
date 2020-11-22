package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;

    private String player1Name;
    private String player2Name;

    private final String[] SCORE_NAMES = {"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name) {
            m_score1++;
        } else {
            m_score2++;
        }
    }

    public String getScore() {
        if (m_score1 >= 4 || m_score2 >= 4) return getScorePointsOver4();
        else return getScorePointsLessThan4();
    }

    private String getScorePointsOver4() {
        if (m_score1 == m_score2) return "Deuce";
        if (m_score1 - m_score2 == 1) return "Advantage player1";
        if (m_score1 - m_score2 == -1) return "Advantage player2";
        if (m_score1 - m_score2 >= 2) return "Win for player1";
        if (m_score1 - m_score2 <= -2) return "Win for player2";

        return "";
    }

    private String getScorePointsLessThan4() {
        if (m_score1 == m_score2) {
            return SCORE_NAMES[m_score1] + "-All";
        } else {
            return SCORE_NAMES[m_score1] + "-" + SCORE_NAMES[m_score2];
        }
    }
}

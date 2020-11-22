package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name)
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        String score = "";
        int tempScore=m_score1;
        if (m_score1==m_score2) {
            return sameScore();
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            int minusResult = m_score1 - m_score2;
            if (minusResult == 1) {
                return "Advantage " + player1Name;
            } else if (minusResult == -1) {
                return "Advantage " + player2Name;
            } else if (minusResult >= 2) {
                return "Win for " + player1Name;
            }
            return "Win for " + player2Name;
        } else {
            for (int i=1; i<3; i++)
            {
                if (i > 1) {
                    score+="-";
                    tempScore = m_score2;
                }
                score+=headingText(tempScore);
            }
        }
        return score;
    }

    private String sameScore() {
        if (m_score1 < 4) return headingText(m_score1) + "-All";
        return "Deuce";
    }

    private String headingText(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "";
        }
    }
}
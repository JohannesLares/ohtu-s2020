
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAssists() {
        return this.assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return this.goals;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getPenalties() {
        return this.penalties;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return this.team;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGames() {
        return this.games;
    }

    public int getPoints() {
        return this.goals + this.assists;
    }

    @Override
    public String toString() {
        return name + " " + nationality + " " + goals + " + " + assists + " = " + this.getPoints();
    }
      
    @Override
    public int compareTo(Player p) {
        return Integer.compare(this.getPoints(), p.getPoints());
    }
}

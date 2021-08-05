package model;

public class RockSong extends Song {
    private final String KINDSONG = "Rock";

    public RockSong() {
    }

    public String getKINDSONG() {
        return KINDSONG;
    }

    @Override
    public String toString() {
        return "RockSong{" +
                "KINDSONG='" + KINDSONG + '\'' +
                '}'+super.toString();
    }
}

package model;

public class RapSong extends Song{
    private final String KINDSONG = "Rap";

    public RapSong() {
    }

    public String getKINDSONG() {
        return KINDSONG;
    }

    @Override
    public String toString() {
        return "RapSong{" +
                "KINDSONG='" + KINDSONG + '\'' +
                '}'+super.toString();
    }
}

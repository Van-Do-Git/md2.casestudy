package model;

public class ClassicalSong extends Song{
    private final String KINDSONG = "Classical";

    public ClassicalSong() {
    }

    public String getKINDSONG() {
        return KINDSONG;
    }

    @Override
    public String toString() {
        return "ClassicalSong{" +
                "KINDSONG='" + KINDSONG + '\'' +
                '}'+super.toString();
    }
}

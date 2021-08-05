package model;

public class FactorySong {
    public Song creatSong(KindOfSong kindOfSong) {
        switch (kindOfSong) {
            case RAP:
                return new RapSong();
            case ROCK:
                return new RockSong();
            case CLASSIC:
                return new ClassicalSong();
            default:
                return null;
        }
    }
}

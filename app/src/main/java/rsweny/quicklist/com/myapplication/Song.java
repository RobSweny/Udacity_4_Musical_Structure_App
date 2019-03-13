package rsweny.quicklist.com.myapplication;

class Song {

    private String song_name;
    private String artist_name;
    private String song_length;
    private String song_genre;


    public Song(String m_song_name, String m_artist_name, String m_song_length, String m_song_genre) {
        // Creating song object
        song_name = m_song_name;
        artist_name = m_artist_name;
        song_length = m_song_length;
        song_genre = m_song_genre;
    }

    public String getSongName() {
        return song_name;
    }

    public String getArtistName(){
        return artist_name;
    }

    public String getSongLength() {
        return song_length;
    }

    public String getSongGenre() {
        return song_genre;
    }
}

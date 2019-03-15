package rsweny.quicklist.com.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

class Song implements Parcelable {

    private String song_name;
    private String artist_name;
    private String song_length;
    private String song_genre;

    public Song(String song_name, String artist_name, String song_length, String song_genre) {
        // Creating song object
        this.song_name = song_name;
        this.artist_name = artist_name;
        this.song_length = song_length;
        this.song_genre = song_genre;
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getSongName() {
        return song_name;
    }

    public String getArtistName() {
        return artist_name;
    }

    public String getSongLength() {
        return song_length;
    }

    public String getSongGenre() {
        return song_genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(song_name);
        dest.writeString(artist_name);
        dest.writeString(song_length);
        dest.writeString(song_genre);
    }

    public Song(Parcel in) {
        song_name = in.readString();
        artist_name = in.readString();
        song_length = in.readString();
        song_genre = in.readString();
    }
}

package rsweny.quicklist.com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PlayingSongActivity extends AppCompatActivity {

    // Declaring views
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        // TODO 1 - Throw intents into this section
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_music_player:
                    savePreferences();
                    Intent intent = new Intent(PlayingSongActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_play_song:
                    break;
            } // End Switch
            return false;
        }
    };

    private Button play_pause_song_button;
    private TextView current_song_textview;
    private TextView current_artist_textview;
    private int song_position_number;
    private Boolean play_pause = true;
    private TextView cover_art_textview;
    private ImageView cover_art_imageview;
    public ArrayList<Song> songs;
    public ArrayList<Song> song_holder;
    private String filteredGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_song_activity);

        // Checking song_position_number
        SharedPreferences sp = getSharedPreferences("your_prefs", PlayingSongActivity.MODE_PRIVATE);
        song_position_number = sp.getInt("CURRENT_SONG_POSITION", -1);

        // Initializing
        BottomNavigationView bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bottom_navigation.getMenu().findItem(R.id.navigation_play_song).setVisible(false);
        // Declare buttons
        Button previous_song_button = findViewById(R.id.previous_song_button);
        play_pause_song_button = findViewById(R.id.play_pause_song_button);
        Button next_song_button = findViewById(R.id.next_song_button);
        current_song_textview = findViewById(R.id.current_song_textview);
        current_artist_textview = findViewById(R.id.current_artist_textview);
        cover_art_imageview = findViewById(R.id.cover_art_imageview);
        cover_art_textview = findViewById(R.id.cover_art_textview);

        songs = new ArrayList<>();
        song_holder = new ArrayList<>();
        songs.add(new Song("Purple Haze", "Jimi Hendrix", "4.32", "Rock"));
        songs.add(new Song("Hotel California", "The Eagles", "2.32", "Rock"));
        songs.add(new Song("Kashmir", "Led Zepplin", "5.32", "Rock"));
        songs.add(new Song("Free Bird", "Lynard Skynard", "10.32", "Rock"));
        /*  RAP    */
        songs.add(new Song("Juicy", "Notorious BIG", "4.32", "Rap"));
        songs.add(new Song("It Was a Good Day", "Ice Cube", "2.42", "Rap"));
        /*  BLUES    */
        songs.add(new Song("The Thrill Is Gone", "BB.King", "2.52", "Blues"));
        songs.add(new Song("My Babe", "Little Walter", "1.32", "Blues"));
        /*  ELECTRONIC    */
        songs.add(new Song("Summer", "Calvin Harris", "10.22", "Electronic"));
        songs.add(new Song("I Remember", "Deadmau5", "5.12", "Electronic"));
        /*  SALSA    */
        songs.add(new Song("Quimbara", "Celia Cruz", "3.32", "Salsa"));
        songs.add(new Song("Aguanile", "Marc Anthony", "5.32", "Salsa"));
        /*  FOLK    */
        songs.add(new Song("Like a rolling Stone", "Bob Dylan", "4.32", "Folk"));
        songs.add(new Song("Both Sides Now", "Joni Mitchell", "1.34", "Folk"));
        /*  REGGAE    */
        songs.add(new Song("One Love", "Bob Marley", "4.32", "Reggae"));
        songs.add(new Song("I Can See Clearly Now", "Jimmy Cliff", "2.32", "Reggae"));


        // Get the position of the user clicked button
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            song_position_number = extras.getInt("song_position_number");
        } // End if

        previous_song_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Decrease song position number
                song_position_number--;
                try {
                    updateViews();
                } catch (IndexOutOfBoundsException e) {
                    // Jump to last song on list to avoid error
                    song_position_number = songs.size() - 1;
                    updateViews();
                }
            }
        });

        play_pause_song_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play_pause) {
                    play_pause_song_button.setBackgroundResource(R.drawable.pause_icon);
                    play_pause = false;
                } else {
                    play_pause_song_button.setBackgroundResource(R.drawable.play_song_icon);
                    play_pause = true;
                }
            }
        });

        next_song_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Decrease song position number
                song_position_number++;
                try {
                    updateViews();
                } catch (IndexOutOfBoundsException e) {
                    // Set to first song on list to avoid error
                    song_position_number = 0;
                    updateViews();
                }
            }
        });

        // Update Textboxes
        updateViews();
    } // End onCreate

    private void savePreferences() {
        SharedPreferences sp = getSharedPreferences("your_prefs", PlayingSongActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("CURRENT_SONG_POSITION", song_position_number);
        editor.apply();
    }

    public void updateViews() {
        int numtries = 3;
        while (numtries-- != 0)
            try {
                current_song_textview.setText(songs.get(song_position_number).getSongName());
                current_artist_textview.setText(songs.get(song_position_number).getArtistName());

                cover_art_imageview.setBackgroundColor(getRandomColor());
                cover_art_textview.setText(songs.get(song_position_number).getSongName());
                cover_art_textview.setTextColor(getRandomColor());
                break;
            } catch (Exception e) {
                Toast.makeText(this, filteredGenre + " genre loaded", Toast.LENGTH_SHORT).show();
                song_position_number = 0;
            }
    }

    public int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
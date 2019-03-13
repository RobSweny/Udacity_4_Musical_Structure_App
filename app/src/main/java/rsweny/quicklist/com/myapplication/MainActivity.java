package rsweny.quicklist.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    // Declaring views
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        // TODO 1 - Throw intents into this section
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_music_player:
                    break;

                case R.id.navigation_play_song:
                    Intent intent = new Intent(MainActivity.this, PlayingSongActivity.class);
                    startActivity(intent);
                    break;
            } // End Switch
            return false;
        }
    };

    // Declare
    private ListView listView;
    private BottomNavigationView bottom_navigation;
    private ImageButton play_imagebutton;
    private static WordAdapter adapter;
    ArrayList<Song> songs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declaring
        bottom_navigation = findViewById(R.id.bottom_navigation);
        listView = findViewById(R.id.listView);
        play_imagebutton = findViewById(R.id.play_imagebutton);

        // Navigation
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottom_navigation.getMenu().findItem(R.id.navigation_music_player).setVisible(false);

        songs = new ArrayList<>();
        //("song_name", "artist_name", "song_length", "song_genre")
        /*  ROCK    */
        songs.add(new Song("Purple Haze","Jimi Hendrix", "4.32", "Rock"));
        songs.add(new Song("Hotel California","The Eagles", "2.32", "Rock"));
        songs.add(new Song("Kashmir","Led Zepplin", "5.32", "Rock"));
        songs.add(new Song("Free Bird","Lynard Skynard", "10.32", "Rock"));
        /*  RAP    */
        songs.add(new Song("Juicy","Notorious BIG", "4.32", "Rap"));
        songs.add(new Song("It Was a Good Day","Ice Cube", "2.42", "Rap"));
        /*  BLUES    */
        songs.add(new Song("The Thrill Is Gone","BB.King", "2.52", "Blues"));
        songs.add(new Song("My Babe","Little Walter", "1.32", "Blues"));
        /*  ELECTRONIC    */
        songs.add(new Song("Summer","Calvin Harris", "10.22", "Electronic"));
        songs.add(new Song("I Remember","Deadmau5", "5.12", "Electronic"));
        /*  SALSA    */
        songs.add(new Song("Quimbara","Celia Cruz", "3.32", "Salsa"));
        songs.add(new Song("Aguanile","Marc Anthony", "5.32", "Salsa"));
        /*  FOLK    */
        songs.add(new Song("Like a rolling Stone","Bob Dylan", "4.32", "Folk"));
        songs.add(new Song("Both Sides Now","Joni Mitchell", "1.34", "Folk"));
        /*  REGGAE    */
        songs.add(new Song("One Love","Bob Marley", "4.32", "Reggae"));
        songs.add(new Song("I Can See Clearly Now","Jimmy Cliff", "2.32", "Reggae"));

        adapter = new WordAdapter(songs, getApplicationContext());
        listView.setAdapter(adapter);

        // Set onClick Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songs.get(position);
                Snackbar.make(view, song.getSongName() + "\n" + song.getArtistName(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });




    } // End onCreate
} // End Main Activity

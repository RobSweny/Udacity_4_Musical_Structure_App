package rsweny.quicklist.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

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
    private Button rock_button;
    private Button rap_button;
    private Button blues_button;
    private Button electronic_button;
    private Button salsa_button;
    private Button folk_button;
    private Button reggae_button;
    private Button all_button;
    private String filteredGenre;
    private int song_current_position;
    ArrayList<String> songs_array = new ArrayList<>();
    ArrayList<Song> songs;
    ArrayList<Song> rap_songs;
    ArrayList<Song> rock_songs;
    ArrayList<Song> reggae_songs;
    ArrayList<Song> folk_songs;
    ArrayList<Song> salsa_songs;
    ArrayList<Song> electronic_songs;
    ArrayList<Song> blues_songs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declaring
        bottom_navigation = findViewById(R.id.bottom_navigation);
        listView = findViewById(R.id.listView);
        play_imagebutton = findViewById(R.id.play_imagebutton);
        rock_button = findViewById(R.id.rock_button);
        rap_button = findViewById(R.id.rap_button);
        blues_button = findViewById(R.id.blues_button);
        electronic_button = findViewById(R.id.electronic_button);
        salsa_button = findViewById(R.id.salsa_button);
        folk_button = findViewById(R.id.folk_button);
        reggae_button = findViewById(R.id.reggae_button);
        all_button = findViewById(R.id.all_button);

        // Navigation
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottom_navigation.getMenu().findItem(R.id.navigation_music_player).setVisible(false);

        songs = new ArrayList<>();
        rock_songs = new ArrayList<>();
        rap_songs = new ArrayList<>();
        reggae_songs = new ArrayList<>();
        electronic_songs = new ArrayList<>();
        folk_songs = new ArrayList<>();
        blues_songs = new ArrayList<>();
        salsa_songs = new ArrayList<>();

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
                Intent intent = new Intent(MainActivity.this, PlayingSongActivity.class);
                intent.putExtra("songs_array", songs_array);
                startActivity(intent);
            } // End onItemClick
        });

        all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetHighlightedColors();
                adapter = new WordAdapter(songs, getApplicationContext());
                listView.setAdapter(adapter);
                all_button.setBackgroundResource(R.color.scrollview_blue_selected);
            }
        });

        rock_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetHighlightedColors();
                filteredGenre = "Rock";
                for(int i = 0; i < songs.size(); i++){
                    if(songs.get(i).getSongGenre().equals("Rock")){
                        rock_songs.add(songs.get(i));
                    }
                }
                adapter = new WordAdapter(rock_songs, getApplicationContext());
                listView.setAdapter(adapter);
                rock_button.setBackgroundResource(R.color.scrollview_blue_selected);
            }
        });

        rap_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredGenre = "Rap";
            }
        });

        blues_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredGenre = "Blues";
            }
        });

        electronic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredGenre = "Electronic";
            }
        });

        folk_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredGenre = "Folk";
            }
        });

        salsa_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredGenre = "Salsa";
            }
        });
        reggae_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredGenre = "Reggae";
            }
        });

    } // End onCreate

    public void resetHighlightedColors(){
        all_button.setBackgroundResource(R.color.scrollview_blue);
        reggae_button.setBackgroundResource(R.color.scrollview_blue);
        rock_button.setBackgroundResource(R.color.scrollview_blue);
        rap_button.setBackgroundResource(R.color.scrollview_blue);
        blues_button.setBackgroundResource(R.color.scrollview_blue);
        electronic_button.setBackgroundResource(R.color.scrollview_blue);
        salsa_button.setBackgroundResource(R.color.scrollview_blue);
        folk_button.setBackgroundResource(R.color.scrollview_blue);
    }

} // End Main Activity

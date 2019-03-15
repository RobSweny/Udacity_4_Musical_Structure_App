package rsweny.quicklist.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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
    public static final String PREFS_NAME = "Musical_app_preferences";
    public static final String SONGHOLDER = "Song_holder";
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
    private TextView play_textview;
    public ArrayList<Song> songs;
    public ArrayList<Song> song_holder;
    private Context context;

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
        play_textview = findViewById(R.id.play_textview);

        // Navigation
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottom_navigation.getMenu().findItem(R.id.navigation_music_player).setVisible(false);

        songs = new ArrayList<>();
        song_holder = new ArrayList<>();

        //("song_name", "artist_name", "song_length", "song_genre")
        /*  ROCK    */
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

        // Add all to the song holder
        song_holder.addAll(songs);

        adapter = new WordAdapter(songs, getApplicationContext());
        listView.setAdapter(adapter);

        // Set onClick Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                passArrayList();
            } // End onItemClick
        });


        all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enablingButtons();
                resetHighlightedColors();
                filteredGenre = "";
                song_holder.addAll(songs);
                adapter = new WordAdapter(song_holder, getApplicationContext());
                listView.setAdapter(adapter);
                all_button.setBackgroundResource(R.color.scrollview_blue_selected);
                all_button.setEnabled(false);
            }
        });

        rock_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enablingButtons();
                resetHighlightedColors();
                filteredGenre = "Rock";
                for (int i = 0; i < songs.size(); i++) {
                    if (songs.get(i).getSongGenre().equals("Rock")) {
                        song_holder.add(songs.get(i));
                    }
                }
                adapter = new WordAdapter(song_holder, getApplicationContext());
                listView.setAdapter(adapter);
                rock_button.setBackgroundResource(R.color.scrollview_blue_selected);
                rock_button.setEnabled(false);
            }
        });

        rap_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enablingButtons();
                resetHighlightedColors();
                filteredGenre = "Rap";
                for (int i = 0; i < songs.size(); i++) {
                    if (songs.get(i).getSongGenre().equals("Rap")) {
                        song_holder.add(songs.get(i));
                    }
                }
                adapter = new WordAdapter(song_holder, getApplicationContext());
                listView.setAdapter(adapter);
                rap_button.setBackgroundResource(R.color.scrollview_blue_selected);
                rap_button.setEnabled(false);
            }
        });

        blues_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enablingButtons();
                resetHighlightedColors();
                filteredGenre = "Blues";
                for (int i = 0; i < songs.size(); i++) {
                    if (songs.get(i).getSongGenre().equals("Blues")) {
                        song_holder.add(songs.get(i));
                    }
                }
                adapter = new WordAdapter(song_holder, getApplicationContext());
                listView.setAdapter(adapter);
                blues_button.setBackgroundResource(R.color.scrollview_blue_selected);
                blues_button.setEnabled(false);
            }
        });

        electronic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enablingButtons();
                resetHighlightedColors();
                filteredGenre = "Electronic";
                for (int i = 0; i < songs.size(); i++) {
                    if (songs.get(i).getSongGenre().equals("Electronic")) {
                        song_holder.add(songs.get(i));
                    }
                }
                adapter = new WordAdapter(song_holder, getApplicationContext());
                listView.setAdapter(adapter);
                electronic_button.setBackgroundResource(R.color.scrollview_blue_selected);
                electronic_button.setEnabled(false);
            }
        });

        folk_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enablingButtons();
                resetHighlightedColors();
                filteredGenre = "Folk";
                for (int i = 0; i < songs.size(); i++) {
                    if (songs.get(i).getSongGenre().equals("Folk")) {
                        song_holder.add(songs.get(i));
                    }
                }
                adapter = new WordAdapter(song_holder, getApplicationContext());
                listView.setAdapter(adapter);
                folk_button.setBackgroundResource(R.color.scrollview_blue_selected);
            }
        });

        salsa_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enablingButtons();
                resetHighlightedColors();
                filteredGenre = "Salsa";
                for (int i = 0; i < songs.size(); i++) {
                    if (songs.get(i).getSongGenre().equals("Salsa")) {
                        song_holder.add(songs.get(i));
                    }
                }
                adapter = new WordAdapter(song_holder, getApplicationContext());
                listView.setAdapter(adapter);
                salsa_button.setBackgroundResource(R.color.scrollview_blue_selected);
                salsa_button.setEnabled(false);
            }
        });
        reggae_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enablingButtons();
                resetHighlightedColors();
                filteredGenre = "Reggae";
                for (int i = 0; i < songs.size(); i++) {
                    if (songs.get(i).getSongGenre().equals("Reggae")) {
                        song_holder.add(songs.get(i));
                    }
                }
                adapter = new WordAdapter(song_holder, getApplicationContext());
                listView.setAdapter(adapter);
                reggae_button.setBackgroundResource(R.color.scrollview_blue_selected);
                reggae_button.setEnabled(false);
            }
        });

    } // End onCreate


    private void passArrayList() {
        Intent intentBundle = new Intent(MainActivity.this, PlayingSongActivity.class);
        startActivity(intentBundle);
    }

    public void resetHighlightedColors() {
        all_button.setBackgroundResource(R.color.scrollview_blue);
        reggae_button.setBackgroundResource(R.color.scrollview_blue);
        rock_button.setBackgroundResource(R.color.scrollview_blue);
        rap_button.setBackgroundResource(R.color.scrollview_blue);
        blues_button.setBackgroundResource(R.color.scrollview_blue);
        electronic_button.setBackgroundResource(R.color.scrollview_blue);
        salsa_button.setBackgroundResource(R.color.scrollview_blue);
        folk_button.setBackgroundResource(R.color.scrollview_blue);
    }

    // Clicking the buttons multiple times would consatantly add more songs to the filtered arraylist
    public void enablingButtons() {
        // clear array for next
        song_holder.clear();

        // Enable all buttons
        all_button.setEnabled(true);
        reggae_button.setEnabled(true);
        rock_button.setEnabled(true);
        rap_button.setEnabled(true);
        blues_button.setEnabled(true);
        electronic_button.setEnabled(true);
        salsa_button.setEnabled(true);
        folk_button.setEnabled(true);
    }
} // End Main Activity

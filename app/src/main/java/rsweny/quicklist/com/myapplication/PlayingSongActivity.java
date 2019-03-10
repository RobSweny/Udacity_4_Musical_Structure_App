package rsweny.quicklist.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PlayingSongActivity extends AppCompatActivity {

    // Declaring views
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        // TODO 1 - Throw intents into this section
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_music_player:
                    Intent intent = new Intent(PlayingSongActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_play_song:
                    break;
            } // End Switch
            return false;
        }
    };

    // Declare buttons
    private Button previous_song_button;
    private Button play_pause_song_button;
    private Button next_song_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_song_activity);

        // Initializing
        BottomNavigationView bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bottom_navigation.getMenu().findItem(R.id.navigation_play_song).setVisible(false);
        previous_song_button = findViewById(R.id.previous_song_button);
        play_pause_song_button = findViewById(R.id.previous_song_button);
        next_song_button = findViewById(R.id.previous_song_button);

    } // End onCreate

    public void songChange(View view) {
        if (view.getId() == R.id.previous_song_button) {
            Toast.makeText(this,"PREVIOUS",Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.play_pause_song_button) {
            Toast.makeText(this,"PLAY|PAUSE",Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.next_song_button) {
            Toast.makeText(this,"NEXT",Toast.LENGTH_SHORT).show();
        }
    } // End songChange
}
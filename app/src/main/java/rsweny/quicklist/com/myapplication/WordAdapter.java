package rsweny.quicklist.com.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Song> implements View.OnClickListener{

    private ArrayList<Song> songs;
    Context mContext;


    public WordAdapter( ArrayList<Song> song, Context context) {
        super(context,R.layout.custom_listview_item, song);
        this.songs = song;
        this.mContext=context;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView songNameTextView;
        TextView artistNameTextView;
        ImageButton play_imagebutton;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Song song = (Song)object;

        switch (v.getId())
        {
            case R.id.play_imagebutton:
                Snackbar.make(v, "Song Name: " + song.getSongName(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    } // End onClick

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Song song = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_listview_item, parent, false);

            // Initialize in the viewHolder
            viewHolder.songNameTextView = convertView.findViewById(R.id.song_name_textview);
            viewHolder.artistNameTextView = convertView.findViewById(R.id.artist_name_textview);
            viewHolder.play_imagebutton = convertView.findViewById(R.id.play_imagebutton);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        // Set texts and listeners
        viewHolder.songNameTextView.setText(song.getSongName());
        viewHolder.artistNameTextView.setText(song.getArtistName());
        viewHolder.play_imagebutton.setOnClickListener(this);
        viewHolder.play_imagebutton.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }




}

package rsweny.quicklist.com.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Song> {

    public WordAdapter(Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_listview_item, parent, false);
        }



        Song currentSong = getItem(position);

        // Song Name
        TextView nameTextView = listItemView.findViewById(R.id.song_name_textview);
        nameTextView.setText(currentSong.getSongName());

        // Artist Name
        TextView numberTextView = listItemView.findViewById(R.id.artist_name_textview);
        numberTextView.setText(currentSong.getArtistName());

        return listItemView;
    }


}

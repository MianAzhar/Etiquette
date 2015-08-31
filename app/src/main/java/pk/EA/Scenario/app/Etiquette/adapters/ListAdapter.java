package pk.EA.Scenario.app.Etiquette.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pk.EA.Scenario.app.Etiquette.R;
import pk.EA.Scenario.app.Etiquette.utils.Etiquette;

/**
 * Created by Mian on 8/17/2015.
 */
public class ListAdapter extends ArrayAdapter<String>
{
    ArrayList<Etiquette> resources;
    ArrayList<String> textList;
    Context context;
    public ListAdapter(Context c, ArrayList<String> text, ArrayList<Etiquette> objects)
    {
        super(c , R.layout.list_item, R.id.detailText, text);
        context = c;
        this.resources = objects;
        this.textList = text;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflator.inflate(R.layout.list_item, parent ,false);

        TextView textView = (TextView) row.findViewById(R.id.detailText);
        textView.setText(textList.get(position));

        ImageView out = (ImageView)row.findViewById(R.id.backgroundImage);
        //out.setBackgroundResource(resources[position]);

        Picasso.with(context).load(resources.get(position).getUrl()).into(out);

        return row;

    }
}
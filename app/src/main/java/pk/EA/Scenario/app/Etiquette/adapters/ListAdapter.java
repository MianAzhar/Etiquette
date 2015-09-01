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

        ImageView out = (ImageView)row.findViewById(R.id.backgroundImage);
        Picasso.with(context).load(resources.get(position).getUrl()).into(out);

        TextView textView = (TextView) row.findViewById(R.id.detailText);
        textView.setText(textList.get(position));

        ImageView f1 = (ImageView)row.findViewById(R.id.fire1);
        ImageView f2 = (ImageView)row.findViewById(R.id.fire2);
        ImageView f3 = (ImageView)row.findViewById(R.id.fire3);
        ImageView f4 = (ImageView)row.findViewById(R.id.fire4);
        ImageView f5 = (ImageView)row.findViewById(R.id.fire5);

        int rate = 0;

        try{
           rate = Integer.parseInt(resources.get(position).getMeter());
        }
        catch (Exception ex){}

        switch (rate)
        {
            case 1:
                f1.setImageResource(R.drawable.firefill);
                f2.setImageResource(R.drawable.fireempty);
                f3.setImageResource(R.drawable.fireempty);
                f4.setImageResource(R.drawable.fireempty);
                f5.setImageResource(R.drawable.fireempty);
                break;
            case 2:
                f1.setImageResource(R.drawable.firefill);
                f2.setImageResource(R.drawable.firefill);
                f3.setImageResource(R.drawable.fireempty);
                f4.setImageResource(R.drawable.fireempty);
                f5.setImageResource(R.drawable.fireempty);
                break;
            case 3:
                f1.setImageResource(R.drawable.firefill);
                f2.setImageResource(R.drawable.firefill);
                f3.setImageResource(R.drawable.firefill);
                f4.setImageResource(R.drawable.fireempty);
                f5.setImageResource(R.drawable.fireempty);
                break;
            case 4:
                f1.setImageResource(R.drawable.firefill);
                f2.setImageResource(R.drawable.firefill);
                f3.setImageResource(R.drawable.firefill);
                f4.setImageResource(R.drawable.firefill);
                f5.setImageResource(R.drawable.fireempty);
                break;
            case 5:
                f1.setImageResource(R.drawable.firefill);
                f2.setImageResource(R.drawable.firefill);
                f3.setImageResource(R.drawable.firefill);
                f4.setImageResource(R.drawable.firefill);
                f5.setImageResource(R.drawable.firefill);
                break;
            default:
                f1.setImageResource(R.drawable.fireempty);
                f2.setImageResource(R.drawable.fireempty);
                f3.setImageResource(R.drawable.fireempty);
                f4.setImageResource(R.drawable.fireempty);
                f5.setImageResource(R.drawable.fireempty);
                break;
        }
        return row;
    }
}
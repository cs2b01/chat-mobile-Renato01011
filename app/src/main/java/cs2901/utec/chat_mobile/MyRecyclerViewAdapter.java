package cs2901.utec.chat_mobile;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private JSONArray elements;
    private Context context;
    public String userFromId;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, name;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            username = itemView.findViewById(R.id.username);
            container = itemView.findViewById(R.id.container);
        }
    }


    public MyRecyclerViewAdapter(JSONArray elements, Context context, String userFromId) {
        this.elements = elements;
        this.context = context;
        this.userFromId = userFromId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_listitem, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        try {
            JSONObject element = elements.getJSONObject(position);
            String name = element.getString("name")+" "+element.getString("fullname");
            final String username = element.getString("username");
            final String id = element.getString("id");
            holder.name.setText(name);
            holder.username.setText(username);

            holder.container.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent goToMessage = new Intent(context,MessageActivity.class);
                    goToMessage.putExtra("user_from_id",userFromId);
                    goToMessage.putExtra("user_to_id",id);
                    goToMessage.putExtra("username", username);
                    context.startActivity(goToMessage);
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return elements.length();
    }
}
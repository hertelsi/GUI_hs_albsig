package com.example.einkaufsliste.ui.lists;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.einkaufsliste.R;

import java.util.ArrayList;

public class ListDataAdapter extends RecyclerView.Adapter<ListDataAdapter.FirstViewHolder> {
    private ArrayList<String> liste;


    public ListDataAdapter(ArrayList<String> liste) {
        this.liste = liste;
    }

    @NonNull
    @Override
    public FirstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Datendarstellung holen
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.buyinglist_item, parent, false);
        FirstViewHolder viewHolder = new FirstViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FirstViewHolder holder, int position) {

        String name = liste.get(position);
        holder.button.setText(name);


        holder.button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    int padding = holder.button.getPaddingRight();
                    int leftXDrawable = (int) holder.button.getRight() - holder.button.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - padding;
                    int rightXDrawable = (int) holder.button.getRight() - padding;
                    if ((leftXDrawable <= event.getRawX()) && (event.getRawX() <= rightXDrawable)){
                        //click on rightdrawable
                        holder.remove(position);
                        return true;
                    } else {
                        holder.UpdateText(holder.button.getText().toString());
                        holder.remove(position);



                    }
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class FirstViewHolder extends RecyclerView.ViewHolder {
        private View layout;
        private TextView textView;
        private Button button;
        private EditText editText;

        public FirstViewHolder(View v) {
            super(v);
            layout = v;
            textView = v.findViewById(R.id.buyinglist_item_button);
            button = v.findViewById(R.id.buyinglist_item_button);
        }

        public void add(int position, String item) {
            liste.add(position, item);
            Log.d("test", "hallo");
            Log.d("test", liste.toString());
            notifyItemInserted(position);
        }

        public void remove(int position) {
            liste.remove(position);
            notifyDataSetChanged();
        }

        public void UpdateText(String t){
            editText = layout.findViewById(R.id.edit_text_ListData);
            Log.d("test",t);
            //editText.setText(t);
            notifyDataSetChanged();

        }

    }
}

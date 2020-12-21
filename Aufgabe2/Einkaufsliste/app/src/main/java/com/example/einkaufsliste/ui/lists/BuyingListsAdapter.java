package com.example.einkaufsliste.ui.lists;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.einkaufsliste.R;
import com.example.einkaufsliste.models.BuyingList;

import java.util.GregorianCalendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BuyingListsAdapter extends RecyclerView.Adapter<BuyingListsAdapter.FirstViewHolder> {

    private final List<BuyingList> buyingLists;

    public BuyingListsAdapter(List<BuyingList> buyingLists) {
        this.buyingLists = buyingLists;
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
        String name = buyingLists.get(position).getText();
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
                        // TODO functionality to navigate to selected buyinglist
                    }
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return buyingLists.size();
    }

    public class FirstViewHolder extends RecyclerView.ViewHolder {
        private View layout;
        private Button button;

        public FirstViewHolder(View view) {
            super(view);
            layout = view;
            button = view.findViewById(R.id.buyinglist_item_button);
        }

        public void add(int position, String name) {
            buyingLists.add(position, new BuyingList(name, new GregorianCalendar().getTime()));

            notifyItemInserted(position);
        }

        public void remove(int position) {
            buyingLists.remove(position);
            notifyItemRemoved(position);
        }

    }
}

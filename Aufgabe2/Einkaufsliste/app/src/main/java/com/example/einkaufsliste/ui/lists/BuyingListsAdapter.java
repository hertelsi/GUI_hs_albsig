package com.example.einkaufsliste.ui.lists;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.einkaufsliste.ChangeFragmentInterface;
import com.example.einkaufsliste.MainActivity;
import com.example.einkaufsliste.R;
import com.example.einkaufsliste.Repository;
import com.example.einkaufsliste.models.BuyingList;

import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.List;

import androidx.annotation.NonNull;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

public class BuyingListsAdapter extends RecyclerView.Adapter<BuyingListsAdapter.BuyingListHolder> {

    private List<BuyingList> buyingLists = new ArrayList<>();
    private ChangeFragmentInterface changeFragmentInterface;
    private ListsViewModel listsViewModel;

    public BuyingListsAdapter(MainActivity mainActivity){
        this.changeFragmentInterface = (ChangeFragmentInterface) mainActivity;
        listsViewModel = new ViewModelProvider(mainActivity).get(ListsViewModel.class);

    }

    @NonNull
    @Override
    public BuyingListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.buyinglist_item, parent, false);
        return new BuyingListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyingListHolder holder, int position) {
        BuyingList currentBuyingList = buyingLists.get(position);
        String name = currentBuyingList.getName();
        int buyingListId = currentBuyingList.getId();
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
                        listsViewModel.removeBuyingList(position);
                        return true;
                    } else {
                        Repository.getInstance().setCurrentBuyingListId(buyingListId);
                        changeFragmentInterface.changeFragment(R.id.nav_singleList);
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

    public void setBuyingLists(Collection<BuyingList> buyingLists){
        ArrayList newBuyingList = new ArrayList();
        for (BuyingList b : buyingLists){
            newBuyingList.add(b);
        }
        this.buyingLists = newBuyingList;
        notifyDataSetChanged();
    }

    public class BuyingListHolder extends RecyclerView.ViewHolder {
        private View layout;
        private Button button;

        public BuyingListHolder(View view) {
            super(view);
            layout = view;
            button = view.findViewById(R.id.buyinglist_item_button);
        }

        public void add(int position, String name) {
            buyingLists.add(position, new BuyingList(name, new Date(System.currentTimeMillis())));
            notifyItemInserted(position);
        }

        public void remove(int position) {
            buyingLists.remove(position);
            notifyDataSetChanged();
        }

    }
}

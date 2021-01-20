package com.example.einkaufsliste.ui.lists;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.einkaufsliste.MainActivity;
import com.example.einkaufsliste.R;
import com.example.einkaufsliste.Repository;
import com.example.einkaufsliste.models.Article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListDataAdapter extends RecyclerView.Adapter<ListDataAdapter.ArticleHolder> {
    private List<Article> articles = new ArrayList<>();
    private ListDataViewModel listDataViewModel;

    // Daten werden von der Activity hineingereicht
    public ListDataAdapter(MainActivity mainActivity) {
        listDataViewModel = new ViewModelProvider(mainActivity).get(ListDataViewModel.class);
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Datendarstellung holen
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.buyinglist_item, parent, false);
        ArticleHolder viewHolder = new ArticleHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {

        String name = articles.get(position).getName();
        long menge = articles.get(position).getAmount();
        String unit = articles.get(position).getUnit();
        String gesamt = menge + " "+ unit +" " + name ;
        holder.button.setText(gesamt);


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
                        listDataViewModel.removeArticle(position);
                        return true;
                    }
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setArticles(Collection<Article> articles){
        ArrayList newArticleList = new ArrayList();
        for (Article a : articles){
            newArticleList.add(a);
        }
        this.articles = newArticleList;
        notifyDataSetChanged();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {
        private View layout;
        private TextView textView;
        private Button button;
        private EditText editText;

        public ArticleHolder(View v) {
            super(v);
            layout = v;
            textView = v.findViewById(R.id.buyinglist_item_button);
            button = v.findViewById(R.id.buyinglist_item_button);
        }

        public void add(int position, String unit, long amount, String name) {
            long id = Repository.getInstance().getCurrentBuyingListId();
            articles.add(position, new Article(unit, amount, name, Repository.getInstance().getUser().getBuyingListById(id)));
            notifyItemInserted(position);
        }

        public void remove(int position) {
            articles.remove(position);
            notifyDataSetChanged();
        }



    }
}

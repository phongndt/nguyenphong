package fpoly.phongndtph56750.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

import fpoly.phongndtph56750.myapplication.databinding.ItemBannerMovieBinding;
import fpoly.phongndtph56750.myapplication.model.Movie;
import fpoly.phongndtph56750.myapplication.util.GlideUtils;

public class BannerMovieAdapter extends RecyclerView.Adapter<BannerMovieAdapter.BannerMovieViewHolder> {

    private final List<Movie> mListMovies;
    public final IClickItemListener iClickItemListener;

    public interface IClickItemListener {
        void onClickItem(Movie movie);
    }

    public BannerMovieAdapter(List<Movie> mListMovies, IClickItemListener iClickItemListener) {
        this.mListMovies = mListMovies;
        this.iClickItemListener = iClickItemListener;
    }

    @NonNull
    @Override
    public BannerMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBannerMovieBinding itemBannerMovieBinding = ItemBannerMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BannerMovieViewHolder(itemBannerMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerMovieViewHolder holder, int position) {
        Movie movie = mListMovies.get(position);
        if (movie == null) {
            return;
        }
        GlideUtils.loadUrlBanner(movie.getImageBanner(), holder.mItemBannerMovieBinding.imgBanner);
        holder.mItemBannerMovieBinding.tvTitle.setText(movie.getName());
        holder.mItemBannerMovieBinding.tvBooked.setText(String.valueOf(movie.getBooked()));

        holder.mItemBannerMovieBinding.layoutItem.setOnClickListener(view -> iClickItemListener.onClickItem(movie));
    }

    @Override
    public int getItemCount() {
        if (mListMovies != null) {
            return mListMovies.size();
        }
        return 0;
    }

    public static class BannerMovieViewHolder extends RecyclerView.ViewHolder {

        private final ItemBannerMovieBinding mItemBannerMovieBinding;

        public BannerMovieViewHolder(@NonNull ItemBannerMovieBinding itemBannerMovieBinding) {
            super(itemBannerMovieBinding.getRoot());
            this.mItemBannerMovieBinding = itemBannerMovieBinding;
        }
    }
}

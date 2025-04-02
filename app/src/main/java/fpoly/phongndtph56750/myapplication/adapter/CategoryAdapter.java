package fpoly.phongndtph56750.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fpoly.phongndtph56750.myapplication.databinding.ItemCategoryBinding;
import fpoly.phongndtph56750.myapplication.model.Category;
import fpoly.phongndtph56750.myapplication.util.GlideUtils;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CatogoryViewHolder> {

    private final List<Category> mListCategory;
    private final IManagerCategoryListener iManagerCategoryListener;

    public interface IManagerCategoryListener {
        void clickItemCategory(Category category);
    }

    public CategoryAdapter(List<Category> mListCategory, IManagerCategoryListener iManagerCategoryListener) {
        this.mListCategory = mListCategory;
        this.iManagerCategoryListener = iManagerCategoryListener;
    }

    @NonNull
    @Override
    public CatogoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding mItemCategoryBinding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CatogoryViewHolder(mItemCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CatogoryViewHolder holder, int position) {
        Category category = mListCategory.get(position);
        if (category == null) {
            return;
        }
        GlideUtils.loadUrl(category.getImage(), holder.mItemCategoryBinding.imgCategory);
        holder.mItemCategoryBinding.tvCategoryName.setText(category.getName());

        holder.mItemCategoryBinding.layoutItem.setOnClickListener(v -> iManagerCategoryListener.clickItemCategory(category));
    }

    @Override
    public int getItemCount() {
        if (mListCategory != null) {
            return mListCategory.size();
        }
        return 0;
    }

    public static class CatogoryViewHolder extends RecyclerView.ViewHolder {

        private final ItemCategoryBinding mItemCategoryBinding;

        public CatogoryViewHolder(@NonNull ItemCategoryBinding itemCategoryBinding) {
            super(itemCategoryBinding.getRoot());
            this.mItemCategoryBinding = itemCategoryBinding;
        }
    }
}

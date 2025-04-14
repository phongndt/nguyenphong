package fpoly.phongndtph56750.myapplication.adapter.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.phongndtph56750.myapplication.R;
import fpoly.phongndtph56750.myapplication.model.DateFirebase;
import fpoly.phongndtph56750.myapplication.model.RoomFirebase;
import fpoly.phongndtph56750.myapplication.model.Voucher;

public class AdminVoucherAdapter extends RecyclerView.Adapter<AdminVoucherAdapter.VoucherViewHolder> {

    private Context context;
    private List<Voucher> voucherList;
    private OnVoucherClickListener listener;

    public interface OnVoucherClickListener {
        void onDeleteVoucher(Voucher voucher);
        void onEditVoucher(Voucher voucher); // ✅ Thêm hàm sửa
    }

    public AdminVoucherAdapter(Context context, List<Voucher> voucherList, OnVoucherClickListener listener) {
        this.context = context;
        this.voucherList = voucherList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_voucher, parent, false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        Voucher voucher = voucherList.get(position);
        holder.tvName.setText(voucher.getNameVoucher());

        String start = "–";
        String end = "–";

        List<DateFirebase> dates = voucher.getDate();
        if (dates != null) {
            if (dates.size() > 0 && dates.get(0).getTitle() != null) {
                start = dates.get(0).getTitle().trim();
            }
            if (dates.size() > 1 && dates.get(1).getTitle() != null) {
                end = dates.get(1).getTitle().trim();
            }
        }

        holder.tvStartDate.setText("Bắt đầu: " + start);
        holder.tvEndDate.setText("Kết thúc: " + end);

        // Gọi xoá
        holder.ivDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteVoucher(voucher);
            }
        });

        // Gọi sửa ✅
        holder.ivEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditVoucher(voucher);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (voucherList != null) ? voucherList.size() : 0;
    }

    public static class VoucherViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvStartDate, tvEndDate;
        ImageView ivDelete, ivEdit; // ✅ Thêm nút sửa

        public VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvStartDate = itemView.findViewById(R.id.tv_start_date);
            tvEndDate = itemView.findViewById(R.id.tv_end_date);
            ivDelete = itemView.findViewById(R.id.img_delete);
            ivEdit = itemView.findViewById(R.id.img_edit); // ✅ đảm bảo trong layout có ImageView này
        }
    }

    public void updateList(List<Voucher> newList) {
        this.voucherList = newList;
        notifyDataSetChanged();
    }
}

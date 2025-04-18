package fpoly.phongndtph56750.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import fpoly.phongndtph56750.myapplication.R;
import fpoly.phongndtph56750.myapplication.model.Voucher;

public class VoucherAdapter extends ArrayAdapter<Voucher> {

    private Context context;
    private List<Voucher> vouchers;
    private DatabaseReference databaseReference;

    public VoucherAdapter(@NonNull Context context, int resource, @NonNull List<Voucher> objects) {
        super(context, resource, objects);
        this.context = context;
        this.vouchers = objects;
        this.databaseReference = FirebaseDatabase.getInstance().getReference("vouchers");

        // Optional: tự động fetch luôn khi adapter được tạo
        fetchVouchersFromFirebase();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        // Sửa lỗi: chỉ inflate nếu convertView null
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_voucher_spinner, parent, false);
        }

        TextView tvVoucher = convertView.findViewById(R.id.tv_voucher_item);
        TextView tvDiscount = convertView.findViewById(R.id.tv_voucher_discount);
        Voucher voucher = vouchers.get(position);

        if (voucher != null) {
            if ("-1".equals(voucher.getId())) {
                tvVoucher.setText("Không sử dụng");
                tvDiscount.setText(""); // Ẩn phần giảm giá cho option mặc định
            } else {
                tvVoucher.setText(voucher.getNameVoucher());
                tvDiscount.setText("-" + voucher.getDiscount() + "đ");
            }
        }
        return convertView;
    }

    // Lấy danh sách voucher từ Firebase
    public void fetchVouchersFromFirebase() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vouchers.clear();
                vouchers.add(new Voucher("-1", "Không sử dụng", null, false, 0)); // Option mặc định

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Voucher voucher = data.getValue(Voucher.class);
                    if (voucher != null && voucher.isActive()) { // Chỉ thêm nếu voucher còn hiệu lực
                        vouchers.add(voucher);
                    }
                }

                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Lỗi khi tải dữ liệu voucher", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

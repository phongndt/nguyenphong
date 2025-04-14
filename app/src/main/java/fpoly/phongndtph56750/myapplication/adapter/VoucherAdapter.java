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
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, parent);
    }

    private View createView(int position, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_voucher_spinner, parent, false);
        TextView tvVoucher = view.findViewById(R.id.tv_voucher_item);
        Voucher voucher = vouchers.get(position);

        if (voucher != null) {
            if (voucher.getId().equals("-1")) {
                tvVoucher.setText("Không sử dụng");
            } else {
                tvVoucher.setText(voucher.getNameVoucher() + " (-" + voucher.getDiscount() + "đ)");
            }
        }
        return view;
    }

    // Phương thức để lấy dữ liệu từ Firebase và cập nhật list vouchers
    public void fetchVouchersFromFirebase() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vouchers.clear();
                vouchers.add(new Voucher("-1", "Không sử dụng", null, false, 0)); // Option mặc định

                // Duyệt qua các vouchers trong Firebase
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Voucher voucher = data.getValue(Voucher.class);
                    if (voucher != null) {
                        vouchers.add(voucher);
                    }
                }

                // Cập nhật lại adapter sau khi lấy dữ liệu
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Lỗi khi tải dữ liệu voucher", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


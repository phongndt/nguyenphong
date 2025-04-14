package fpoly.phongndtph56750.myapplication.activity.admin;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import fpoly.phongndtph56750.myapplication.adapter.admin.AdminVoucherAdapter;
import fpoly.phongndtph56750.myapplication.constant.GlobalFunction;
import fpoly.phongndtph56750.myapplication.databinding.ActivityAdminVoucherAtivityBinding;
import fpoly.phongndtph56750.myapplication.model.Voucher;

public class AdminVoucherActivity extends AppCompatActivity implements AdminVoucherAdapter.OnVoucherClickListener {

    private ActivityAdminVoucherAtivityBinding binding;
    private List<Voucher> voucherList = new ArrayList<>();
    private AdminVoucherAdapter voucherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminVoucherAtivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        voucherAdapter = new AdminVoucherAdapter(this, voucherList, this);

        binding.rcvData.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvData.setAdapter(voucherAdapter);

        loadVoucherFromFirebase();

        binding.btnAddVoucher.setOnClickListener(v -> {
            GlobalFunction.startActivity(this, AddVoucherAdminActivity.class);
        });
    }

    private void loadVoucherFromFirebase() {
        FirebaseDatabase.getInstance().getReference("vouchers")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        voucherList.clear();

                        // Voucher mặc định "Không sử dụng"


                        for (DataSnapshot data : snapshot.getChildren()) {
                            Voucher v = data.getValue(Voucher.class);
                            if (v != null && v.isActive()) {
                                voucherList.add(v);
                            }
                        }

                        voucherAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AdminVoucherActivity.this, "Lỗi tải dữ liệu từ Firebase", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void deleteVoucherItem(Voucher voucher) {
        new AlertDialog.Builder(this)
                .setTitle("Xoá mã giảm giá")
                .setMessage("Bạn có chắc muốn xoá mã \"" + voucher.getNameVoucher() + "\" không?")
                .setPositiveButton("Xoá", (dialogInterface, i) -> {
                    FirebaseDatabase.getInstance().getReference("vouchers")
                            .child(voucher.getId()) // ID đã là String
                            .removeValue()
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Lỗi xoá: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            });
                })
                .setNegativeButton("Huỷ", null)
                .show();
    }

    @Override
    public void onDeleteVoucher(Voucher voucher) {
        deleteVoucherItem(voucher);
    }


    @Override
    public void onEditVoucher(Voucher voucher) {
        // Chuyển sang FixVoucherAdminActivity để sửa
        Bundle bundle = new Bundle();
        bundle.putSerializable("voucher", voucher);
        GlobalFunction.startActivity(this, VoucherAdminActivity.class, bundle);
    }
}

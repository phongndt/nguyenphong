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
    private Spinner voucherSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Khởi tạo View Binding
        binding = ActivityAdminVoucherAtivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Khởi tạo Adapter
        voucherAdapter = new AdminVoucherAdapter(this, voucherList, this);

        // Thiết lập RecyclerView
        binding.rcvData.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvData.setAdapter(voucherAdapter);

        // Tải dữ liệu voucher từ Firebase
        loadVoucherFromFirebase();

        // Xử lý sự kiện khi người dùng nhấn nút Thêm Voucher
        binding.btnAddVoucher.setOnClickListener(v -> {
            // Chuyển đến màn hình thêm voucher mới
            GlobalFunction.startActivity(AdminVoucherActivity.this, AddVoucherAdminActivity.class);
        });
    }

    private void loadVoucherFromFirebase() {
        // Lấy danh sách voucher từ Firebase
        FirebaseDatabase.getInstance().getReference("vouchers")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Xóa hết dữ liệu cũ
                        voucherList.clear();

                        // Thêm voucher mặc định "Không sử dụng"
                        voucherList.add(new Voucher("-1", "Không sử dụng", null, false, 0));

                        // Duyệt qua các voucher trong Firebase và thêm vào danh sách
                        for (DataSnapshot data : snapshot.getChildren()) {
                            Voucher v = data.getValue(Voucher.class);
                            if (v != null && v.isStatus()) { // Kiểm tra voucher đang hoạt động
                                voucherList.add(v);
                            }
                        }

                        // Cập nhật lại adapter
                        voucherAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Xử lý lỗi nếu có
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
                            .child(String.valueOf(voucher.getId()))
                            .removeValue((error, ref) -> {
                                Toast.makeText(this, "Xoá thành công", Toast.LENGTH_SHORT).show();
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
        // Gửi voucher sang FixVoucherAdminActivity để sửa
        Bundle bundle = new Bundle();
        bundle.putSerializable("voucher", voucher);
        GlobalFunction.startActivity(this, VoucherAdminActivity.class, bundle);
    }
}

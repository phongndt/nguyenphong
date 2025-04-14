package fpoly.phongndtph56750.myapplication.activity.admin;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import fpoly.phongndtph56750.myapplication.R;
import fpoly.phongndtph56750.myapplication.model.DateFirebase;
import fpoly.phongndtph56750.myapplication.model.Voucher;

public class AddVoucherAdminActivity extends AppCompatActivity {

    private EditText edtName, edtStartDate, edtEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voucher_admin);

        edtName = findViewById(R.id.edt_name);
        edtStartDate = findViewById(R.id.tv_start_date);
        edtEndDate = findViewById(R.id.tv_end_date);

        edtStartDate.setOnClickListener(v -> showDatePickerDialog(edtStartDate));
        edtEndDate.setOnClickListener(v -> showDatePickerDialog(edtEndDate));

        findViewById(R.id.btn_add_or_edit).setOnClickListener(v -> addVoucher());
    }

    private void showDatePickerDialog(EditText target) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this,
                (DatePicker view, int y, int m, int d) -> {
                    String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", d, m + 1, y);
                    target.setText(selectedDate);
                }, year, month, day);

        dialog.show();
    }

    private void addVoucher() {
        String name = edtName.getText().toString().trim();
        String start = edtStartDate.getText().toString().trim();
        String end = edtEndDate.getText().toString().trim();

        if (name.isEmpty() || start.isEmpty() || end.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo danh sách ngày
        List<DateFirebase> dateList = new ArrayList<>();
        dateList.add(new DateFirebase(start));
        dateList.add(new DateFirebase(end));

        // Tạo voucher mới
        String id = UUID.randomUUID().toString();
        boolean status = true; // cần để hiển thị ở AdminVoucherActivity
        int discount = 0; // Bạn có thể thêm input sau

        Voucher newVoucher = new Voucher(id, name, dateList, status, discount);

        // Ghi vào Firebase
        FirebaseDatabase.getInstance().getReference("vouchers")
                .child(id)
                .setValue(newVoucher)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("AddVoucher", "Thêm thành công");
                        Toast.makeText(this, "Thêm voucher thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Log.e("AddVoucher", "Thêm thất bại", task.getException());
                        Toast.makeText(this, "Lỗi khi thêm voucher", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("AddVoucher", "Firebase lỗi", e);
                    Toast.makeText(this, "Firebase lỗi: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}

package fpoly.phongndtph56750.myapplication.activity.admin;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fpoly.phongndtph56750.myapplication.R;
import fpoly.phongndtph56750.myapplication.model.RoomFirebase;
import fpoly.phongndtph56750.myapplication.model.Voucher;

public class VoucherAdminActivity extends AppCompatActivity {

    private EditText edtName, edtStartDate, edtEndDate;
    private Voucher currentVoucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_voucher_admin);

        edtName = findViewById(R.id.edt_name);
        edtStartDate = findViewById(R.id.tv_start_date);
        edtEndDate = findViewById(R.id.tv_end_date);

        // Nhận dữ liệu từ Intent
        currentVoucher = (Voucher) getIntent().getSerializableExtra("voucher");

        if (currentVoucher != null) {
            edtName.setText(currentVoucher.getNameVoucher());

            List<RoomFirebase> dateList = currentVoucher.getDate();
            if (dateList != null && dateList.size() >= 2) {
                edtStartDate.setText(dateList.get(0).getTitle());
                edtEndDate.setText(dateList.get(1).getTitle());
            }
        }

        // Bắt sự kiện chọn ngày
        edtStartDate.setOnClickListener(v -> showDatePickerDialog(edtStartDate));
        edtEndDate.setOnClickListener(v -> showDatePickerDialog(edtEndDate));

        // Xử lý nút sửa voucher
        findViewById(R.id.btn_add_or_edit).setOnClickListener(v -> updateVoucher());
    }

    private void showDatePickerDialog(EditText target) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    target.setText(selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void updateVoucher() {
        String name = edtName.getText().toString().trim();
        String start = edtStartDate.getText().toString().trim();
        String end = edtEndDate.getText().toString().trim();

        if (name.isEmpty() || start.isEmpty() || end.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        List<RoomFirebase> newDateList = new ArrayList<>();
        newDateList.add(new RoomFirebase(start));
        newDateList.add(new RoomFirebase(end));

        currentVoucher.setNameVoucher(name);
        currentVoucher.setDate(newDateList);

        FirebaseDatabase.getInstance().getReference("vouchers")
                .child(String.valueOf(currentVoucher.getId()))
                .setValue(currentVoucher, (error, ref) -> {
                    if (error == null) {
                        Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        finish(); // Trở về danh sách
                    } else {
                        Toast.makeText(this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

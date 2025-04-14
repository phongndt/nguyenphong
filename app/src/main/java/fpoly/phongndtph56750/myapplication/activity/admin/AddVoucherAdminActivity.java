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
import java.util.UUID;

import fpoly.phongndtph56750.myapplication.R;
import fpoly.phongndtph56750.myapplication.model.DateFirebase;
import fpoly.phongndtph56750.myapplication.model.RoomFirebase;
import fpoly.phongndtph56750.myapplication.model.Voucher;

public class AddVoucherAdminActivity extends AppCompatActivity {

    private EditText edtName, edtStartDate, edtEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voucher_admin); // Sử dụng đúng layout thêm

        edtName = findViewById(R.id.edt_name);
        edtStartDate = findViewById(R.id.tv_start_date);
        edtEndDate = findViewById(R.id.tv_end_date);

        edtStartDate.setOnClickListener(v -> showDatePickerDialog(edtStartDate));
        edtEndDate.setOnClickListener(v -> showDatePickerDialog(edtEndDate));

        findViewById(R.id.btn_add_or_edit).setOnClickListener(v -> addVoucher());
    }

    private void showDatePickerDialog(EditText target) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(
                this,
                (DatePicker view, int year, int month, int dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    target.setText(selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void addVoucher() {
        String name = edtName.getText().toString().trim();
        String start = edtStartDate.getText().toString().trim();
        String end = edtEndDate.getText().toString().trim();

        if (name.isEmpty() || start.isEmpty() || end.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo list ngày bắt đầu và kết thúc sử dụng DateFirebase
        List<DateFirebase> dateList = new ArrayList<>();
        dateList.add(new DateFirebase(start)); // start date
        dateList.add(new DateFirebase(end));   // end date

        // Tạo voucher mới với mức giảm giá là 0 (hoặc giá trị nào đó bạn muốn)
        String id = UUID.randomUUID().toString();
        int discount = 0; // Mức giảm giá mặc định (có thể thay đổi nếu cần)

        Voucher newVoucher = new Voucher(id, name, dateList, false, discount); // Thêm tham số discount vào đây

        // Ghi dữ liệu lên Firebase
        FirebaseDatabase.getInstance().getReference("vouchers")
                .child(id)
                .setValue(newVoucher, (error, ref) -> {
                    if (error == null) {
                        Toast.makeText(this, "Thêm voucher thành công", Toast.LENGTH_SHORT).show();
                        finish(); // Quay lại màn voucher
                    } else {
                        Toast.makeText(this, "Lỗi khi thêm voucher", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}

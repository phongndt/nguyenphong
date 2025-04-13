package fpoly.phongndtph56750.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fpoly.phongndtph56750.myapplication.R;
import fpoly.phongndtph56750.myapplication.model.Voucher;

public class VoucherAdapter extends ArrayAdapter<Voucher> {

    private Context context;
    private List<Voucher> vouchers;

    public VoucherAdapter(@NonNull Context context, int resource, @NonNull List<Voucher> objects) {
        super(context, resource, objects);
        this.context = context;
        this.vouchers = objects;
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
}


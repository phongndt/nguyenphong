package fpoly.phongndtph56750.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fpoly.phongndtph56750.myapplication.R;
import fpoly.phongndtph56750.myapplication.adapter.BookingHistoryAdapter;
import fpoly.phongndtph56750.myapplication.databinding.FragmentBookingBinding;
import fpoly.phongndtph56750.myapplication.model.BookingHistory;

public class BookingFragment extends Fragment {

    private FragmentBookingBinding fragmentBookingBinding;
    private List<BookingHistory> mListBookingHistory;
    private BookingHistoryAdapter mBookingHistoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }
}
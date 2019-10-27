package br.com.mov.views.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import br.com.mov.R;

public class LoginFragment extends Fragment {

    private Dialog popup;
    private Typeface fontSemiBold;
    private Typeface fontRegular;
    private TextView msgWelcome;
    private TextView msg;
    private TextView msg2;
    private TextView toggleMsg;
    private Button positiveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.BLACK);
        }

        fontSemiBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Semibold.ttf");
        fontRegular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Regular.ttf");

        popup = new Dialog(getContext());
        TextView logoMsg = view.findViewById(R.id.fragment_login_logo);
        TextView description = view.findViewById(R.id.fragment_login_msg);
        Button btnLogin = view.findViewById(R.id.fragment_login_btn);
        Button btnCount = view.findViewById(R.id.fragment_login_count_btn);

        logoMsg.setTypeface(fontSemiBold);
        description.setTypeface(fontRegular);

        btnCount.setOnClickListener(v -> showCountPopup(v));

        btnLogin.setOnClickListener(v -> showLoginPopup(v));
        return view;
    }

    //regionPopup Count
    private void showCountPopup(View v) {
        popup.setContentView(R.layout.popup_count);
        initFieldsCountPopup();
        setFonts();
        toggleMsg.setOnClickListener(v1 -> {
            popup.dismiss();
            showLoginPopup(v1);
        });
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();
    }

    private void initFieldsCountPopup() {
        toggleMsg = popup.findViewById(R.id.popup_count_new_login);
        msgWelcome = popup.findViewById(R.id.popup_count_welcome);
        msg = popup.findViewById(R.id.popup_count_msg);
        msg2 = popup.findViewById(R.id.popup_count_msg2);
    }
    //endregion

    //regionPopup Login
    private void showLoginPopup(View v) {
        popup.setContentView(R.layout.popup_login);
        initFieldsLoginPopup();
        setFonts();
        toggleMsg.setOnClickListener(v12 -> {
            popup.dismiss();
            showCountPopup(v12);
        });
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();
    }

    private void initFieldsLoginPopup() {
        positiveButton = popup.findViewById(R.id.popup_login_login_btn);
        toggleMsg = popup.findViewById(R.id.popup_login_new_count);
        msgWelcome = popup.findViewById(R.id.popup_login_welcome);
        msg = popup.findViewById(R.id.popup_login_msg);
        msg2 = popup.findViewById(R.id.popup_login_msg2);
    }
    //endregion

    private void setFonts() {
        msgWelcome.setTypeface(fontSemiBold);
        msg.setTypeface(fontRegular);
        msg2.setTypeface(fontRegular);
    }
}

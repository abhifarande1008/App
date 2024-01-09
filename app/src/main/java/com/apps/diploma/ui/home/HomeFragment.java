package com.apps.diploma.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.apps.diploma.Content;
import com.apps.diploma.R;
import com.apps.diploma.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    Button sem1Btn, sem2Btn, sem3Btn, sem4Btn, sem5Btn, sem6Btn, coBtn, elceBtn, mechBtn, civilBtn;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        FirebaseMessaging.getInstance().subscribeToTopic("notification");

        sem1Btn = view.findViewById(R.id.sem1Btn);
        sem2Btn = view.findViewById(R.id.sem2Btn);
        sem3Btn = view.findViewById(R.id.sem3Btn);
        sem4Btn = view.findViewById(R.id.sem4Btn);
        sem5Btn = view.findViewById(R.id.sem5Btn);
        sem6Btn = view.findViewById(R.id.sem6Btn);

        coBtn = view.findViewById(R.id.coBtn);
        elceBtn = view.findViewById(R.id.elecBtn);
        mechBtn = view.findViewById(R.id.mechBtn);
        civilBtn = view.findViewById(R.id.civilBtn);

        SharedPreferences sharedPreferences_Items = getActivity().getSharedPreferences("last_items", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences_Items.edit();

        boolean is_first_run = sharedPreferences_Items.getBoolean("is_first_run", true);

        if (is_first_run) {

            editor.putBoolean("is_first_run", false);
            editor.commit();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference root = database.getReference("downloads");

            root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int value = snapshot.getValue(Integer.class);
                    root.setValue(value + 1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        String selectedBranch = sharedPreferences_Items.getString("branch","co");
        String selectedSem = sharedPreferences_Items.getString("semester","sem6");

        if (selectedSem.equals("sem1")){
            sem1Btn.setTextColor(getResources().getColor(R.color.white));
            sem1Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }else if (selectedSem.equals("sem2")){
            sem2Btn.setTextColor(getResources().getColor(R.color.white));
            sem2Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }else if (selectedSem.equals("sem3")){
            sem3Btn.setTextColor(getResources().getColor(R.color.white));
            sem3Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }else if (selectedSem.equals("sem4")){
            sem4Btn.setTextColor(getResources().getColor(R.color.white));
            sem4Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }else if (selectedSem.equals("sem5")){
            sem5Btn.setTextColor(getResources().getColor(R.color.white));
            sem5Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }else if (selectedSem.equals("sem6")){
            sem6Btn.setTextColor(getResources().getColor(R.color.white));
            sem6Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }else {
            sem5Btn.setTextColor(getResources().getColor(R.color.white));
            sem5Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }

        if (selectedBranch.equals("co")){
            coBtn.setTextColor(getResources().getColor(R.color.white));
            coBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }else if (selectedBranch.equals("elec")){
            elceBtn.setTextColor(getResources().getColor(R.color.white));
            elceBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }else if (selectedBranch.equals("mech")){
            mechBtn.setTextColor(getResources().getColor(R.color.white));
            mechBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }else if (selectedBranch.equals("civil")){
            civilBtn.setTextColor(getResources().getColor(R.color.white));
            civilBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }else {
            coBtn.setTextColor(getResources().getColor(R.color.white));
            coBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));
        }

        Button btn_books = (Button) view.findViewById(R.id.res_books);
        Button btn_notes = (Button) view.findViewById(R.id.res_notes);
        Button btn_manuals = (Button) view.findViewById(R.id.res_manuals);
        Button btn_solvedManuals = (Button) view.findViewById(R.id.res_solvedManuals);
        Button btn_pyqp = (Button) view.findViewById(R.id.res_pyqp);
        Button btn_other = (Button) view.findViewById(R.id.res_other);

        btn_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Content.class);
                intent.putExtra("res_selected", "books");

                startActivity(intent);
            }
        });

        btn_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Content.class);
                intent.putExtra("res_selected", "notes");

                startActivity(intent);
            }
        });

        btn_manuals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Content.class);
                intent.putExtra("res_selected", "manuals");

                startActivity(intent);
            }
        });

        btn_solvedManuals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Content.class);
                intent.putExtra("res_selected", "solved_manuals");

                startActivity(intent);
            }
        });

        btn_pyqp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Content.class);
                intent.putExtra("res_selected", "pyqp");

                startActivity(intent);
            }
        });

        btn_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Content.class);
                intent.putExtra("res_selected", "other");

                startActivity(intent);
            }
        });

        sem1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem1Btn.setTextColor(getResources().getColor(R.color.white));
                sem1Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));

                editor.putString("semester", "sem1");
                editor.commit();

                sem2Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem2Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem3Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem3Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem4Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem4Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem5Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem5Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem6Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem6Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
            }
        });

        sem2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem2Btn.setTextColor(getResources().getColor(R.color.white));
                sem2Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));

                editor.putString("semester", "sem2");
                editor.commit();

                sem1Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem1Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem3Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem3Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem4Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem4Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem5Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem5Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem6Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem6Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
            }
        });

        sem3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem3Btn.setTextColor(getResources().getColor(R.color.white));
                sem3Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));

                editor.putString("semester", "sem3");
                editor.commit();

                sem1Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem1Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem2Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem2Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem4Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem4Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem5Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem5Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem6Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem6Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
            }
        });

        sem4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem4Btn.setTextColor(getResources().getColor(R.color.white));
                sem4Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));

                editor.putString("semester", "sem4");
                editor.commit();

                sem1Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem1Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem2Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem2Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem3Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem3Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem5Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem5Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem6Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem6Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
            }
        });

        sem5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem5Btn.setTextColor(getResources().getColor(R.color.white));
                sem5Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));

                editor.putString("semester", "sem5");
                editor.commit();

                sem1Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem1Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem2Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem2Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem3Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem3Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem4Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem4Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem6Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem6Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
            }
        });

        sem6Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem6Btn.setTextColor(getResources().getColor(R.color.white));
                sem6Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));

                editor.putString("semester", "sem6");
                editor.commit();

                sem1Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem1Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem2Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem2Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem3Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem3Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem4Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem4Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                sem5Btn.setTextColor(getResources().getColor(R.color.app_default));
                sem5Btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
            }
        });

        coBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coBtn.setTextColor(getResources().getColor(R.color.white));
                coBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));

                editor.putString("branch", "co");
                editor.commit();

                elceBtn.setTextColor(getResources().getColor(R.color.app_default));
                elceBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                mechBtn.setTextColor(getResources().getColor(R.color.app_default));
                mechBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                civilBtn.setTextColor(getResources().getColor(R.color.app_default));
                civilBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
            }
        });

        elceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elceBtn.setTextColor(getResources().getColor(R.color.white));
                elceBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));

                editor.putString("branch", "elec");
                editor.commit();

                coBtn.setTextColor(getResources().getColor(R.color.app_default));
                coBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                mechBtn.setTextColor(getResources().getColor(R.color.app_default));
                mechBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                civilBtn.setTextColor(getResources().getColor(R.color.app_default));
                civilBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
            }
        });

        mechBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mechBtn.setTextColor(getResources().getColor(R.color.white));
                mechBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));

                editor.putString("branch", "mech");
                editor.commit();

                coBtn.setTextColor(getResources().getColor(R.color.app_default));
                coBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                elceBtn.setTextColor(getResources().getColor(R.color.app_default));
                elceBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                civilBtn.setTextColor(getResources().getColor(R.color.app_default));
                civilBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
            }
        });

        civilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                civilBtn.setTextColor(getResources().getColor(R.color.white));
                civilBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.download_btn_bg));

                editor.putString("branch", "civil");
                editor.commit();

                coBtn.setTextColor(getResources().getColor(R.color.app_default));
                coBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                elceBtn.setTextColor(getResources().getColor(R.color.app_default));
                elceBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
                mechBtn.setTextColor(getResources().getColor(R.color.app_default));
                mechBtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stub));
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
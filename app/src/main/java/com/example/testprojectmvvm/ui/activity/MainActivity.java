package com.example.testprojectmvvm.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.testprojectmvvm.R;
import com.example.testprojectmvvm.models.UserEntrys;
import com.example.testprojectmvvm.ui.adapter.RecyclerViewAdapter;
import com.example.testprojectmvvm.viewmodels.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private MainViewModel mainViewModel;
    private RecyclerViewAdapter adapter;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        initRealmConfiguration();
        mainViewModel.init();
        mainViewModel.getListUserEntrys().observe(this, nicePlaces -> adapter.notifyDataSetChanged());
        mainViewModel.getIsUpdating().observe(this, aBoolean -> {
            if(aBoolean){
                showProgressBar();
            }
            else{
                hideProgressBar();
                recyclerView.smoothScrollToPosition(mainViewModel.getListUserEntrys().getValue().size()-1);
            }
        });

        initRecyclerView();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void initRealmConfiguration() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("userEntrysMVVM.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewAdapter(this, mainViewModel.getListUserEntrys().getValue());
      //  RecyclerView.LayoutManager linearLayoutManager =;
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.button)
    void onButtonClick(View v){
        String text = String.valueOf(editText.getText());
        if (text.equals("")){
            errorInputText();
        }
        else{
            mainViewModel.addNewEntry(new UserEntrys(text));
            editText.setText("");
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }

    private void errorInputText() {
        Toast.makeText(this, "Ошибка ввода данных", Toast.LENGTH_SHORT).show();
    }


}

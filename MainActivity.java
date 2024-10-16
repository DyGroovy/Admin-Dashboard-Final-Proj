package com.example.admin_finalproj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> users;
    private RelativeLayout userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();
        userList = findViewById(R.id.userList);
        ImageButton addUserButton = findViewById(R.id.addUserButton);

        addUserButton.setOnClickListener(v -> addUser());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        updateUI();
    }

    private void addUser() {
        users.add("User " + (users.size() + 1));
        updateUI();
    }

    private void deleteUser(int index) {
        if (index >= 0 && index < users.size()) {
            users.remove(index);
            updateUI();
        }
    }

    private void updateUI() {
        userList.removeAllViews();
        for (int i = 0; i < users.size(); i++) {
            String user = users.get(i);

            TextView userName = new TextView(this);
            userName.setText(user);
            RelativeLayout.LayoutParams userNameParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            userNameParams.topMargin = i * 50;
            userName.setLayoutParams(userNameParams);
            userList.addView(userName);

            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");
            deleteButton.setTextSize(10);
            deleteButton.setBackgroundTintList(getResources().getColorStateList(R.color.black, null));
            RelativeLayout.LayoutParams deleteButtonParams = new RelativeLayout.LayoutParams(
                    200, RelativeLayout.LayoutParams.WRAP_CONTENT);
            deleteButtonParams.topMargin = i * 50;
            deleteButtonParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            deleteButton.setLayoutParams(deleteButtonParams);
            int finalI = i;
            deleteButton.setOnClickListener(v -> deleteUser(finalI));
            userList.addView(deleteButton);
        }
    }
}

package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String typeAdmin = getIntent().getExtras().getString("TypeAdmin","defaultKey");
        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHost.getNavController();
        NavInflater navInflater = navController.getNavInflater();
        NavGraph graph;
        Bundle bundle;
        switch (typeAdmin){
            case "REPORT":
                graph = navInflater.inflate(R.navigation.nav_report);
                navController.setGraph(graph);
                break;
            case "TABLE":
                graph = navInflater.inflate(R.navigation.nav_table);
                navController.setGraph(graph);
                break;
            case "USER":
                graph = navInflater.inflate(R.navigation.nav_user);
                navController.setGraph(graph);
                break;
            case "STORE":
                graph = navInflater.inflate(R.navigation.nav_store);
                navController.setGraph(graph);
                break;
            case "PAY":
                graph = navInflater.inflate(R.navigation.nav_pay);
                bundle = new Bundle();
                bundle.putSerializable("table", null);
                bundle.putString("typePay", "PAY");
                navController.setGraph(graph, bundle);
                break;
            case "SHELL":
                graph = navInflater.inflate(R.navigation.nav_shell);
                bundle = new Bundle();
                bundle.putString("typePay", "SHELL");
                navController.setGraph(graph, bundle);
                break;
        }
    }
}
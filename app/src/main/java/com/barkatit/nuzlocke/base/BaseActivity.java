package com.barkatit.nuzlocke.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

import com.barkatit.nuzlocke.R;
import com.barkatit.nuzlocke.ui.box.BoxActivity;
import com.barkatit.nuzlocke.ui.party.PartyActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<VIEW_MODEL extends BaseViewModel, VIEW_BINDING extends ViewDataBinding> extends DaggerAppCompatActivity {

    @Inject
    BaseViewModelFactory viewModelFactory;

    protected VIEW_BINDING binding;
    protected VIEW_MODEL viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        parseArguments(getIntent().getExtras());
        setup(getLayoutId(), getViewModelClass());
        setUpActionBar();
        setupView();
    }

    protected abstract void parseArguments(Bundle bundle);

    private void setup(int layoutId, Class<VIEW_MODEL> viewModelClass) {
        binding = DataBindingUtil.setContentView(this, layoutId);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass);
    }

    public abstract void setupView();

    public abstract Class<VIEW_MODEL> getViewModelClass();

    public abstract int getLayoutId();

    public abstract String getActionBarText();

    public void setUpActionBar() {
        View actionBar = binding.getRoot().findViewById(R.id.action_bar);
        if (actionBar instanceof MaterialToolbar) {
            setSupportActionBar((Toolbar) actionBar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(getActionBarText());
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            View drawerLayout = binding.getRoot().findViewById(R.id.drawer_layout);
            if (drawerLayout instanceof DrawerLayout) {
                ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                        this,
                        (DrawerLayout) drawerLayout,
                        (MaterialToolbar) actionBar,
                        R.string.drawer_accessibility_drawer_open,
                        R.string.drawer_accessibility_drawer_close);
                ((DrawerLayout) drawerLayout).addDrawerListener(drawerToggle);
                drawerToggle.syncState();
            }

            final View navigationView = binding.getRoot().findViewById(R.id.navigation_view);
            if (navigationView instanceof NavigationView) {
                ((NavigationView) navigationView).setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.party_nav_item:
                                if (!(BaseActivity.this instanceof PartyActivity)) {
                                    Intent partyIntent = new Intent(BaseActivity.this, PartyActivity.class);
                                    partyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(partyIntent);
                                }
                                break;
                            case R.id.box_nav_item:
                                if (!(BaseActivity.this instanceof BoxActivity)) {
                                    Intent boxIntent = new Intent(BaseActivity.this, BoxActivity.class);
                                    boxIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(boxIntent);
                                }
                                break;
                        }
                        return false;
                    }
                });
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}

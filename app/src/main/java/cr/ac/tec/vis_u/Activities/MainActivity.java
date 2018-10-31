package cr.ac.tec.vis_u.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import cr.ac.tec.vis_u.Adapters.ImageAdapterCareerMenu;
import cr.ac.tec.vis_u.Decorators.SpaceItemDecoration;
import cr.ac.tec.vis_u.Models.Career;
import cr.ac.tec.vis_u.Parcelables.CareerParcelable;
import cr.ac.tec.vis_u.R;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
  private OkHttpClient client;
  private Toolbar mainToolbar;
  private ArrayList<Career> careerDataList;
  private ArrayList<CareerParcelable> careerParcelables;
  private Bundle bundle;
  private RecyclerView rView;
  private GridLayoutManager layoutManager;
  private SpaceItemDecoration decoration = new SpaceItemDecoration(25);
  private ImageAdapterCareerMenu adapter;
  
  
  //create view, initialize objects;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mainToolbar = (Toolbar) findViewById(R.id.toolbar);
    
    bundle = getIntent().getExtras();
    rView = (RecyclerView) findViewById(R.id.recycler_view);
    rView.setHasFixedSize(true);
    rView.addItemDecoration(decoration);
    layoutManager = new GridLayoutManager(getApplicationContext(), 2);
    
    setSupportActionBar(mainToolbar);
    
    
    //set up recycler view with request made in logo splash activity
    int i;
    CareerParcelable iterator;
    Career career;
    careerParcelables = bundle.getParcelableArrayList("CAREER_LIST");
    careerDataList = new ArrayList<>();
    
    for (i = 0; i < careerParcelables.size(); i++) {
      iterator = careerParcelables.get(i);
      career = new Career(iterator.getIdCareer(), iterator.getName(), iterator.getImage(),
          iterator.getSchool());
      careerDataList.add(career);
    }
    
    adapter = new ImageAdapterCareerMenu(getApplicationContext(), careerDataList);
    rView.setLayoutManager(layoutManager);
    rView.setAdapter(adapter);
    rView.setVisibility(View.VISIBLE);
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu items for use in the action bar
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_about, menu);
    return super.onCreateOptionsMenu(menu);
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    
    switch (id) {
      case R.id.menu_about:
        Intent intent = new Intent(MainActivity.this, SectionAbout.class);
        startActivity(intent);
        ;
        
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
  
  
}
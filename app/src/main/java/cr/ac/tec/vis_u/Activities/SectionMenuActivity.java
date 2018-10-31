package cr.ac.tec.vis_u.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

import java.util.ArrayList;

import cr.ac.tec.vis_u.Adapters.ImageAdapterSectionMenu;
import cr.ac.tec.vis_u.Decorators.SpaceItemDecoration;
import cr.ac.tec.vis_u.Models.Section;
import cr.ac.tec.vis_u.Parcelables.SectionParcelable;
import cr.ac.tec.vis_u.R;

public class SectionMenuActivity extends AppCompatActivity {
  
  private TextView careerName;
  private TextView schoolName;
  private String careerNameText;
  private String careerSchool;
  private long careerId;
  private ArrayList<SectionParcelable> sectionParcelables = new ArrayList<>();
  private RecyclerView rView;
  private LinearLayoutManager layoutManager;
  private SpaceItemDecoration decoration = new SpaceItemDecoration(25);
  private ImageAdapterSectionMenu adapter;
  private ProgressBar progressBar;
  private Bundle bundle;
  private Toolbar mainToolbar;
  
  //Create view and initialize variables
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_section_menu);
    mainToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(mainToolbar);
    SectionParcelable iterator;
    Section section;
    rView = (RecyclerView) findViewById(R.id.recycler_view);
    rView.setHasFixedSize(true);
    rView.addItemDecoration(decoration);
    layoutManager = new LinearLayoutManager(SectionMenuActivity.this);
    
    bundle = getIntent().getExtras();
    careerNameText = bundle.getString("CAREER_NAME");
    careerSchool = bundle.getString("CAREER_SCHOOL");
    careerName = (TextView) findViewById(R.id.txt_title);
    schoolName = (TextView) findViewById(R.id.secondary_txt_title);
    careerId = bundle.getLong("CAREER_ID");
    sectionParcelables = bundle.getParcelableArrayList("SECTION_LIST");
    
    careerName.setText(careerNameText);
    schoolName.setText(careerSchool);
    
    //extract data from request in career image holder
    
    rView.setLayoutManager(layoutManager);
    rView.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(1f)));
    rView.getItemAnimator().setAddDuration(500);
    rView.getItemAnimator().setRemoveDuration(500);
    adapter = new ImageAdapterSectionMenu(getApplicationContext(),
        careerNameText);
    rView.setAdapter(adapter);
    
    for (int i = 0; i < sectionParcelables.size(); i++) {
      iterator = sectionParcelables.get(i);
      section = new Section(iterator.getIdSection(), iterator.getName(), iterator.getImage(),
          iterator.getDescription(), iterator.getTitleColor());
      adapter.add(section, i);
    }
  }
  
  
}

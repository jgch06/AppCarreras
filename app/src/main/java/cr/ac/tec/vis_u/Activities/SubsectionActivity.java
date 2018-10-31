package cr.ac.tec.vis_u.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import cr.ac.tec.vis_u.Adapters.SubsectionAdapter;
import cr.ac.tec.vis_u.Decorators.SpaceItemDecoration;
import cr.ac.tec.vis_u.Models.Subsection;
import cr.ac.tec.vis_u.Parcelables.SubsectionParcelable;
import cr.ac.tec.vis_u.R;

public class SubsectionActivity extends AppCompatActivity {
  
  private Toolbar mainToolbar;
  private Bundle bundle;
  private ArrayList<Subsection> subsectionDataList;
  private ArrayList<SubsectionParcelable> subsectionParcelables;
  private RecyclerView rView;
  private LinearLayoutManager layoutManager;
  private SpaceItemDecoration decoration = new SpaceItemDecoration(25);
  private SubsectionAdapter adapter;
  private String sectionName;
  private TextView toolbarTitle;
  private TextView toolbarSecondaryTitle;
  private TextView noContentTextViewSubsection;
  
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    int i;
    setContentView(R.layout.activity_subsection);
    
    mainToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(mainToolbar);
    toolbarTitle = (TextView) findViewById(R.id.txt_title);
    toolbarSecondaryTitle = (TextView) findViewById(R.id.secondary_txt_title);
    noContentTextViewSubsection = (TextView) findViewById(R.id.textViewNoContentSubsection);
    
    bundle = getIntent().getExtras();
    SubsectionParcelable iterator;
    Subsection subsection;
    rView = (RecyclerView) findViewById(R.id.subsection_recycler);
    rView.setHasFixedSize(true);
    rView.addItemDecoration(decoration);
    layoutManager = new LinearLayoutManager(SubsectionActivity.this);
    
    bundle = getIntent().getExtras();
    sectionName = bundle.getString("SECTION_NAME");
    toolbarTitle.setText(sectionName);
    
    subsectionParcelables = bundle.getParcelableArrayList("SECTION_SUBSECTIONS");
    subsectionDataList = new ArrayList<>();
    
    if (!subsectionParcelables.isEmpty()) {
      for (i = 0; i < subsectionParcelables.size(); i++) {
        iterator = subsectionParcelables.get(i);
        String replaced = iterator.getDescription().replace("\\n", "\n");
        subsection = new Subsection(iterator.getName(), replaced);
        subsectionDataList.add(subsection);
      }
      
      adapter = new SubsectionAdapter(getApplicationContext(), subsectionDataList);
      rView.setLayoutManager(layoutManager);
      rView.setAdapter(adapter);
    } else {
      noContentTextViewSubsection.setVisibility(View.VISIBLE);
      rView.setVisibility(View.GONE);
    }
  }
}

package cr.ac.tec.vis_u.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;

import java.util.ArrayList;

import cr.ac.tec.vis_u.Adapters.SectionImageAdapter;
import cr.ac.tec.vis_u.Models.SectionImage;
import cr.ac.tec.vis_u.Parcelables.SectionImageParcelable;
import cr.ac.tec.vis_u.R;


public class SectionImageActivity extends AppCompatActivity {
  
  private Toolbar mainToolbar;
  private ArrayList<SectionImage> sectionImageDataList;
  private ArrayList<SectionImageParcelable> sectionImageParcelables;
  private Bundle bundle;
  private RecyclerView rView;
  private CarouselLayoutManager layoutManager;
  private SectionImageAdapter adapter;
  private String sectionName;
  private TextView toolbarTitle;
  private TextView toolbarSecondaryTitle;
  private TextView noContentText;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_section_image);
    mainToolbar = (Toolbar) findViewById(R.id.toolbar);
    
    setSupportActionBar(mainToolbar);
    
    toolbarTitle = (TextView) findViewById(R.id.txt_title);
    toolbarSecondaryTitle = (TextView) findViewById(R.id.secondary_txt_title);
    toolbarSecondaryTitle.setText(getString(R.string.images));
    bundle = getIntent().getExtras();
    sectionName = bundle.getString("SECTION_NAME");
    toolbarTitle.setText(sectionName);
    rView = (RecyclerView) findViewById(R.id.section_image_recycler);
    noContentText = (TextView) findViewById(R.id.textViewNoContentImage);
    layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
    layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
    
    
    //set up recycler view with request made in previous activity
    int i;
    SectionImageParcelable iterator;
    SectionImage sectionImage;
    sectionImageParcelables = bundle.getParcelableArrayList("SECTION_IMAGES");
    sectionImageDataList = new ArrayList<>();
    
    if (!sectionImageParcelables.isEmpty()) {
      for (i = 0; i < sectionImageParcelables.size(); i++) {
        iterator = sectionImageParcelables.get(i);
        sectionImage = new SectionImage(iterator.getImage_file_path());
        sectionImageDataList.add(sectionImage);
      }
      
      adapter = new SectionImageAdapter(getApplicationContext(), sectionImageDataList);
      rView.setLayoutManager(layoutManager);
      rView.setAdapter(adapter);
      rView.setHasFixedSize(true);
    } else {
      rView.setVisibility(View.GONE);
      noContentText.setVisibility(View.VISIBLE);
    }
  }
}

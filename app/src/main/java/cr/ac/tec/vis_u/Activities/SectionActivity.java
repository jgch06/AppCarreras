package cr.ac.tec.vis_u.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cr.ac.tec.vis_u.Adapters.OptionAdapterSectionMenu;
import cr.ac.tec.vis_u.Decorators.SpaceItemDecoration;
import cr.ac.tec.vis_u.Models.Option;
import cr.ac.tec.vis_u.R;

public class SectionActivity extends AppCompatActivity {
  
  private Toolbar mainToolbar;
  private Bundle bundle;
  private long id;
  private String sectionNameText;
  private String careerNameText;
  private String sectionDescriptionText;
  private String imagePath;
  private String titleColor;
  private RecyclerView rView;
  private OptionAdapterSectionMenu adapter;
  private LinearLayoutManager layoutManager;
  private SpaceItemDecoration decoration = new SpaceItemDecoration(25);
  private ImageView sectionImage;
  private TextView careerName;
  private TextView sectionName;
  private TextView sectionDescription;
  private ArrayList<Option> optionList = new ArrayList<>();
  private Option optionImages;
  private Option optionVideos;
  private Option optionSubsections;
  
  
  //initialize view and variables
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_section);
    mainToolbar = (Toolbar) findViewById(R.id.toolbar);
    
    setSupportActionBar(mainToolbar);
    
    rView = (RecyclerView) findViewById(R.id.options_recycler_view);
    layoutManager = new LinearLayoutManager(SectionActivity.this,
        LinearLayoutManager.HORIZONTAL, false);
    rView.setLayoutManager(layoutManager);
    
    bundle = getIntent().getExtras();
    id = bundle.getLong("SECTION_ID");
    sectionNameText = bundle.getString("SECTION_NAME");
    careerNameText = bundle.getString("CAREER_NAME");
    sectionDescriptionText = bundle.getString("SECTION_DESCRIPTION");
    imagePath = bundle.getString("SECTION_IMAGE");
    titleColor = bundle.getString("SECTION_COLOR");
    sectionImage = (ImageView) findViewById(R.id.section_image);
    careerName = (TextView) findViewById(R.id.txt_title);
    sectionName = (TextView) findViewById(R.id.section_title);
    sectionDescription = (TextView) findViewById(R.id.txt_section_description);
    
    //set toolbar info and section titles
    careerName.setText(careerNameText);
    sectionName.setText(sectionNameText);
    sectionName.setTextColor(Color.parseColor(titleColor));
    sectionDescription.setText(sectionDescriptionText);
    Glide.with(this).load(imagePath)
        .error(R.mipmap.placeholder1)
        .placeholder(R.drawable.circular_progress_bar)
        .animate(android.R.anim.fade_in)
        .into(sectionImage);
    optionImages = new Option(1, id, "Imágenes", sectionNameText);
    optionVideos = new Option(2, id, "Videos", sectionNameText);
    optionSubsections = new Option(3, id, "Más Información", sectionNameText);
    
    //add data to recycler view
    optionList.add(optionImages);
    optionList.add(optionVideos);
    optionList.add(optionSubsections);
    
    
    adapter = new OptionAdapterSectionMenu(SectionActivity.this, id, sectionNameText, optionList);
    rView.setAdapter(adapter);
    
  }
}

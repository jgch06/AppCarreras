package cr.ac.tec.vis_u.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import cr.ac.tec.vis_u.R;

public class SectionAbout extends AppCompatActivity {
  
  private Toolbar mainToolbar;
  private TextView titulo1;
  private TextView titulo2;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_section_about);
    mainToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(mainToolbar);
    
    titulo1 = (TextView) findViewById(R.id.txt_title);
    titulo2 = (TextView) findViewById(R.id.secondary_txt_title);
    
    titulo1.setText(getString(R.string.about));
    titulo2.setText(getString(R.string.about_subtitle));
  }
}



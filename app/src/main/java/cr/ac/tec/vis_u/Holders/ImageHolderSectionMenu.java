package cr.ac.tec.vis_u.Holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cr.ac.tec.vis_u.Activities.SectionActivity;
import cr.ac.tec.vis_u.R;

/**
 * Created by elang on 9/11/2016.
 */
public class ImageHolderSectionMenu extends RecyclerView.ViewHolder implements View.OnClickListener {
  private static final String SECTION_ID = "SECTION_ID";
  private static final String SECTION_NAME = "SECTION_NAME";
  private static final String SECTION_DESCRIPTION = "SECTION_DESCRIPTION";
  private static final String SECTION_IMAGE = "SECTION_IMAGE";
  private static final String SECTION_COLOR = "SECTION_COLOR";
  private static final String CAREER_NAME = "CAREER_NAME";
  public TextView name;
  public long id;
  public long careerId;
  public ImageView image;
  public String imagePath;
  public String careerName;
  public String description;
  public String titleColor;
  
  public ImageHolderSectionMenu(View itemView) {
    super(itemView);
    itemView.setOnClickListener(this);
    name = (TextView) itemView.findViewById(R.id.section_name);
    image = (ImageView) itemView.findViewById(R.id.section_image);
  }
  
  @Override
  public void onClick(View view) {
    final Intent intent = new Intent(view.getContext(), SectionActivity.class);
    
    intent.putExtra(SECTION_ID, id);
    intent.putExtra(SECTION_NAME, name.getText());
    intent.putExtra(SECTION_DESCRIPTION, description);
    intent.putExtra(SECTION_IMAGE, imagePath);
    intent.putExtra(CAREER_NAME, careerName);
    intent.putExtra(SECTION_COLOR, titleColor);
    
    Context context = view.getContext();
    context.startActivity(intent);
  }
}
package cr.ac.tec.vis_u.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import cr.ac.tec.vis_u.R;

/**
 * Created by elang on 9/26/2016.
 */
public class SectionImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  public ImageView image;
  public boolean mExpanded;
  
  public SectionImageHolder(View itemView) {
    super(itemView);
    itemView.setOnClickListener(this);
    image = (ImageView) itemView.findViewById(R.id.section_carousel_image);
  }
  
  @Override
  public void onClick(View view) {
    
  }
}

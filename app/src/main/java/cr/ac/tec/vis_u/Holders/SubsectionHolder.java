package cr.ac.tec.vis_u.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cr.ac.tec.vis_u.R;

/**
 * Created by elang on 10/5/2016.
 */
public class SubsectionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  public TextView subsectionTitle;
  public TextView subsectionContent;
  
  public SubsectionHolder(View itemView) {
    super(itemView);
    subsectionTitle = (TextView) itemView.findViewById(R.id.subsection_title);
    subsectionContent = (TextView) itemView.findViewById(R.id.subsection_content);
  }
  
  @Override
  public void onClick(View view) {
    
  }
}

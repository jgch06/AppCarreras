package cr.ac.tec.vis_u.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cr.ac.tec.vis_u.Holders.SubsectionHolder;
import cr.ac.tec.vis_u.Models.Subsection;
import cr.ac.tec.vis_u.R;

/**
 * Created by elang on 10/5/2016.
 */
public class SubsectionAdapter extends RecyclerView.Adapter<SubsectionHolder> {
  private Context context;
  private ArrayList<Subsection> subsections;
  
  public SubsectionAdapter(Context context, ArrayList<Subsection> subsections) {
    this.context = context;
    this.subsections = subsections;
  }
  
  @Override
  public SubsectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View layoutView = LayoutInflater.from(parent.getContext()).inflate(
        R.layout.card_view_subsection_list, null);
    SubsectionHolder subsectionHolder = new SubsectionHolder(layoutView);
    return subsectionHolder;
  }
  
  @Override
  public void onBindViewHolder(SubsectionHolder holder, int position) {
    String name = subsections.get(position).getName();
    String description = subsections.get(position).getDescription();
    
    holder.subsectionTitle.setText(name);
    holder.subsectionContent.setText(description);
  }
  
  @Override
  public int getItemCount() {
    return subsections.size();
  }
}

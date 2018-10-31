package cr.ac.tec.vis_u.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cr.ac.tec.vis_u.Holders.OptionHolderSectionMenu;
import cr.ac.tec.vis_u.Models.Option;
import cr.ac.tec.vis_u.R;

/**
 * Created by elang on 9/25/2016.
 */
public class OptionAdapterSectionMenu extends RecyclerView.Adapter<OptionHolderSectionMenu> {
  private Context context;
  private long sectionId;
  private String sectionName;
  private ArrayList<Option> options;
  
  public OptionAdapterSectionMenu(Context context, long sectionId,
                                  String sectionName, ArrayList<Option> options) {
    this.context = context;
    this.sectionId = sectionId;
    this.sectionName = sectionName;
    this.options = options;
  }
  
  @Override
  public OptionHolderSectionMenu onCreateViewHolder(ViewGroup parent, int viewType) {
    View layoutView = LayoutInflater.from(parent.getContext()).inflate(
        R.layout.card_view_option_list, null);
    OptionHolderSectionMenu optionHolder = new OptionHolderSectionMenu(layoutView);
    return optionHolder;
  }
  
  @Override
  public void onBindViewHolder(OptionHolderSectionMenu holder, int position) {
    long optionId = options.get(position).getOptionId();
    long idSection = options.get(position).getIdSection();
    String sectionName = options.get(position).getSectionName();
    String optionName = options.get(position).getOptionName();
    
    holder.optionId = optionId;
    holder.idSection = idSection;
    holder.optionName.setText(optionName);
    holder.sectionName = sectionName;
  }
  
  @Override
  public int getItemCount() {
    return options.size();
  }
}

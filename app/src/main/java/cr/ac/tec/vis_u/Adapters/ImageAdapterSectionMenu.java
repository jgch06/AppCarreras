package cr.ac.tec.vis_u.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cr.ac.tec.vis_u.Holders.ImageHolderSectionMenu;
import cr.ac.tec.vis_u.Models.Section;
import cr.ac.tec.vis_u.R;

/**
 * Created by elang on 9/11/2016.
 */
public class ImageAdapterSectionMenu extends RecyclerView.Adapter<ImageHolderSectionMenu> {
  
  private Context context;
  private String careerName;
  private long careerId;
  private ArrayList<Section> sectionDataList;
  private LayoutInflater mInflater;
  
  public ImageAdapterSectionMenu(Context context, String careerName) {
    mInflater = LayoutInflater.from(context);
    this.context = context;
    this.sectionDataList = new ArrayList<Section>();
    this.careerName = careerName;
  }
  
  @Override
  public ImageHolderSectionMenu onCreateViewHolder(ViewGroup parent, int viewType) {
    View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_section_list,
        null);
    ImageHolderSectionMenu imageHolder = new ImageHolderSectionMenu(layoutView);
    return imageHolder;
  }
  
  @Override
  public void onBindViewHolder(ImageHolderSectionMenu holder, int position) {
    long id = sectionDataList.get(position).getIdSection();
    String name = sectionDataList.get(position).getName();
    String description = sectionDataList.get(position).getDescription();
    String image = sectionDataList.get(position).getImage();
    String titleColor = sectionDataList.get(position).getTitleColor();
    
    holder.name.setText(name);
    holder.name.setTextColor(Color.parseColor(titleColor));
    Glide.with(context).load(image)
        .error(R.mipmap.placeholder1)
        .placeholder(R.mipmap.placeholder1)
        .animate(android.R.anim.fade_in)
        .into(holder.image);
    holder.id = id;
    holder.description = description;
    holder.careerName = this.careerName;
    holder.careerId = id;
    holder.imagePath = image;
    holder.titleColor = titleColor;
  }
  
  @Override
  public int getItemCount() {
    return sectionDataList.size();
  }
  
  public void add(Section section, int position) {
    sectionDataList.add(position, section);
    notifyItemInserted(position);
  }
}

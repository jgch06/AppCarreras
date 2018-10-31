package cr.ac.tec.vis_u.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import cr.ac.tec.vis_u.Holders.ImageHolderCareerMenu;
import cr.ac.tec.vis_u.Models.Career;
import cr.ac.tec.vis_u.R;

public class ImageAdapterCareerMenu extends RecyclerView.Adapter<ImageHolderCareerMenu> {
  
  LayoutInflater mInflater;
  private Context context;
  private List<Career> careerDataList;
  
  public ImageAdapterCareerMenu(Context context, ArrayList<Career> careerDataList) {
    this.mInflater = LayoutInflater.from(context);
    this.context = context;
    this.careerDataList = careerDataList;
  }
  
  @Override
  public ImageHolderCareerMenu onCreateViewHolder(ViewGroup parent, int viewType) {
    View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_career_list,
        null);
    return new ImageHolderCareerMenu(layoutView);
  }
  
  @Override
  public void onBindViewHolder(ImageHolderCareerMenu holder, int position) {
    
    long id = careerDataList.get(position).getIdCareer();
    String name = careerDataList.get(position).getName();
    String image = careerDataList.get(position).getImage();
    String school = careerDataList.get(position).getSchool();
    
    Glide.with(context).load(image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .dontAnimate()
        .fitCenter()
        .animate(android.R.anim.fade_in)
        .into(holder.image);
    holder.name.setText(name);
    holder.id = id;
    holder.school = school;
    holder.careerName = name;
    holder.idCareer = id;
  }
  
  @Override
  public int getItemCount() {
    return careerDataList.size();
  }
  
}

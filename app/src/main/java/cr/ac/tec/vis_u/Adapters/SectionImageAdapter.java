package cr.ac.tec.vis_u.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cr.ac.tec.vis_u.Holders.SectionImageHolder;
import cr.ac.tec.vis_u.Models.SectionImage;
import cr.ac.tec.vis_u.R;

/**
 * Created by elang on 9/26/2016.
 */
public class SectionImageAdapter extends RecyclerView.Adapter<SectionImageHolder> {
  private Context context;
  private ArrayList<SectionImage> sectionImages;
  
  public SectionImageAdapter(Context context, ArrayList<SectionImage> sectionImages) {
    this.context = context;
    this.sectionImages = sectionImages;
  }
  
  @Override
  public SectionImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View layoutView = LayoutInflater.from(parent.getContext()).inflate(
        R.layout.card_view_section_image_carousel, null);
    SectionImageHolder imageHolder = new SectionImageHolder(layoutView);
    return imageHolder;
  }
  
  @Override
  public void onBindViewHolder(SectionImageHolder holder, int position) {
    String image_file_path = sectionImages.get(position).getImage_file_path();
    
    Glide.with(context).load(image_file_path)
        .placeholder(R.mipmap.placeholder1)
        .error(R.mipmap.placeholder1)
        .animate(android.R.anim.fade_in)
        .into(holder.image);
  }
  
  @Override
  public int getItemCount() {
    return sectionImages.size();
  }
}
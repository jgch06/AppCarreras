package cr.ac.tec.vis_u.Decorators;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by elang on 9/11/2016.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
  private final int space;
  
  public SpaceItemDecoration(int space) {
    this.space = space;
  }
  
  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    outRect.left = space;
    outRect.right = space;
    outRect.bottom = space;
    outRect.top = space;
  }
}
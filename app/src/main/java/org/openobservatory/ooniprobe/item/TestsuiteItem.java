package org.openobservatory.ooniprobe.item;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.openobservatory.ooniprobe.R;
import org.openobservatory.ooniprobe.common.PreferenceManager;
import org.openobservatory.ooniprobe.test.suite.AbstractSuite;

import androidx.appcompat.widget.ContentFrameLayout;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import localhost.toolkit.widget.recyclerview.HeterogeneousRecyclerItem;

public class TestsuiteItem extends HeterogeneousRecyclerItem<AbstractSuite, TestsuiteItem.ViewHolderImpl> {
	private final View.OnClickListener onClickListener;
	private final PreferenceManager pm;
	private Context context;
	public TestsuiteItem(AbstractSuite extra, PreferenceManager pm, Context context, View.OnClickListener onClickListener) {
		super(extra);
		this.pm = pm;
		this.onClickListener = onClickListener;
		this.context = context;
	}

	@Override public ViewHolderImpl onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
		return new ViewHolderImpl(layoutInflater.inflate(R.layout.item_testsuite, viewGroup, false));
	}

	@Override public void onBindViewHolder(ViewHolderImpl holder) {
		holder.title.setText(extra.getTitle());
		holder.desc.setText(extra.getCardDesc());
		holder.icon.setImageResource(extra.getIcon());
		applyGradient(holder.icon);
//		int color = ContextCompat.getColor(holder.card.getContext(), extra.getColor());
//		holder.card.setCardBackgroundColor(color);
//		holder.run.setTextColor(color);
//		holder.run.setOnClickListener(onClickListener);
		holder.itemView.setOnClickListener(onClickListener);
//		holder.run.setTag(extra);
		holder.itemView.setTag(extra);
//		holder.runtime.setText(holder.runtime.getContext().getString(R.string.Dashboard_Card_Seconds, extra.getRuntime(pm).toString()));
	}

	class ViewHolderImpl extends RecyclerView.ViewHolder {
		@BindView(R.id.title) TextView title;
		@BindView(R.id.desc) TextView desc;
		@BindView(R.id.icon) ImageView icon;
//		@BindView(R.id.card) CardView card;
//		@BindView(R.id.run) Button run;
//		@BindView(R.id.runtime) TextView runtime;

		ViewHolderImpl(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	/*
	Used this hack
	https://stackoverflow.com/questions/37775675/imageview-set-color-filter-to-gradient
	https://stackoverflow.com/questions/36513854/how-to-get-a-bitmap-from-vectordrawable
	as Vector with gradient are not supported in api <24
	https://stackoverflow.com/questions/54979464/vector-drawable-with-gradient-color-not-supporting-below-api-24
	https://stackoverflow.com/questions/40872114/can-gradientcolor-be-used-to-define-a-gradient-for-a-fill-or-stroke-entirely-in
	*/
	private void applyGradient(ImageView imegeview) {
		VectorDrawableCompat drawable = (VectorDrawableCompat)imegeview.getDrawable();
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth()*4, drawable.getIntrinsicHeight()*4, Bitmap.Config.ARGB_8888);
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);
/*
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, 0, 0, height, context.getColor(R.color.color_pd), context.getColor(R.color.color_indigo6), Shader.TileMode.CLAMP);
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawRect(0, 0, width, height, paint);
*/
		//Bitmap newBitmap = addGradient(bitmap);
		imegeview.setImageDrawable(new BitmapDrawable(context.getResources(), bitmap));
	}

	private Bitmap addGradient(Bitmap originalBitmap) {
		int width = originalBitmap.getWidth();
		int height = originalBitmap.getHeight();
		Bitmap updatedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(updatedBitmap);

		canvas.drawBitmap(originalBitmap, 0, 0, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, 0, 0, height, context.getColor(R.color.color_pd), context.getColor(R.color.color_indigo6), Shader.TileMode.CLAMP);
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawRect(0, 0, width, height, paint);

		return updatedBitmap;
	}

}

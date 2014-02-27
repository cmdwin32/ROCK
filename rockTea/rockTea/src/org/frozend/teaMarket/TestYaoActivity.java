package org.frozend.teaMarket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.frozend.teaMarket.ShakeListener.OnShakeListener;

import android.R.string;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lehoon.test.R;

public class TestYaoActivity extends Activity implements OnClickListener
{
	/** Called when the activity is first created. */
	ListView mListview;
	ShakeListener mShakeListener;
	private TextView textView;
	private ImageView img;
	private Animation myAnimation_Translate;
	private SoundManager mSoundManager;
	private AssetManager asset_manager = null;
	private long time = 0;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		myAnimation_Translate = AnimationUtils.loadAnimation(this,
//				R.anim.my_translate_action);
//		myAnimation_Translate.setFillAfter(true);
//		textView = (TextView) findViewById(R.id.print);
//		img = (ImageView) findViewById(R.id.head_detail);
//		mSoundManager = new SoundManager();
//		mSoundManager.initSounds(getBaseContext());
//		mSoundManager.addSound(1, R.raw.ming);
//		img.setOnClickListener(this);
		mShakeListener = new ShakeListener(this);
		mShakeListener.setOnShakeListener(new Shake());
		time = System.currentTimeMillis();
	}

	class Shake implements OnShakeListener
	{

		@Override
		public void onShake()
		{
			// tv.setText("ҡһҡ");
//			mSoundManager.playSound(1);
			long now = System.currentTimeMillis();
			Log.e("time", new String() + now);
			if(now - time > 500){
				
				time = now;
				fresh(null);	
			}
		}
		

	}

	@Override
	public void onClick(View v)
	{

		textView.startAnimation(myAnimation_Translate);
	}

	@Override
	protected void onPause()
	{

		// TODO Auto-generated method stub
		super.onPause();
		mShakeListener.stop();
	}

	@Override
	protected void onResume()
	{

		// TODO Auto-generated method stub
		super.onResume();
		mShakeListener.start();

	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		mShakeListener.stop();
	}
	
	public void fresh(View v){
		Log.e("111", "222");
		ImageView img = (ImageView) this.findViewById(R.id.imageView1);
//		img.setImageResource(R.drawable.tea1);
		
		InputStream in = null;
		try {
			String image_name = "tea";
			Random r = new Random();
			image_name += (r.nextInt(3)+1);
			image_name += ".jpeg";
			in = this.getResources().getAssets().open(image_name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        Bitmap bmp=BitmapFactory.decodeStream(in);  
        img.setImageBitmap(bmp);
        
	}

}
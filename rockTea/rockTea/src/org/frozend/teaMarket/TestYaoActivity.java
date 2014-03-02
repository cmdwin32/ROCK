package org.frozend.teaMarket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.frozend.teaMarket.ShakeListener.OnShakeListener;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ApplicationErrorReport.AnrInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.frozend.rocktee.R;

public class TestYaoActivity extends Activity implements OnClickListener
{
	
	private static String[] types = {"typeOne", "typeTwo", "typeThree"};
	private static String[] values = {"beijing", "shanghai", "shenzhen"};

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
		int screenWidth  = getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）  
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageView img = (ImageView) this.findViewById(R.id.imageView1);
		img.setScaleX(screenWidth/480);
		img.setScaleY(screenHeight/640);
		mShakeListener = new ShakeListener(this);
		mShakeListener.setOnShakeListener(new Shake());
		time = System.currentTimeMillis();
		initSp();
	}

	class Shake implements OnShakeListener
	{

		@Override
		public void onShake()
		{
			// tv.setText("摇一摇");
//			mSoundManager.playSound(1);
			long now = System.currentTimeMillis();
			Log.e("time", new String() + now);
			if(now - time > 500){
				
				time = now;
				fresh(null);	
			}
		}
		

	}
	public void initSp(){
		Spinner sp = (Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<String> type = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
		type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(type);
		sp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
			   public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			   {
				   Log.e("tee", types[arg2]+" 对应的值是："+values[arg2]);
				   arg0.setVisibility(View.VISIBLE);
			   }
			   public void onNothingSelected(AdapterView<?> arg0)
			   {
			    //m_TextView.setText("请选择一项...");
				   Spinner sp = (Spinner)findViewById(R.id.spinner1);
				   sp.setSelection(0);
			   }
		});
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
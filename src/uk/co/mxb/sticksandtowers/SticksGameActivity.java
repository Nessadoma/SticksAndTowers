package uk.co.mxb.sticksandtowers;

import android.app.Activity;
import android.os.Bundle;

public class SticksGameActivity extends Activity
{
	private SticksGameView view;
	
	@Override
	public void onCreate(Bundle saved)
	{
		super.onCreate(saved);
		setContentView(R.layout.game_screen_layout);
	}
}

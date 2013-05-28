package uk.co.mxb.sticksandtowers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class SticksGameView extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener 
{
	private MainThread _thread;
	private SticksGameInfo sticksGameInfo;
	
	public SticksGameView(Context context, AttributeSet attributeSet)
	{
		super(context);
		getHolder().addCallback(this);
	}
	
	public SticksGameView(Context context, AttributeSet attributeSet, SticksGameInfo oldState)
	{
		super(context);
		getHolder().addCallback(this);
		sticksGameInfo = oldState;
	}

	@Override
	public boolean onTouch(View v, MotionEvent me) 
	{
		_thread.touches.add(me.getX());
		_thread.touches.add(me.getY());	
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) 
	{
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) 
	{
		_thread = new MainThread(getHolder());
		if(sticksGameInfo == null)
		{
			sticksGameInfo = new SticksGameInfo(new SticksGameHandler());
		}else
		{
			_thread.firstTime = false;
		}
		
		_thread.setRunning(true);
		_thread.start();
		setOnTouchListener(this);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) 
	{
		boolean retry = true;
		_thread.setRunning(false);
		while(retry)
		{
			try
			{
				_thread.join();
				retry = false;
			}catch(InterruptedException e){
				
			}
		}
	}
	
	public SticksGameInfo getInternalState()
	{
		return sticksGameInfo;
	}
}

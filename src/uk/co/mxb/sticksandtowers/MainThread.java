package uk.co.mxb.sticksandtowers;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{
	private SurfaceHolder _surfaceHolder;
	private boolean _run = false;
	boolean firstTime = true;
	List<Float> touches;
	
	public MainThread(SurfaceHolder surfaceHolder)
	{
		_surfaceHolder = surfaceHolder;
		touches = new ArrayList<Float>();
	}
	
	public void setRunning(boolean run)
	{
		_run = run;
	}
	
	@Override
	public void run()
	{
		Canvas c;
		
		while(_run)
		{
			if(firstTime)
			{
				Log.i("Debug", "First time setup");
				firstTime = false;
				continue;
			}
			
			c = null;
			try
			{
				c = _surfaceHolder.lockCanvas(null);
				synchronized(_surfaceHolder)
				{
					if(touches.size() > 4)
					{
						float startX = touches.get(0);
						touches.remove(0);
						float startY = touches.get(0);
						touches.remove(0);
						float stopX = touches.get(0);
						float stopY = touches.get(1);
						Paint p = new Paint();
						p.setColor(Color.RED);
						c.drawLine(startX, startY, stopX, stopY, p);
					}
					
					//update scores or something
					//Log.i("Debug", "Game running...");
				}
			}finally{
				if(c != null)
				{
					_surfaceHolder.unlockCanvasAndPost(c);
				}
			}
		}
	}	
}

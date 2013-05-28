package uk.co.mxb.sticksandtowers;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SticksGameHandler extends Handler 
{
	@Override
	public void handleMessage(Message msg)
	{
		Log.i("Debug", msg.toString());
	}
}

package by.salin.apps.jems.sample.utils;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import static android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;

public class PopupActivityUtil
{
	static final float DEFAULT_SQUARE_PATH = 0.85f;
	static final float DEFAULT_PATH = 0.75f;
	static final float DEFAULT_DIM_AMOUNT = 0.7f;
	private DisplayMetrics displayMetrics;
	private LayoutParams windowLayoutParams;
	private Window window;
	private Activity activity;

	private PopupActivityUtil(Activity activity)
	{
		super();

		this.activity = activity;

		this.displayMetrics = activity.getResources().getDisplayMetrics();
		this.window = activity.getWindow();

		this.window.addFlags(LayoutParams.FLAG_DIM_BEHIND);
		this.window.setGravity(Gravity.CENTER);

		this.windowLayoutParams = this.window.getAttributes();
		this.windowLayoutParams.dimAmount = DEFAULT_DIM_AMOUNT;
		this.window.setAttributes(windowLayoutParams);

		this.setParams(PopupType.SQUARE);
	}

	public static PopupActivityUtil getInstance(Activity activity)
	{
		return new PopupActivityUtil(activity);
	}

	public PopupActivityUtil setParams(int width)
	{
		window.setFlags(FLAG_DIM_BEHIND, FLAG_DIM_BEHIND);
		windowLayoutParams.height = width;
		windowLayoutParams.width = width;
		windowLayoutParams.gravity = Gravity.CENTER;
		window.setAttributes(windowLayoutParams);
		return this;
	}

	public PopupActivityUtil setParams(int width, int height)
	{
		window.setFlags(FLAG_DIM_BEHIND, FLAG_DIM_BEHIND);
		windowLayoutParams.height = height;
		windowLayoutParams.width = width;
		windowLayoutParams.gravity = Gravity.CENTER;
		window.setAttributes(windowLayoutParams);
		return this;
	}

	public PopupActivityUtil setParams(PopupType popupType)
	{
		if (popupType == null || activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			popupType = PopupType.SQUARE;
		}
		switch (popupType)
		{
			case SQUARE:
			{
				setParams(PopupType.SQUARE, DEFAULT_SQUARE_PATH);
				break;
			}
			case RECTANGLE:
			{
				setParams(PopupType.RECTANGLE, DEFAULT_PATH);
				break;
			}
		}
		return this;
	}

	public PopupActivityUtil setParams(PopupType popupType, float part)
	{
		switch (popupType)
		{
			case SQUARE:
			{
				int side = (int) (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) * part);
				setParams(side);
				break;
			}
			case RECTANGLE:
			{
				int width = (int) (displayMetrics.widthPixels * part);
				int heigth = (int) (displayMetrics.heightPixels * part);
				setParams(width, heigth);
				break;
			}
			default:
				break;
		}
		return this;
	}

	public PopupActivityUtil setParams(PopupType popupType, int width)
	{
		switch (popupType)
		{
			case SQUARE:
			{
				setParams(width);
				break;
			}
			case RECTANGLE:
			{
				int heigth = (int) (displayMetrics.heightPixels * DEFAULT_PATH);
				setParams(width, heigth);
				break;
			}
			default:
				break;
		}
		return this;
	}

	public PopupActivityUtil setParams(float partWidth, float partHeight)
	{
		int width = (int) (displayMetrics.widthPixels * partWidth);
		int heigth = (int) (displayMetrics.heightPixels * partHeight);
		setParams(width, heigth);
		return this;
	}

	public static enum PopupType
	{
		SQUARE, RECTANGLE;
	}
}
